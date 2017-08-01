package com.mithrilclient.updater.regex;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public final class InvokeVirtualInstruction extends Instruction {
	private final String methodSignature;

	public InvokeVirtualInstruction(String methodSignature, boolean capture) {
		super(Opcodes.INVOKEVIRTUAL, capture);
		this.methodSignature = methodSignature;
	}

	public boolean matches(AbstractInsnNode instruction) {
		if (!super.matches(instruction)) return false;
		MethodInsnNode mn = (MethodInsnNode) instruction;
		return methodSignature.equals(mn.owner + "." + mn.name + ":" + mn.desc);
	}
}
