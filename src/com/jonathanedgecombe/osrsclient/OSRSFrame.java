package com.jonathanedgecombe.osrsclient;

import javax.swing.JFrame;

import com.jonathanedgecombe.osrsclient.reflection.ReflectionHooks;

@SuppressWarnings("serial")
public final class OSRSFrame extends JFrame {
	private final static String TITLE = "OSRS Client";

	private final Stub stub;

	public OSRSFrame() {
		super(TITLE);

		stub = new Stub(getContentPane());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void start() {
		stub.start();

		try {
			ReflectionHooks.init();
		} catch (Exception e) {
			// TODO Handle this properly
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		new OSRSFrame().start();
	}
}
