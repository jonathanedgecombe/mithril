package com.mithrilclient.reflection.hook;

public final class ClassHook {
	private final Class<?> clazz;

	public ClassHook(ClassLoader classLoader, String className) {
		try {
			this.clazz = classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Class<?> get() {
		return clazz;
	}
}
