package com.aitest.jim.aipicbookserver.domain.work;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Liuyi58
 * @since 2023-06-22  15:39
 **/
public class WorkDomain {
	private long id;
	private List<ParagraphDomain> paragraphs;
	private long userId;
	private long createTime;
	private long updateTime;
	private String title;
	private String storyAbstract;
	private int readTime;
	private int childAge;
	private CharacterEnum character;
	private String firstPic;

	public long getId() {
		return id;
	}

	public WorkDomain setId(long id) {
		this.id = id;
		return this;
	}

	public List<ParagraphDomain> getParagraphs() {
		return paragraphs;
	}

	public WorkDomain setParagraphs(List<ParagraphDomain> paragraphs) {
		this.paragraphs = paragraphs;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public WorkDomain setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public long getCreateTime() {
		return createTime;
	}

	public WorkDomain setCreateTime(long createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public WorkDomain setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getStoryAbstract() {
		return storyAbstract;
	}

	public WorkDomain setStoryAbstract(String storyAbstract) {
		this.storyAbstract = storyAbstract;
		return this;
	}

	public String getFirstPic() {
		return firstPic;
	}

	public WorkDomain setFirstPic(String firstPic) {
		this.firstPic = firstPic;
		return this;
	}

	public int getReadTime() {
		return readTime;
	}

	public WorkDomain setReadTime(int readTime) {
		this.readTime = readTime;
		return this;
	}

	public int getChildAge() {
		return childAge;
	}

	public WorkDomain setChildAge(int childAge) {
		this.childAge = childAge;
		return this;
	}

	public CharacterEnum getCharacter() {
		return character;
	}

	public WorkDomain setCharacter(CharacterEnum character) {
		this.character = character;
		return this;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public WorkDomain setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public static WorkPO toPO(WorkDomain workDomain) {
		return new WorkPO().setFirstPic(workDomain.getFirstPic()).setCharacter(workDomain.getCharacter().getId()).setChildAge(workDomain.getChildAge())
				.setTitle(workDomain.getTitle()).setStoryAbstract(workDomain.getStoryAbstract()).setUserId(workDomain.userId)
				.setCreateTime(new Timestamp(workDomain.getCreateTime())).setUpdateTime(new Timestamp(workDomain.getUpdateTime())).setReadTime(workDomain.getReadTime());
	}

	public static WorkDomain fromPO(WorkPO workPO) {
		return new WorkDomain().setFirstPic(workPO.getFirstPic()).setId(workPO.getId()).setUpdateTime(workPO.getUpdateTime().getTime())
				.setCreateTime(workPO.getCreateTime().getTime()).setCharacter(CharacterEnum.findById(workPO.getCharacter())).setStoryAbstract(workPO.getStoryAbstract())
				.setTitle(workPO.getTitle()).setChildAge(workPO.getChildAge()).setReadTime(workPO.getReadTime());
	}

	@Override
	public String toString() {
		return "WorkDomain{" + "id=" + id + ", paragraphs=" + paragraphs + ", userId=" + userId + ", createTime=" + createTime + ", updateTime=" + updateTime + ", title='" +
				title + '\'' + ", storyAbstract='" + storyAbstract + '\'' + ", readTime=" + readTime + ", childAge=" + childAge + ", character=" + character + ", firstPic='" +
				firstPic + '\'' + '}';
	}
}
