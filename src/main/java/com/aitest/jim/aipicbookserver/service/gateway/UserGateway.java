package com.aitest.jim.aipicbookserver.service.gateway;

import com.aitest.jim.aipicbookserver.utils.Constants;
import com.aitest.jim.aipicbookserver.utils.JacksonUtil;
import com.google.common.base.Strings;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author Liuyi58
 * @since 2023-06-19  22:43
 **/
@Component
public class UserGateway {
	@Resource
	private OkHttpClient httpClient;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserGateway.class);

	public UserInfo getOpenIdInfo(String cCode) {
		Request request = new Request.Builder().url(
				String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", Constants.APP_ID, Constants.APP_SECRET,
						cCode)).build();
		try (Response response = httpClient.newCall(request).execute()) {
			// 处理响应
			if (Objects.isNull(response)) {
				LOGGER.error("call code2session failed with request:{}", cCode);
				return null;
			}
			String responseData = response.body().string();
			if (Strings.isNullOrEmpty(responseData)) {
				LOGGER.error("call code2session failed with request:{}", cCode);
				return null;
			}
			Map<String, Object> map = JacksonUtil.fromJsonToMap(responseData);
			String sessionKey = (String)map.get("session_key");
			String openid = (String)map.get("openid");
			if (Strings.isNullOrEmpty(sessionKey) || Strings.isNullOrEmpty(openid)) {
				LOGGER.error("call code2session failed with request:{} and response:{}", cCode, responseData);
				return null;
			}
			return new UserInfo().setOpenId(openid).setSessionKey(sessionKey);
		} catch (IOException e) {
			LOGGER.error("call code2session failed with request:{}", cCode, e);
			return null;
		}
	}
}
