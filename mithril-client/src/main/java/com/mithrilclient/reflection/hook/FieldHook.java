package com.mithrilclient.reflection.hook;

import java.lang.reflect.Field;

public class FieldHook<T> {
	private final Field field;

	public FieldHook(ClassLoader classLoader, String className, String fieldName) {
		try {
			field = classLoader.loadClass(className).getDeclaredField(fieldName);
			field.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <U> T get(U instance) {
		try {
			return (T) field.get(instance);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public T getStatic() {
		return get(null);
	}

	public <U> void set(U instance, T value) {
		try {
			field.set(instance, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
