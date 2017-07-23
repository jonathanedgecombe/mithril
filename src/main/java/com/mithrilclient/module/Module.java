package com.mithrilclient.module;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Module {
	public void init() {
		// do nothing
	}

	public void tick() {
		// do nothing
	}

	public void paint(Graphics2D g, int width, int height) {
		// do nothing
	}

	public static void initModules(List<Module> modules) {
		modules.add(new XPTrackerModule());
		modules.add(new PrayerPotionModule());
		modules.add(new LoginDetailsModule());
	}
}
