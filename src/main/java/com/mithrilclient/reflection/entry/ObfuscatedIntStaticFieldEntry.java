package com.mithrilclient.reflection.entry;

public final class ObfuscatedIntStaticFieldEntry extends StaticFieldEntry<Integer> {
	private final int setMultiplier, getMultiplier;

	public ObfuscatedIntStaticFieldEntry(ClassEntry classEntry, String fieldName, int setMultiplier, int getMultiplier) {
		super(classEntry, fieldName);

		this.setMultiplier = setMultiplier;
		this.getMultiplier = getMultiplier;

		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Integer get() throws IllegalArgumentException {
		try {
			return ((Integer) field.get(null)) * getMultiplier;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void set(Integer value) throws IllegalArgumentException {
		try {
			field.set(null, value * setMultiplier);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
