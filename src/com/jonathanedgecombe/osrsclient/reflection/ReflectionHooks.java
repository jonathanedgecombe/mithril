package com.jonathanedgecombe.osrsclient.reflection;

import java.awt.Component;

import com.jonathanedgecombe.osrsclient.reflection.entry.ClassEntry;
import com.jonathanedgecombe.osrsclient.reflection.entry.FieldEntry;
import com.jonathanedgecombe.osrsclient.reflection.entry.StaticFieldEntry;

public final class ReflectionHooks {
	private final static ClassEntry CANVAS_ENTRY_PARENT_CLASS = new ClassEntry("av");
	private final static StaticFieldEntry<?> CANVAS_ENTRY = new StaticFieldEntry<>(CANVAS_ENTRY_PARENT_CLASS, "ac");

	private final static ClassEntry CANVAS_CLASS = new ClassEntry("bl");
	private final static FieldEntry<Component, ?> COMPONENT_FIELD = new FieldEntry<>(CANVAS_CLASS, CANVAS_ENTRY, "c");

	public static void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		CANVAS_ENTRY_PARENT_CLASS.init();
		CANVAS_ENTRY.init();

		CANVAS_CLASS.init();
		COMPONENT_FIELD.init();
	}

	public static Component getCanvasParent() {
		return COMPONENT_FIELD.get();
	}

	public static void setCanvasParent(Component component) {
		COMPONENT_FIELD.set(component);
	}
}
