package com.aitest.jim.aipicbookserver.utils;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.aitest.jim.aipicbookserver.utils.Constants.GPT_API_KEY;

/**
 * @author Liuyi58
 * @since 2023-06-22  18:25
 **/
@Configuration
public class OpenAiUtilConfigure {
	@Bean(name = "openAiUtil")
	public OpenAiService getService(){
		return new OpenAiService(GPT_API_KEY);
	}
}
