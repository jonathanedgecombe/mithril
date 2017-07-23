package com.mithrilclient.reflection;

import java.awt.Component;

import com.mithrilclient.reflection.entry.ClassEntry;
import com.mithrilclient.reflection.entry.FieldEntry;
import com.mithrilclient.reflection.entry.ObfuscatedIntStaticFieldEntry;
import com.mithrilclient.reflection.entry.StaticFieldEntry;

public final class ReflectionHooks {
	private final static ClassEntry CANVAS_ENTRY_PARENT_CLASS = new ClassEntry("av");
	private final static StaticFieldEntry<?> CANVAS_ENTRY = new StaticFieldEntry<>(CANVAS_ENTRY_PARENT_CLASS, "ac");

	private final static ClassEntry CANVAS_CLASS = new ClassEntry("bl");
	private final static FieldEntry<Component, ?> COMPONENT_FIELD = new FieldEntry<>(CANVAS_CLASS, CANVAS_ENTRY, "c");

	private final static ClassEntry CLIENT_CLASS = new ClassEntry("client");
	private final static StaticFieldEntry<int[]> XP_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jt");
	private final static StaticFieldEntry<int[]> SKILLS_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jp");
	private final static StaticFieldEntry<int[]> BASE_SKILLS_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jn");

	private final static ClassEntry LOGIN_CONTROLLER_CLASS = new ClassEntry("cu");
	private final static StaticFieldEntry<String> USERNAME_FIELD = new StaticFieldEntry<>(LOGIN_CONTROLLER_CLASS, "ad");
	private final static StaticFieldEntry<Integer> CURSOR_FIELD_FIELD = new ObfuscatedIntStaticFieldEntry(LOGIN_CONTROLLER_CLASS, "aa", 1056528165, -155548924);
	private final static StaticFieldEntry<Integer> LOGIN_STATE_FIELD = new ObfuscatedIntStaticFieldEntry(LOGIN_CONTROLLER_CLASS, "ak", -1832063215, 161866225);
	private final static StaticFieldEntry<String> LOGIN_MESSAGE_FIELD = new StaticFieldEntry<>(LOGIN_CONTROLLER_CLASS, "at");

	public static void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		CANVAS_ENTRY_PARENT_CLASS.init();
		CANVAS_ENTRY.init();

		CANVAS_CLASS.init();
		COMPONENT_FIELD.init();

		CLIENT_CLASS.init();
		XP_FIELD.init();
		SKILLS_FIELD.init();
		BASE_SKILLS_FIELD.init();

		LOGIN_CONTROLLER_CLASS.init();
		USERNAME_FIELD.init();
		CURSOR_FIELD_FIELD.init();
		LOGIN_STATE_FIELD.init();
		LOGIN_MESSAGE_FIELD.init();
	}

	public static Component getCanvasParent() {
		return COMPONENT_FIELD.get();
	}

	public static void setCanvasParent(Component component) {
		COMPONENT_FIELD.set(component);
	}

	public static int[] getXpLevels() {
		return XP_FIELD.get();
	}

	public static int[] getSkills() {
		return SKILLS_FIELD.get();
	}

	public static int[] getBaseSkills() {
		return BASE_SKILLS_FIELD.get();
	}

	public static String getUsername() {
		return USERNAME_FIELD.get();
	}

	public static void setUsername(String username) {
		USERNAME_FIELD.set(username);
	}

	public static int getLoginCursorField() {
		return CURSOR_FIELD_FIELD.get();
	}

	public static void setLoginCursorField(int field) {
		CURSOR_FIELD_FIELD.set(field);
	}

	public static int getLoginState() {
		return LOGIN_STATE_FIELD.get();
	}

	public static void setLoginState(int state) {
		LOGIN_STATE_FIELD.set(state);
	}

	public static void setLoginMessage(String message) {
		LOGIN_MESSAGE_FIELD.set(message);
	}
}
