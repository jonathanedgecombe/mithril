package com.mithrilclient.reflection.entry;

public final class ObfuscatedIntInstanceFieldEntry extends InstanceFieldEntry<Integer> {
	private final int setMultiplier, getMultiplier;

	public ObfuscatedIntInstanceFieldEntry(ClassEntry classEntry, String fieldName, int setMultiplier, int getMultiplier) {
		super(classEntry, fieldName);

		this.setMultiplier = setMultiplier;
		this.getMultiplier = getMultiplier;

		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <U> Integer get(U instance) throws IllegalArgumentException {
		try {
			return ((Integer) field.get(instance)) * getMultiplier;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public <U> void set(U instance, Integer value) throws IllegalArgumentException {
		try {
			field.set(instance, value * setMultiplier);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
