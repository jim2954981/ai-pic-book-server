package com.aitest.jim.aipicbookserver.domain.work;

import java.sql.Timestamp;

/**
 * @author Liuyi58
 * @since 2023-06-22  15:29
 **/
public class WorkPO {
	private long id;
	private String title;
	private String storyAbstract;
	private int childAge;
	private int readTime;
	private long userId;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int character;
	private String firstPic;
	public long getId() {
		return id;
	}

	public WorkPO setId(long id) {
		this.id = id;
		return this;
	}

	public String getFirstPic() {
		return firstPic;
	}

	public WorkPO setFirstPic(String firstPic) {
		this.firstPic = firstPic;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public WorkPO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getStoryAbstract() {
		return storyAbstract;
	}

	public WorkPO setStoryAbstract(String storyAbstract) {
		this.storyAbstract = storyAbstract;
		return this;
	}

	public int getChildAge() {
		return childAge;
	}

	public WorkPO setChildAge(int childAge) {
		this.childAge = childAge;
		return this;
	}

	public int getReadTime() {
		return readTime;
	}

	public WorkPO setReadTime(int readTime) {
		this.readTime = readTime;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public WorkPO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public WorkPO setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public WorkPO setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public int getCharacter() {
		return character;
	}

	public WorkPO setCharacter(int character) {
		this.character = character;
		return this;
	}

	@Override
	public String toString() {
		return "WorkPO{" +
				"id=" + id +
				", title='" + title + '\'' +
				", storyAbstract='" + storyAbstract + '\'' +
				", childAge=" + childAge +
				", readTime=" + readTime +
				", userId=" + userId +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", character=" + character +
				", firstPic='" + firstPic + '\'' +
				'}';
	}
}
