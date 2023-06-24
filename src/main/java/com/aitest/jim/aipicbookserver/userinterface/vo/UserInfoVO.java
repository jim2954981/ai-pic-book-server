package com.aitest.jim.aipicbookserver.userinterface.vo;

/**
 * @author Liuyi58
 * @since 2023-06-19  23:22
 **/
public class UserInfoVO {
	private long userId;
	private String sessionKey;

	public long getUserId() {
		return userId;
	}

	public UserInfoVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public UserInfoVO setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}

	@Override
	public String toString() {
		return "UserInfoVO{" + "userId=" + userId + ", sessionKey='" + sessionKey + '\'' + '}';
	}
}
