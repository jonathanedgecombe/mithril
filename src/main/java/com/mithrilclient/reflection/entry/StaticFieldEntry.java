package com.mithrilclient.reflection.entry;

import java.lang.reflect.Field;

import com.mithrilclient.reflection.ReflectionUtils;

public class StaticFieldEntry<T> extends AbstractFieldEntry<T> {
	private final ClassEntry classEntry;
	private final String fieldName;
	protected Field field;

	public StaticFieldEntry(ClassEntry classEntry, String fieldName) {
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
	public T get() throws IllegalArgumentException {
		try {
			return (T) field.get(null);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void set(T value) throws IllegalArgumentException {
		try {
			field.set(null, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
