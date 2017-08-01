package com.mithrilclient.updater.hook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import com.mithrilclient.updater.regex.Instruction;
import com.mithrilclient.updater.regex.InvokeVirtualInstruction;
import com.mithrilclient.updater.regex.Regex;

public final class CanvasHook extends Hook {
	public CanvasHook() {
		super(new Regex(
				new InvokeVirtualInstruction("java/awt/Canvas.addFocusListener:(Ljava/awt/event/FocusListener;)V", false),
				new Instruction(Opcodes.GOTO, false),
				new Instruction(Opcodes.GETSTATIC, true),
				new Instruction(Opcodes.CHECKCAST, false),
				new Instruction(Opcodes.ALOAD, false),
				new Instruction(Opcodes.GETFIELD, false),
				new Instruction(Opcodes.LDC, false),
				new Instruction(Opcodes.INVOKEVIRTUAL, true)
			));
	}

	@Override
	public void write(BufferedWriter writer, ClassNode clazz, List<AbstractInsnNode> match) throws IOException {
		writer.write("		canvasHook = new FieldHook<>(classLoader, \"" + ((FieldInsnNode) match.get(0)).owner +  "\", \"" + ((FieldInsnNode) match.get(0)).name + "\");\n");
		writer.write("		componentHook = new FieldHook<>(classLoader, \"" + ((MethodInsnNode) match.get(1)).owner + "\", \"" + ((MethodInsnNode) match.get(1)).name + "\");\n");
		writer.write("\n");
	}
}
