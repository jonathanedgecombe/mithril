package com.mithrilclient.updater.hook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.mithrilclient.updater.regex.Regex;

public abstract class Hook {
	private final Optional<String> className;
	private final Regex regex;

	public Hook(Regex regex) {
		this.className = Optional.empty();
		this.regex = regex;
	}

	public Hook(String className, Regex regex) {
		this.className = Optional.of(className);
		this.regex = regex;
	}

	public boolean matchClass(String name) {
		if (className.isPresent()) {
			return className.get().equals(name);
		}

		return false;
	}

	public boolean match(ClassNode clazz, MethodNode method, BufferedWriter writer) throws IOException {
		Optional<List<AbstractInsnNode>> match = regex.match(method);
		if (match.isPresent()) {
			write(writer, clazz, match.get());
			return true;
		}

		return false;
	}

	public abstract void write(BufferedWriter writer, ClassNode clazz, List<AbstractInsnNode> match) throws IOException;
}
