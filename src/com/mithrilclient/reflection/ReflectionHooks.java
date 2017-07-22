package com.mithrilclient.reflection;

import java.awt.Component;

import com.mithrilclient.reflection.entry.ClassEntry;
import com.mithrilclient.reflection.entry.FieldEntry;
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

	public static void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		CANVAS_ENTRY_PARENT_CLASS.init();
		CANVAS_ENTRY.init();

		CANVAS_CLASS.init();
		COMPONENT_FIELD.init();

		CLIENT_CLASS.init();
		XP_FIELD.init();
		SKILLS_FIELD.init();
		BASE_SKILLS_FIELD.init();
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
}
