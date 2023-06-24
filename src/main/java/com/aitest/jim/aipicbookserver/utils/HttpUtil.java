package com.aitest.jim.aipicbookserver.utils;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liuyi58
 * @since 2023-06-18  20:52
 **/
@Configuration
public class HttpUtil {
	@Bean(name = "httpClient")
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().build();
	}
}
