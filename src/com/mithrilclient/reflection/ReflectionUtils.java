package com.mithrilclient.reflection;

import java.lang.reflect.Field;

public final class ReflectionUtils {
	public static Class<?> loadClass(String name) throws ClassNotFoundException {
		return ReflectionUtils.class.getClassLoader().loadClass(name);
	}

	public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException, SecurityException {
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		return field;
	}
}
