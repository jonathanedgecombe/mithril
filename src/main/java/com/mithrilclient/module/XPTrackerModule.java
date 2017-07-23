package com.mithrilclient.module;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.mithrilclient.gui.MithrilMenuBar.XPTrackerMenuItem;
import com.mithrilclient.reflection.ReflectionHooks;
import com.mithrilclient.resource.Image;
import com.mithrilclient.util.Skills;

public final class XPTrackerModule extends Module {
	private final static Color GREEN = new Color(0, 127, 0);

	@Override
	public synchronized void paint(Graphics2D g, int width, int height) {
		int x = 5, y = 21;

		int[] levels = ReflectionHooks.getBaseSkills();
		int[] xpLevels = ReflectionHooks.getXpLevels();

		long total = 0;
		for (int skill = 0; skill < Skills.NUM_SKILLS; skill++) {
			total += xpLevels[skill];
		}

		if (total == 0) return;

		for (int skill = 0; skill < Skills.NUM_SKILLS; skill++) {
			if (!XPTrackerMenuItem.XP_TRACKERS.get(skill).isSelected()) continue;

			int currentLevel = levels[skill];
			int startXp = Skills.XP_THRESHOLDS[currentLevel - 1];
			int endXp = Skills.XP_THRESHOLDS[currentLevel == 99 ? 99 : currentLevel];
	
			int range = endXp - startXp;
			int delta = xpLevels[skill] - startXp;
	
			double percent = (double) (delta * 100) / range;
	
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x + 11, y + 28, 100, 12);
	
			g.setColor(GREEN);
			g.fillRect(x + 11, y + 28, (int) percent, 12);
	
			g.drawImage(Image.XP_TRACKER_OVERLAY, x, y, null);
	
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			g.setFont(g.getFont().deriveFont(12f));
	
			g.setColor(Color.BLACK);
			g.drawString(Skills.SKILL_NAMES[skill], x + 12, y + 22);
			g.setColor(Color.WHITE);
			g.drawString(Skills.SKILL_NAMES[skill], x + 11, y + 21);
	
			g.setFont(g.getFont().deriveFont(11f));
	
			String progress = (Math.round(percent)) + "%";
			int offset = g.getFontMetrics().stringWidth(progress) / 2;
	
			g.setColor(Color.BLACK);
			g.drawString(progress, x + 62 - offset, y + 39);
			g.setColor(Color.WHITE);
			g.drawString(progress, x + 61 - offset, y + 38);

			y += 56;
		}
	}
}
