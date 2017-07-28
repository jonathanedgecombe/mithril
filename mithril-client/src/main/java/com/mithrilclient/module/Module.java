package com.mithrilclient.module;

import java.awt.Graphics2D;
import java.util.List;

import com.mithrilclient.reflection.ReflectionHooks;

public abstract class Module {
	public void init(ReflectionHooks hooks) {
		// do nothing
	}

	public void tick(ReflectionHooks hooks) {
		// do nothing
	}

	public void paint(ReflectionHooks hooks, Graphics2D g, int width, int height) {
		// do nothing
	}

	public static void initModules(List<Module> modules) {
		modules.add(new XPTrackerModule());
		modules.add(new PrayerPotionModule());
		modules.add(new LoginDetailsModule());
		modules.add(new GroundItemModule());
	}
}
