package com.aitest.jim.aipicbookserver.userinterface.vo;

/**
 * @author Liuyi58
 * @since 2023-06-18  20:04
 **/
public class BaseResponse<T> {
	private T data;
	private String msg;
	private int code;

	public T getData() {
		return data;
	}

	public BaseResponse<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BaseResponse<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public int getCode() {
		return code;
	}

	public BaseResponse<T> setCode(int code) {
		this.code = code;
		return this;
	}

	@Override
	public String toString() {
		return "BaseResponse{" + "data=" + data + ", msg='" + msg + '\'' + ", code=" + code + '}';
	}

	public BaseResponse(T data, String msg, int code) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public BaseResponse() {
	}

	public static BaseResponse failure() {
		return new BaseResponse(null, "request failure", 1);
	}

	public static BaseResponse failure(String msg) {
		return new BaseResponse(null, msg, 1);
	}

	public static <T> BaseResponse<T> success(T data) {
		return new BaseResponse<>(data, "request success", 0);
	}
}
