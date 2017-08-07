package com.mithrilclient.updater;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.mithrilclient.updater.hook.CanvasHook;
import com.mithrilclient.updater.hook.Hook;
import com.mithrilclient.updater.hook.SkillsHook;

public final class Updater {
	private final static Path CLIENT_PATH = Paths.get("../mithril-client/lib/client.jar");
	private final static Path OUTPUT_PATH = Paths.get("../mithril-client/src/main/java/com/mithrilclient/reflection/ReflectionHooks2.java");

	public static void main(String[] args) throws IOException {
		new Updater(CLIENT_PATH, OUTPUT_PATH).update();
	}

	private final Application application;
	private final List<Hook> hooks = new ArrayList<>();
	private final Path outputPath;

	public Updater(Path clientPath, Path outputPath) throws IOException {
		this.application = Application.readJar(clientPath);
		this.outputPath = outputPath;

		init();
	}

	private void init() {
		hooks.add(new SkillsHook());
		hooks.add(new CanvasHook());
	}

	public void update() throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))/*Files.newBufferedWriter(outputPath)*/) {
			writeHeader(writer);

			for (Iterator<ClassNode> it = application.iterator(); it.hasNext();) {
				final ClassNode clazz = it.next();
				if (hooks.isEmpty()) break;

				for (MethodNode method : clazz.methods) {
					for (Iterator<Hook> hit = hooks.iterator(); hit.hasNext();) {
						Hook hook = hit.next();
						if (hook.match(clazz, method, writer)) hit.remove();
					}
				}
			}

			writeFooter(writer);
		}

		if (!hooks.isEmpty()) {
			System.err.println(hooks);
			throw new RuntimeException("Unconsumed hooks");
		}
	}

	public void writeHeader(BufferedWriter writer) throws IOException {
		writer.write("/*\n");
	}

	public void writeFooter(BufferedWriter writer) throws IOException {
		writer.write("*/\n");
	}
}
