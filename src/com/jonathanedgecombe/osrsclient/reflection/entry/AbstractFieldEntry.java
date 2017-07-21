package com.jonathanedgecombe.osrsclient.reflection.entry;

public abstract class AbstractFieldEntry<T> extends Entry {
	public abstract T get() throws IllegalArgumentException, IllegalAccessException;

	public abstract void set(T value) throws IllegalArgumentException;
}
