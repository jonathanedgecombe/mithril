package com.mithrilclient.updater.regex;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class Or extends Instruction {
	private final Instruction a, b;

	public Or(Instruction a, Instruction b, boolean capture) {
		super(Opcodes.NOP, capture);
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean matches(AbstractInsnNode instruction) {
		return a.matches(instruction) || b.matches(instruction);
	}
}
