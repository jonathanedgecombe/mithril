package com.mithrilclient.updater.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;

public final class Regex {
	private final List<Instruction> list;

	public Regex(Instruction... instructions) {
		this.list = Arrays.asList(instructions);
	}

	public Optional<List<AbstractInsnNode>> match(MethodNode method) {
		AbstractInsnNode[] instructions = method.instructions.toArray();

		List<AbstractInsnNode> match = new ArrayList<>();
		outer: for (int i = 0; i < instructions.length; i++) {
			if (i + list.size() > instructions.length) return Optional.empty();

			match.clear();
			for (int j = 0, skip = 0; j < list.size(); j++) {
				if (i + j + skip >= instructions.length) return Optional.empty();

				AbstractInsnNode instruction = instructions[i + j + skip];

				if (instruction instanceof LabelNode || instruction instanceof FrameNode) {
					skip++;
					j--;
					continue;
				}

				Instruction regexInstruction = list.get(j);

				if (!regexInstruction.matches(instruction)) continue outer;
				if (regexInstruction.toCapture()) match.add(instruction);
			}

			return Optional.of(match);
		}

		return Optional.empty();
	}
}
