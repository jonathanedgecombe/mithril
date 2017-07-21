package com.jonathanedgecombe.osrsclient.module;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Module {
	public abstract void tick();

	public abstract void paint(Graphics2D g);

	public static void init(List<Module> modules) {
		modules.add(new XPTrackerModule());
	}
}
