package com.mithrilclient;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.mithrilclient.module.Module;
import com.mithrilclient.reflection.ReflectionHooks;

@SuppressWarnings("serial")
public final class MithrilClient extends JFrame {
	public final static String TITLE = "Mithril";
	public final static Path CONFIG_PATH = Paths.get("config.json");

	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), 16, TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

	private final Stub stub;
	private final List<Module> modules = new ArrayList<>();

	private boolean initialized = false;

	public MithrilClient() {
		super(TITLE);

		stub = new Stub(this, getContentPane());

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void start() {
		Module.initModules(modules);
		stub.start();

		try {
			ReflectionHooks.init();

			for (Module module : modules) {
				module.init();
			}

			setInitialized();
		} catch (Exception e) {
			// TODO Handle this properly
			throw new RuntimeException(e);
		}
	}

	private synchronized void setInitialized() {
		initialized = true;
	}

	public synchronized boolean isInitialized() {
		return initialized;
	}

	public void tick(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int width = stub.getWidth();
		int height = stub.getHeight();

		for (Module module : modules) {
			module.paint(g2d, width, height);
			executor.execute(() -> module.tick());
		}
	}

	public static void main(String[] args) {
		new MithrilClient().start();
	}
}
