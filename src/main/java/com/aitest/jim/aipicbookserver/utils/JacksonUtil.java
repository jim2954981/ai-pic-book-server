package com.aitest.jim.aipicbookserver.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Liuyi58
 * @since 2023-06-19  23:01
 **/
public class JacksonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
	}

	/**
	 * 将Java对象序列化为JSON字符串
	 *
	 * @param object Java对象
	 * @return JSON字符串
	 * @throws JsonProcessingException
	 */
	public static String toJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 将JSON字符串反序列化为Java对象
	 *
	 * @param json  JSON字符串
	 * @param clazz Java对象类型
	 * @param <T>   Java对象类型
	 * @return Java对象
	 * @throws IOException
	 */
	public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
		return objectMapper.readValue(json, clazz);
	}

	public static Map<String, Object> fromJsonToMap(String json) throws IOException {
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
		};
		return objectMapper.readValue(json, typeReference);
	}

	public static <T> List<T> fromJsonToList(String json, Class<T> clazz) throws IOException {
		TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
		};
		return objectMapper.readValue(json, typeReference);
	}
}
