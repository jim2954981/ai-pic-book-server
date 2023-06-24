package com.aitest.jim.aipicbookserver.userinterface.vo;

import java.util.List;

/**
 * @author Liuyi58
 * @since 2023-06-22  23:30
 **/
public class WorkVO {
	private long id;
	private long userId;
	private String title;
	private List<ParaGraphVO> paraGraphVOList;
	private String createTime;
	private String firstPic;

	public long getId() {
		return id;
	}

	public WorkVO setId(long id) {
		this.id = id;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public WorkVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public WorkVO setTitle(String title) {
		this.title = title;
		return this;
	}

	public List<ParaGraphVO> getParaGraphVOList() {
		return paraGraphVOList;
	}

	public WorkVO setParaGraphVOList(List<ParaGraphVO> paraGraphVOList) {
		this.paraGraphVOList = paraGraphVOList;
		return this;
	}

	public String getCreateTime() {
		return createTime;
	}

	public WorkVO setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getFirstPic() {
		return firstPic;
	}

	public WorkVO setFirstPic(String firstPic) {
		this.firstPic = firstPic;
		return this;
	}

	@Override
	public String toString() {
		return "WorkVO{" +
				"id=" + id +
				", userId=" + userId +
				", title='" + title + '\'' +
				", paraGraphVOList=" + paraGraphVOList +
				", createTime='" + createTime + '\'' +
				", firstPic='" + firstPic + '\'' +
				'}';
	}
}
