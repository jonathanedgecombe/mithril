package com.mithrilclient.reflection.entry;

public abstract class Entry {
	public abstract void init() throws NoSuchFieldException, SecurityException, ClassNotFoundException;
}
