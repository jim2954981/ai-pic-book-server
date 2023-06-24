package com.aitest.jim.aipicbookserver.domain.work;

import java.sql.Timestamp;

/**
 * @author Liuyi58
 * @since 2023-06-22  15:39
 **/
public class ParagraphDomain {
	private long id;
	private long workId;
	private String content;
	private int order;
	private String pic;
	private long createTime;
	private long updateTime;

	public long getId() {
		return id;
	}

	public ParagraphDomain setId(long id) {
		this.id = id;
		return this;
	}

	public long getWorkId() {
		return workId;
	}

	public ParagraphDomain setWorkId(long workId) {
		this.workId = workId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ParagraphDomain setContent(String content) {
		this.content = content;
		return this;
	}

	public int getOrder() {
		return order;
	}

	public ParagraphDomain setOrder(int order) {
		this.order = order;
		return this;
	}

	public String getPic() {
		return pic;
	}

	public ParagraphDomain setPic(String pic) {
		this.pic = pic;
		return this;
	}

	public static ParagraphPO toPO(ParagraphDomain domain) {
		return new ParagraphPO().setContent(domain.getContent()).setCreateTime(new Timestamp(domain.getCreateTime())).setUpdateTime(new Timestamp(domain.getUpdateTime()))
				.setOrder(domain.getOrder()).setPic(domain.getPic()).setWorkId(domain.getWorkId());
	}

	public static ParagraphDomain fromPO(ParagraphPO paragraphPO) {
		return new ParagraphDomain().setId(paragraphPO.getId()).setWorkId(paragraphPO.getWorkId()).setPic(paragraphPO.getPic()).setOrder(paragraphPO.getOrder())
				.setContent(paragraphPO.getContent()).setCreateTime(paragraphPO.getCreateTime().getTime()).setUpdateTime(paragraphPO.getUpdateTime().getTime());
	}

	public long getCreateTime() {
		return createTime;
	}

	public ParagraphDomain setCreateTime(long createTime) {
		this.createTime = createTime;
		return this;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public ParagraphDomain setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	@Override
	public String toString() {
		return "ParagraphDomain{" + "id=" + id + ", workId=" + workId + ", content='" + content + '\'' + ", order=" + order + ", pic='" + pic + '\'' + ", createTime=" +
				createTime + ", updateTime=" + updateTime + '}';
	}
}
