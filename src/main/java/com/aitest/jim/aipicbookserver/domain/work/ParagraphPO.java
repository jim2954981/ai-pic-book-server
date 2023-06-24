package com.aitest.jim.aipicbookserver.domain.work;

import java.sql.Timestamp;

/**
 * @author Liuyi58
 * @since 2023-06-22  15:31
 **/
public class ParagraphPO {
	private long id;
	private long workId;
	private int order;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String content;
	private String pic;

	public long getId() {
		return id;
	}

	public ParagraphPO setId(long id) {
		this.id = id;
		return this;
	}

	public long getWorkId() {
		return workId;
	}

	public ParagraphPO setWorkId(long workId) {
		this.workId = workId;
		return this;
	}

	public int getOrder() {
		return order;
	}

	public ParagraphPO setOrder(int order) {
		this.order = order;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public ParagraphPO setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public ParagraphPO setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ParagraphPO setContent(String content) {
		this.content = content;
		return this;
	}

	public String getPic() {
		return pic;
	}

	public ParagraphPO setPic(String pic) {
		this.pic = pic;
		return this;
	}

	@Override
	public String toString() {
		return "ParagraphPO{" +
				"id=" + id +
				", workId=" + workId +
				", order=" + order +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", content='" + content + '\'' +
				", pic='" + pic + '\'' +
				'}';
	}
}
