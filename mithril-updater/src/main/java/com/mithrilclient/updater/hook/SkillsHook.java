package com.mithrilclient.updater.hook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;

import com.mithrilclient.updater.regex.Instruction;
import com.mithrilclient.updater.regex.Or;
import com.mithrilclient.updater.regex.Regex;

public final class SkillsHook extends Hook {
	public SkillsHook() {
		super(new Regex(
				new Instruction(Opcodes.GETSTATIC, true),
				new Instruction(Opcodes.ALOAD, false),
				new Instruction(Opcodes.ILOAD, false),
				new Instruction(Opcodes.IINC, false),
				new Instruction(Opcodes.IALOAD, false),
				new Instruction(Opcodes.IALOAD, false),
				new Instruction(Opcodes.ISTORE, false),
				new Instruction(Opcodes.ILOAD, false),
				new Or(new Instruction(Opcodes.ICONST_2, false), new Instruction(Opcodes.IF_ICMPNE, false), false),
				new Or(new Instruction(Opcodes.ICONST_2, false), new Instruction(Opcodes.IF_ICMPNE, false), false),
				new Instruction(Opcodes.GETSTATIC, true),
				new Instruction(Opcodes.ALOAD, false),
				new Instruction(Opcodes.ILOAD, false),
				new Instruction(Opcodes.IINC, false),
				new Instruction(Opcodes.IALOAD, false),
				new Instruction(Opcodes.IALOAD, false),
				new Instruction(Opcodes.ISTORE, false),
				new Or(new Instruction(Opcodes.ILOAD, false), new Instruction(Opcodes.ICONST_3, false), false),
				new Or(new Instruction(Opcodes.ILOAD, false), new Instruction(Opcodes.ICONST_3, false), false),
				new Instruction(Opcodes.IF_ICMPNE, false),
				new Instruction(Opcodes.GETSTATIC, true)
			));
	}

	@Override
	public void write(BufferedWriter writer, ClassNode clazz, List<AbstractInsnNode> match) throws IOException {
		writer.write("		xpLevelsHook = new FieldHook<>(classLoader, \"" + clazz.name + "\", \"" + ((FieldInsnNode) match.get(2)).name + "\");\n");
		writer.write("		levelsHook = new FieldHook<>(classLoader, \"" + clazz.name + "\", \"" + ((FieldInsnNode) match.get(0)).name + "\");\n");
		writer.write("		baseLevelsHook = new FieldHook<>(classLoader, \"" + clazz.name + "\", \"" + ((FieldInsnNode) match.get(1)).name + "\");\n");
		writer.write("\n");
	}
}
