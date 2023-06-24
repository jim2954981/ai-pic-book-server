package com.aitest.jim.aipicbookserver.service.gateway;

import com.aitest.jim.aipicbookserver.domain.work.ParagraphDomain;
import com.aitest.jim.aipicbookserver.domain.work.WorkDomain;
import com.aitest.jim.aipicbookserver.utils.JacksonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liuyi58
 * @since 2023-06-22  17:24
 **/
@Service
public class WorkGateway {
	@Resource
	private OpenAiService openAiUtil;
	private static final String CONTENT_PROMPT =
			"你要给一个%d岁的孩子讲一个关于%s的童话。童话主角是%s，童话要有一个美好的结尾，必须是原创，请将童话分成%d个段落，每一个段落在100字以内，每个段落搭配英文翻译，中文的key是chinese,英文的key是english,以json形式返回";
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkGateway.class);

	public List<ParagraphDomain> getContents(WorkDomain workDomain) throws IOException {
		CompletionRequest completionRequest = CompletionRequest.builder()
				.prompt(String.format(CONTENT_PROMPT, workDomain.getChildAge(), workDomain.getStoryAbstract(), workDomain.getCharacter().getName(), workDomain.getReadTime()))
				.model("text-davinci-003").temperature(1.0).echo(true).build();
		List<CompletionChoice> completionChoices = openAiUtil.createCompletion(completionRequest).getChoices();
		if (CollectionUtils.isEmpty(completionChoices) || Strings.isNullOrEmpty(completionChoices.get(0).getText())) {
			LOGGER.error("generate work failed with request:{} and response:{}", completionRequest, completionChoices.get(0));
			return Collections.emptyList();
		}
		List<ContentDTO> contents = JacksonUtil.fromJsonToList(completionChoices.get(0).getText(), ContentDTO.class);
		List<String> chineseContents = contents.stream().map(ContentDTO::getChinese).collect(Collectors.toList());
		List<String> englishContents = contents.stream().map(ContentDTO::getEnglish).collect(Collectors.toList());
		//todo: 生图api
		List<String> pirUrl;
		List<ParagraphDomain> paragraphDomains = Lists.newArrayList();
		for (int i = 0; i < chineseContents.size(); i++) {
			String content = chineseContents.get(i);
			ParagraphDomain paragraphDomain = new ParagraphDomain();
			paragraphDomain.setOrder(i);
			paragraphDomain.setContent(content);
			paragraphDomains.add(paragraphDomain);
		}
		return paragraphDomains;
	}
}
