package com.aitest.jim.aipicbookserver.service.gateway;

/**
 * @author Liuyi58
 * @since 2023-06-19  22:44
 **/
public class UserInfo {
	private String sessionKey;
	private String openId;

	private long userId;

	public String getSessionKey() {
		return sessionKey;
	}

	public UserInfo setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}

	public String getOpenId() {
		return openId;
	}

	public UserInfo setOpenId(String openId) {
		this.openId = openId;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public UserInfo setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"sessionKey='" + sessionKey + '\'' +
				", openId='" + openId + '\'' +
				", userId=" + userId +
				'}';
	}
}
