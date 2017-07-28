package com.mithrilclient.updater;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public final class Application implements Iterable<ClassNode> {
	public static Application readJar(Path path) throws IOException {
		Application application = new Application();

		try (JarInputStream in = new JarInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
			JarEntry entry;
			while ((entry = in.getNextJarEntry()) != null) {
				String name = entry.getName();
				if (!name.endsWith(".class")) continue;

				name = name.replaceAll(".class$", "");

				ClassNode node = new ClassNode();
				ClassReader reader = new ClassReader(in);
				reader.accept(node, ClassReader.SKIP_DEBUG);

				application.classes.put(name, node);
			}
		}

		return application;
	}

	private final Map<String, ClassNode> classes = new HashMap<>();

	@Override
	public Iterator<ClassNode> iterator() {
		return classes.values().iterator();
	}
}
