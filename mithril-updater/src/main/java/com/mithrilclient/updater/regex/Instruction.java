package com.mithrilclient.updater.regex;

import org.objectweb.asm.tree.AbstractInsnNode;

public class Instruction {
	private final int opcode;
	private final boolean capture;

	public Instruction(int opcode, boolean capture) {
		this.opcode = opcode;
		this.capture = capture;
	}

	public int getOpcode() {
		return opcode;
	}

	public boolean toCapture() {
		return capture;
	}

	public boolean matches(AbstractInsnNode instruction) {
		return instruction.getOpcode() == opcode;
	}
}
