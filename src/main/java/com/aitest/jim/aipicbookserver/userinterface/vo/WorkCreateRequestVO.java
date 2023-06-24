package com.aitest.jim.aipicbookserver.userinterface.vo;

/**
 * @author Liuyi58
 * @since 2023-06-20  23:18
 **/
public class WorkCreateRequestVO {
	private String title;
	private String contentAbstract;
	private int childAge;
	private int readTime;
	private int characterCode;
	private long userId;

	public String getTitle() {
		return title;
	}

	public WorkCreateRequestVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContentAbstract() {
		return contentAbstract;
	}

	public WorkCreateRequestVO setContentAbstract(String contentAbstract) {
		this.contentAbstract = contentAbstract;
		return this;
	}

	public int getChildAge() {
		return childAge;
	}

	public WorkCreateRequestVO setChildAge(int childAge) {
		this.childAge = childAge;
		return this;
	}

	public int getReadTime() {
		return readTime;
	}

	public WorkCreateRequestVO setReadTime(int readTime) {
		this.readTime = readTime;
		return this;
	}

	public int getCharacterCode() {
		return characterCode;
	}

	public WorkCreateRequestVO setCharacterCode(int characterCode) {
		this.characterCode = characterCode;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public WorkCreateRequestVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String toString() {
		return "WorkCreateRequestVO{" +
				"title='" + title + '\'' +
				", contentAbstract='" + contentAbstract + '\'' +
				", childAge=" + childAge +
				", readTime=" + readTime +
				", characterCode=" + characterCode +
				", userId=" + userId +
				'}';
	}
}
