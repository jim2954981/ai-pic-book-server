package com.aitest.jim.aipicbookserver.service.gateway;

import com.aitest.jim.aipicbookserver.domain.work.ParagraphDomain;
import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import com.aitest.jim.aipicbookserver.utils.JacksonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.ArrayList;
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
	public static final String NEG_PROMPT =
			"skin spots,acnes,skin blemishes,age spot,mutated hands,mutated fingers,deformed,bad anatomy,disfigured,poorly drawn face,extra limb,ugly,poorly drawn hands,missing limb,floating limbs,disconnected limbs,out of focus,long neck,long body,extra fingers,fewer fingers,,(multi nipples),bad hands,signature,username,bad feet,blurry,bad body";
	private static final String HOST_NAME = "5d08574a3d92889da6.gradio.live";
	@Resource
	private OkHttpClient httpClient;
	private static final String CONTENT_PROMPT =
			"角色：你是一个儿童绘本制作者，你知道如何使用stable diffusion的prompt生成图片。Stable Diffusion支持通过使用提示词来产生新的图像。prompt由普通常见的单词构成。在Prompt的生成中，你需要通过prompt来详细描述 人物相貌、衣着、姿势、视角、背景。用英语单词来描述，请使用,做分隔符，每个提示词不要带引号，只要内容，不要描述的标题，并将这些 按从最重要到最不重要的顺序 排列 。每一句的prompt都要组成一幅完整的画面，只描述 人物相貌、衣着、背景，prompt不准用句子.\n" +
					"任务：你要给一个%d岁的孩子讲一个关于%s的故事，故事主人公是一个%s，故事由%d句话组成，每一句都要有一个要点。\n" +
					"同时，你需要为每一句配一个stable diffusion的英文prompt，\n" + "输出结构：{\"chinese\":${中文内容},\"prompt\":${prompt}}，json数组形式返回";
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
			LOGGER.error("query gpt failed with request:{} and response:{}", request, response);
			return Collections.emptyList();
		}
		List<StoryDTO> storyDTOS = JacksonUtil.fromJsonToList(gptResponseDTO.getChoices().get(0).getMessage().getContent(), StoryDTO.class);
		List<String> chineseContents = storyDTOS.stream().map(StoryDTO::getChinese).collect(Collectors.toList());
		List<String> englishContents = storyDTOS.stream().map(StoryDTO::getPrompt).collect(Collectors.toList());
		List<String> pirUrl = getPics(englishContents);
		List<ParagraphDomain> paragraphDomains = Lists.newArrayList();
		for (int i = 0; i < chineseContents.size(); i++) {
			String content = chineseContents.get(i);
			ParagraphDomain paragraphDomain = new ParagraphDomain();
			paragraphDomain.setOrder(i + 1);
			paragraphDomain.setContent(content);
			paragraphDomain.setPic(pirUrl.get(i));
			paragraphDomains.add(paragraphDomain);
		}
		return paragraphDomains;
	}

	private List<String> getPics(List<String> prompts) throws IOException {
		List<String> pics = new ArrayList<>();
		for (String prompt : prompts) {
			SdRequest sdRequest = new SdRequest().setPrompt(prompt).setSteps(27).setWidth(512).setHeight(512).setBatchSize(1).setCfgScale(7).setNegativePrompt(NEG_PROMPT);
			LOGGER.info("query sd api with reqeust:{}", sdRequest);
			RequestBody requestBody = RequestBody.create(JacksonUtil.toJson(sdRequest), MediaType.parse("application/json"));
			Request request = new Request.Builder().url(String.format("https://%s/sdapi/v1/txt2img", HOST_NAME)).post(requestBody).build();
			Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful() || Objects.isNull(response.body())) {
				LOGGER.error("query sd failed with request:{} and response:{}", request, response);
				return Collections.emptyList();
			}
			SdResponse sdResponse = JacksonUtil.fromJson(response.body().string(), SdResponse.class);
			pics.addAll(sdResponse.images);
		}
		return pics;
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

	private static class SdRequest {
		private String prompt;
		@JsonProperty(value = "negative_prompt")
		private String negativePrompt;
		@JsonProperty(value = "batch_size")
		private int batchSize = 1;
		private int width;
		private int height;
		@JsonProperty(value = "n_iter")
		private int nIter = 1;
		@JsonProperty(value = "cfg_scale")
		private int cfgScale;
		private int steps;

		public String getPrompt() {
			return prompt;
		}

		public SdRequest setPrompt(String prompt) {
			this.prompt = prompt;
			return this;
		}

		public String getNegativePrompt() {
			return negativePrompt;
		}

		public SdRequest setNegativePrompt(String negativePrompt) {
			this.negativePrompt = negativePrompt;
			return this;
		}

		public int getBatchSize() {
			return batchSize;
		}

		public SdRequest setBatchSize(int batchSize) {
			this.batchSize = batchSize;
			return this;
		}

		public int getWidth() {
			return width;
		}

		public SdRequest setWidth(int width) {
			this.width = width;
			return this;
		}

		public int getHeight() {
			return height;
		}

		public SdRequest setHeight(int height) {
			this.height = height;
			return this;
		}

		public int getnIter() {
			return nIter;
		}

		public SdRequest setnIter(int nIter) {
			this.nIter = nIter;
			return this;
		}

		public int getCfgScale() {
			return cfgScale;
		}

		public SdRequest setCfgScale(int cfgScale) {
			this.cfgScale = cfgScale;
			return this;
		}

		public int getSteps() {
			return steps;
		}

		public SdRequest setSteps(int steps) {
			this.steps = steps;
			return this;
		}

		@Override
		public String toString() {
			return "SdRequest{" + "prompt='" + prompt + '\'' + ", negativePrompt='" + negativePrompt + '\'' + ", batchSize=" + batchSize + ", width=" + width + ", height=" +
					height + ", nIter=" + nIter + ", cfgScale=" + cfgScale + '}';
		}
	}

	private static class SdResponse {
		private List<String> images;

		public List<String> getImages() {
			return images;
		}

		public SdResponse setImages(List<String> images) {
			this.images = images;
			return this;
		}

		@Override
		public String toString() {
			return "SdResponse{" + "images=" + images + '}';
		}
	}
}
