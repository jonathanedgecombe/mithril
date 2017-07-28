package com.mithrilclient.updater.regex;

import java.util.Optional;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public final class VarInstruction extends Instruction {
	private final Optional<Integer> var;

	public VarInstruction(Optional<Integer> var, int opcode, boolean capture) {
		super(opcode, capture);
		this.var = var;
	}

	public boolean matches(AbstractInsnNode instruction) {
		if (!super.matches(instruction)) return false;

		if (var.isPresent()) {
			return ((VarInsnNode) instruction).var == var.get();
		}

		return true;
	}
}
