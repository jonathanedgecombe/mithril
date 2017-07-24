package com.mithrilclient.reflection.entry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class StaticMethodEntry<T> extends Entry {
	private final ClassEntry classEntry;
	private final String methodName;
	private final Class<?>[] parameterTypes;

	private Method method;

	public StaticMethodEntry(ClassEntry classEntry, String methodName, Class<?>... parameterTypes) {
		this.classEntry = classEntry;
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;

		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() throws Exception {
		method = classEntry.get().getDeclaredMethod(methodName, parameterTypes);
		method.setAccessible(true);
	}

	@SuppressWarnings("unchecked")
	public T call(Object... params) {
		try {
			return (T) method.invoke(null, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
