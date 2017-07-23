package com.mithrilclient.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.mithrilclient.util.Skills;

@SuppressWarnings("serial")
public final class MithrilMenuBar extends JMenuBar {
	public MithrilMenuBar() {
		init();
	}

	public void init() {
		JMenu xpTrackersMenu = new JMenu("XP Trackers");

		for (int skill = 0; skill < Skills.NUM_SKILLS; skill++) {
			xpTrackersMenu.add(new XPTrackerMenuItem(skill));
		}

		add(xpTrackersMenu);
	}

	public final static class XPTrackerMenuItem extends JCheckBoxMenuItem {
		public final static Map<Integer, XPTrackerMenuItem> XP_TRACKERS = new HashMap<>();

		public XPTrackerMenuItem(int skill) {
			setText(Skills.SKILL_NAMES[skill]);
			XP_TRACKERS.put(skill, this);
		}
	}
}
