package com.aitest.jim.aipicbookserver.domain.work;

import java.util.Arrays;

/**
 * @author Liuyi58
 * @since 2023-06-21  00:00
 **/
public enum CharacterEnum {
	FOX(1, "小狐狸"), PANDA(2, "小熊猫"), CAT(3, "小猫咪"), RABBIT(4, "小白兔");
	private final int id;
	private final String name;

	CharacterEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static CharacterEnum findById(int id) {
		return Arrays.stream(values()).filter(e -> e.getId() == id).findFirst().orElse(FOX);
	}
}
