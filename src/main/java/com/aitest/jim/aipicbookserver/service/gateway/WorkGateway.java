package com.aitest.jim.aipicbookserver.service.gateway;

import com.aitest.jim.aipicbookserver.domain.work.ParagraphDomain;
import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import com.aitest.jim.aipicbookserver.utils.JacksonUtil;
import com.google.common.collect.Lists;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.aitest.jim.aipicbookserver.utils.Constants.GPT_API_KEY;

/**
 * @author Liuyi58
 * @since 2023-06-22  17:24
 **/
@Service
public class WorkGateway {
	@Resource
	private OkHttpClient httpClient;
	private static final String CONTENT_PROMPT =
			"你要给一个%d岁的孩子讲一个关于%s的童话。童话主角是%s，童话要有一个美好的结尾，必须是原创，请将童话分成%d个段落，每一个段落在100字以内，每一个段落在100字以内，每个段落的结构为：{\"chinese\":${中文内容},\n" +
					"\"english\":${英文内容}}，json数组形式返回,key是story";
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkGateway.class);

	public List<ParagraphDomain> getContents(WorkDomain workDomain) throws IOException {
		GptRequestDTO requestDTO = new GptRequestDTO().setModel("gpt-3.5-turbo").setMessages(Lists.newArrayList(new GptMessageDTO().setRole("user").setContent(
				String.format(CONTENT_PROMPT, workDomain.getChildAge(), workDomain.getStoryAbstract(), workDomain.getCharacter().getName(), workDomain.getReadTime()))));
		LOGGER.info("query gpt api with reqeust:{}", requestDTO);
		RequestBody requestBody = RequestBody.create(JacksonUtil.toJson(requestDTO), MediaType.parse("application/json"));
		Request request =
				new Request.Builder().url("https://api.openai.com/v1/chat/completions").header("Authorization", String.format("Bearer %s", GPT_API_KEY)).post(requestBody).build();
		Response response = httpClient.newCall(request).execute();
		if (!response.isSuccessful() || Objects.isNull(response.body())) {
			LOGGER.error("query gpt  failed with request:{} and response:{}", request, response);
			return Collections.emptyList();
		}
		GptResponseDTO gptResponseDTO = JacksonUtil.fromJson(response.body().string(), GptResponseDTO.class);
		if (CollectionUtils.isEmpty(gptResponseDTO.getChoices())) {
			LOGGER.error("query gpt  failed with request:{} and response:{}", request, response);
			return Collections.emptyList();
		}
		ContentDTO contentDTO = JacksonUtil.fromJson(gptResponseDTO.getChoices().get(0).getMessage().getContent(), ContentDTO.class);
		List<ContentDTO.StoryDTO> storyDTOS = contentDTO.getStory();
		List<String> chineseContents = storyDTOS.stream().map(ContentDTO.StoryDTO::getChinese).collect(Collectors.toList());
		List<String> englishContents = storyDTOS.stream().map(ContentDTO.StoryDTO::getEnglish).collect(Collectors.toList());
		//todo: 生图api
		List<String> pirUrl;
		List<ParagraphDomain> paragraphDomains = Lists.newArrayList();
		for (int i = 0; i < chineseContents.size(); i++) {
			String content = chineseContents.get(i);
			ParagraphDomain paragraphDomain = new ParagraphDomain();
			paragraphDomain.setOrder(i + 1);
			paragraphDomain.setContent(content);
			paragraphDomain.setPic("https://tdesign.gtimg.com/miniprogram/images/swiper1.png");
			paragraphDomains.add(paragraphDomain);
		}
		return paragraphDomains;
	}

	private static class GptRequestDTO {
		private String model;
		private List<GptMessageDTO> messages;

		public String getModel() {
			return model;
		}

		public GptRequestDTO setModel(String model) {
			this.model = model;
			return this;
		}

		public List<GptMessageDTO> getMessages() {
			return messages;
		}

		public GptRequestDTO setMessages(List<GptMessageDTO> messages) {
			this.messages = messages;
			return this;
		}

		@Override
		public String toString() {
			return "GptRequestDTO{" + "model='" + model + '\'' + ", messages=" + messages + '}';
		}
	}

	private static class GptMessageDTO {
		private String role;
		private String content;

		public String getRole() {
			return role;
		}

		public GptMessageDTO setRole(String role) {
			this.role = role;
			return this;
		}

		public String getContent() {
			return content;
		}

		public GptMessageDTO setContent(String content) {
			this.content = content;
			return this;
		}

		@Override
		public String toString() {
			return "GptMessageDTO{" + "role='" + role + '\'' + ", content='" + content + '\'' + '}';
		}
	}

	private static class GptResponseDTO {
		private List<GptResponseChoice> choices;

		public List<GptResponseChoice> getChoices() {
			return choices;
		}

		public GptResponseDTO setChoices(List<GptResponseChoice> choices) {
			this.choices = choices;
			return this;
		}

		@Override
		public String toString() {
			return "GptResponseDTO{" + "choices=" + choices + '}';
		}
	}

	private static class GptResponseChoice {
		private int index;
		private GptMessageDTO message;

		public int getIndex() {
			return index;
		}

		public GptResponseChoice setIndex(int index) {
			this.index = index;
			return this;
		}

		public GptMessageDTO getMessage() {
			return message;
		}

		public GptResponseChoice setMessage(GptMessageDTO message) {
			this.message = message;
			return this;
		}

		@Override
		public String toString() {
			return "GptResponseChoice{" + "index=" + index + ", message=" + message + '}';
		}
	}
}
