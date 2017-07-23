package com.mithrilclient.module;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Module {
	public Module() {
		init();
	}

	public void init() {
		// do nothing
	}

	public void tick() {
		// do nothing
	}

	public void paint(Graphics2D g, int width, int height) {
		// do nothing
	}

	public static void init(List<Module> modules) {
		modules.add(new XPTrackerModule());
		modules.add(new PrayerPotionModule());
	}
}
