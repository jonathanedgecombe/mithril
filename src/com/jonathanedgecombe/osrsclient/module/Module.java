package com.jonathanedgecombe.osrsclient.module;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Module {
	public abstract void tick();

	public abstract void paint(Graphics2D g, int width, int height);

	public static void init(List<Module> modules) {
		modules.add(new XPTrackerModule());
		modules.add(new PrayerPotionModule());
	}
}
