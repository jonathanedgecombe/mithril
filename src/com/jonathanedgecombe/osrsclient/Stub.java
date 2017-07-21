package com.jonathanedgecombe.osrsclient;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public final class Stub extends JPanel implements AppletStub {
	private final static String BASE_URL = "http://oldschool1.runescape.com/";
	private final static String CONFIG_URL = BASE_URL + "jav_config.ws";

	private final static int DEFAULT_WIDTH = 765, DEFAULT_HEIGHT = 503;
	private final static Dimension DEFAULT_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	private final Map<String, String> parameters = new HashMap<>();
	private final Applet client;

	public Stub(Container container) {
		try {
			fetchConfig();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO handle properly
		}

		try {
			client = (Applet) Stub.class.getClassLoader().loadClass("client").newInstance();
			client.setStub(this);

			client.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			client.setMinimumSize(DEFAULT_DIMENSION);
			client.setPreferredSize(DEFAULT_DIMENSION);

			setLayout(new BorderLayout());
			add(client, BorderLayout.CENTER);

			container.setLayout(new BorderLayout());
			container.add(this, BorderLayout.CENTER);

			container.addComponentListener(new ComponentListener() {
				
				@Override
				public void componentShown(ComponentEvent e) {}
				
				@Override
				public void componentResized(ComponentEvent e) {
					appletResize(container.getWidth(), container.getHeight());
				}

				@Override
				public void componentMoved(ComponentEvent e) {}
				
				@Override
				public void componentHidden(ComponentEvent e) {}
			});
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		client.init();
		client.start();

		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(20);

					Field canvasField = loadClass("av").getDeclaredField("ac");
					canvasField.setAccessible(true);
					Object canvas = canvasField.get(null);

					Field componentField = loadClass("bl").getDeclaredField("c");
					componentField.setAccessible(true);
					Component parent = (Component) componentField.get(canvas);

					if (!(parent instanceof CanvasWrapper)) {
						CanvasWrapper wrapper = new CanvasWrapper(parent);
						componentField.set(canvas, wrapper);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}).start();
	}

	public static Class<?> loadClass(String name) throws Exception {
		return Stub.class.getClassLoader().loadClass(name);
	}

	public void fetchConfig() throws MalformedURLException, IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(CONFIG_URL).openStream()))) {
			while (true) {
				String line = reader.readLine();
				if (line == null) break;

				String[] parts = line.split("=", 3);
				switch (parts[0]) {
				case "param":
					String key = parts[1];
					String value = parts.length == 3 ? parts[2] : "";

					parameters.put(key, value);
					break;
				}
			}
		}
	}

	@Override
	public void appletResize(int width, int height) {
		client.setSize(width, height);
	}

	@Override
	public AppletContext getAppletContext() {
		System.out.println("getAppletContext");
		return null;
	}

	@Override
	public URL getCodeBase() {
		return getDocumentBase();
	}

	@Override
	public URL getDocumentBase() {
		try {
			return new URL(BASE_URL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}

	@Override
	public boolean isActive() {
		return true;
	}
}
