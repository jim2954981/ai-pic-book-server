package com.aitest.jim.aipicbookserver.domain.user;

import java.sql.Timestamp;

/**
 * @author Liuyi58
 * @since 2023-06-18  19:33
 **/
public class UserPO {
	private Long id;
	private String wxOpenId;
	private Timestamp addTime;
	private Timestamp updateTime;

	public Long getId() {
		return id;
	}

	public UserPO setId(Long id) {
		this.id = id;
		return this;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public UserPO setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
		return this;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public UserPO setAddTime(Timestamp addTime) {
		this.addTime = addTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public UserPO setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	@Override
	public String toString() {
		return "UserPO{" +
				"id=" + id +
				", wxOpenId='" + wxOpenId + '\'' +
				", addTime=" + addTime +
				", updateTime=" + updateTime +
				'}';
	}
}
