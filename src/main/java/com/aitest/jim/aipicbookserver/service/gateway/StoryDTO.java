package com.aitest.jim.aipicbookserver.service.gateway;

/**
 * @author Liuyi58
 * @since 2023-06-22  19:30
 **/
public class StoryDTO {
	private String chinese;
	private String prompt;

	public String getChinese() {
		return chinese;
	}

	public StoryDTO setChinese(String chinese) {
		this.chinese = chinese;
		return this;
	}

	public String getPrompt() {
		return prompt;
	}

	public StoryDTO setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}

	@Override
	public String toString() {
		return "StoryDTO{" + "chinese='" + chinese + '\'' + ", prompt='" + prompt + '\'' + '}';
	}
}


