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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mithrilclient.gui.MithrilMenuBar;
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
	private boolean firstTick = true;

	public MithrilClient() {
		super(TITLE);

		stub = new Stub(this, getContentPane());
		setJMenuBar(new MithrilMenuBar());

		pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void start() {
		Module.initModules(modules);
		stub.start();
		setInitialized();
	}

	private synchronized void setInitialized() {
		initialized = true;
	}

	public synchronized boolean isInitialized() {
		return initialized;
	}

	public void tick(final ReflectionHooks hooks, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int width = stub.getWidth();
		int height = stub.getHeight();

		final MithrilClient client = this;

		try {
			if (firstTick) {
				for (Module module : modules) {
					module.init(hooks);
				}
	
				firstTick = false;
			}
	
			for (Module module : modules) {
				module.paint(hooks, g2d, width, height);
				executor.execute(() -> {
					try {
						module.tick(hooks);
					} catch (RuntimeException e) {
						client.handle(e);
					}
				});
			}
		} catch (RuntimeException e) {
			handle(e);
		}
	}

	public synchronized void handle(Throwable e) {
		e.printStackTrace();
		System.exit(0);
		// TODO
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// do nothing
		}

		new MithrilClient().start();
	}
}
