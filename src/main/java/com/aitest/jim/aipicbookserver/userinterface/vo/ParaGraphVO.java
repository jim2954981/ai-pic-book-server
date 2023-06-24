package com.aitest.jim.aipicbookserver.userinterface.vo;

/**
 * @author Liuyi58
 * @since 2023-06-20  23:17
 **/
public class ParaGraphVO {
	private long id;
	private long workId;
	private String content;
	private String pic;

	public long getId() {
		return id;
	}

	public ParaGraphVO setId(long id) {
		this.id = id;
		return this;
	}

	public long getWorkId() {
		return workId;
	}

	public ParaGraphVO setWorkId(long workId) {
		this.workId = workId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ParaGraphVO setContent(String content) {
		this.content = content;
		return this;
	}

	public String getPic() {
		return pic;
	}

	public ParaGraphVO setPic(String pic) {
		this.pic = pic;
		return this;
	}

	@Override
	public String toString() {
		return "ParaGraphVO{" +
				"id=" + id +
				", workId=" + workId +
				", content='" + content + '\'' +
				", pic='" + pic + '\'' +
				'}';
	}
}
