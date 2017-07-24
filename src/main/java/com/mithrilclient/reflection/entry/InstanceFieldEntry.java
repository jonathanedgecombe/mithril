package com.mithrilclient.reflection.entry;

import java.lang.reflect.Field;

import com.mithrilclient.reflection.ReflectionUtils;

public class InstanceFieldEntry<T> {
	private final ClassEntry classEntry;
	private final String fieldName;
	protected Field field;

	public InstanceFieldEntry(ClassEntry classEntry, String fieldName) {
		this.classEntry = classEntry;
		this.fieldName = fieldName;

		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Class<?> clazz = classEntry.get();
		field = ReflectionUtils.getField(clazz, fieldName);
	}

	@SuppressWarnings("unchecked")
	public <U> T get(U instance) throws IllegalArgumentException {
		try {
			return (T) field.get(instance);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public <U> void set(U instance, T value) throws IllegalArgumentException {
		try {
			field.set(instance, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
