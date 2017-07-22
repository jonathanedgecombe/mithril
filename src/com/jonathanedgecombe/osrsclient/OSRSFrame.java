package com.jonathanedgecombe.osrsclient;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.jonathanedgecombe.osrsclient.module.Module;
import com.jonathanedgecombe.osrsclient.reflection.ReflectionHooks;

@SuppressWarnings("serial")
public final class OSRSFrame extends JFrame {
	private final static String TITLE = "OSRS Client";

	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(), 16, TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

	private final Stub stub;
	private final List<Module> modules = new ArrayList<>();

	public OSRSFrame() {
		super(TITLE);

		stub = new Stub(this, getContentPane());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void start() {
		Module.init(modules);
		stub.start();

		try {
			ReflectionHooks.init();
		} catch (Exception e) {
			// TODO Handle this properly
			throw new RuntimeException(e);
		}
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
		new OSRSFrame().start();
	}
}
