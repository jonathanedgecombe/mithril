package com.mithrilclient.reflection.hook;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MethodHook<T> {
	private final Method method;

	public MethodHook(ClassLoader classLoader, String className, String methodName, Class<?>... parameterTypes) {
		try {
			method = classLoader.loadClass(className).getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
		} catch (SecurityException | ClassNotFoundException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <U> T invoke(U instance, Object... parameters) {
		try {
			Object result = method.invoke(instance, parameters);
			if (result == null) return null;
			return (T) result;
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
