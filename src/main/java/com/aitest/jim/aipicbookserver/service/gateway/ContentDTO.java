package com.aitest.jim.aipicbookserver.service.gateway;

import java.util.List;

/**
 * @author Liuyi58
 * @since 2023-06-22  19:30
 **/
public class ContentDTO {
	private List<StoryDTO> story;

	public List<StoryDTO> getStory() {
		return story;
	}

	public ContentDTO setStory(List<StoryDTO> story) {
		this.story = story;
		return this;
	}

	@Override
	public String toString() {
		return "ContentDTO{" +
				"story=" + story +
				'}';
	}

	public static class StoryDTO{
		private String chinese;
		private String english;

		public String getChinese() {
			return chinese;
		}

		public StoryDTO setChinese(String chinese) {
			this.chinese = chinese;
			return this;
		}

		public String getEnglish() {
			return english;
		}

		public StoryDTO setEnglish(String english) {
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
}

