package com.jonathanedgecombe.osrsclient.reflection.entry;

import java.lang.reflect.Field;

import com.jonathanedgecombe.osrsclient.reflection.ReflectionUtils;

public final class FieldEntry<T, U> extends AbstractFieldEntry<T> {
	private final ClassEntry classEntry;
	private final AbstractFieldEntry<U> instance;
	private final String fieldName;
	private Field field;

	public FieldEntry(ClassEntry classEntry, AbstractFieldEntry<U> instance, String fieldName) {
		this.classEntry = classEntry;
		this.instance = instance;
		this.fieldName = fieldName;
	}

	public void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Class<?> clazz = classEntry.get();
		field = ReflectionUtils.getField(clazz, fieldName);
	}

	@SuppressWarnings("unchecked")
	public T get() throws IllegalArgumentException {
		try {
			return (T) field.get(instance.get());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void set(T value) throws IllegalArgumentException {
		try {
			field.set(instance.get(), value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
