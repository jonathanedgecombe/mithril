package com.mithrilclient.updater.regex;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public final class VarInstruction extends Instruction {
	private final int var;

	public VarInstruction(int var, int opcode, boolean capture) {
		super(opcode, capture);
		this.var = var;
	}

	public boolean matches(AbstractInsnNode instruction) {
		if (!super.matches(instruction)) return false;
		return ((VarInsnNode) instruction).var == var;
	}
}
