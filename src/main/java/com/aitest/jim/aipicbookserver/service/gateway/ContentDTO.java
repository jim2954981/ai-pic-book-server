package com.aitest.jim.aipicbookserver.service.gateway;

/**
 * @author Liuyi58
 * @since 2023-06-22  19:30
 **/
public class ContentDTO {
	private String chinese;
	private String english;

	public String getChinese() {
		return chinese;
	}

	public ContentDTO setChinese(String chinese) {
		this.chinese = chinese;
		return this;
	}

	public String getEnglish() {
		return english;
	}

	public ContentDTO setEnglish(String english) {
		this.english = english;
		return this;
	}

	@Override
	public String toString() {
		return "ContentDTO{" +
				"chinese='" + chinese + '\'' +
				", english='" + english + '\'' +
				'}';
	}
}

