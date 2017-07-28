package com.mithrilclient.reflection.hook;

public final class ObfuscatedIntHook extends FieldHook<Integer> {
	private final int getMultiplier, setMultiplier;

	public ObfuscatedIntHook(ClassLoader classLoader, String className, String fieldName, int getMultiplier, int setMultiplier) {
		super(classLoader, className, fieldName);

		this.getMultiplier = getMultiplier;
		this.setMultiplier = setMultiplier;
	}

	@Override
	public <U> Integer get(U instance) {
		return super.get(instance) * getMultiplier;
	}

	@Override
	public <U> void set(U instance, Integer value) {
		super.set(instance, value * setMultiplier);
	}
}
