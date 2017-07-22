package com.mithrilclient.reflection.entry;

import com.mithrilclient.reflection.ReflectionUtils;

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