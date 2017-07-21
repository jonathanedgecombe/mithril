package com.jonathanedgecombe.osrsclient.reflection.entry;

import com.jonathanedgecombe.osrsclient.reflection.ReflectionUtils;

public final class ClassEntry extends Entry {
	private final String className;
	private Class<?> clazz;

	public ClassEntry(String className) {
		this.className = className;
	}

	public void init() throws ClassNotFoundException {
		clazz = ReflectionUtils.loadClass(className);
	}

	public Class<?> get() {
		return clazz;
	}
}
