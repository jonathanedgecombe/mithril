package com.mithrilclient.module;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.mithrilclient.reflection.ReflectionHooks;
import com.mithrilclient.resource.Image;
import com.mithrilclient.util.Skills;

public final class XPTrackerModule extends Module {
	private final static Color GREEN = new Color(0, 127, 0);

	private final static int X = 5, Y = 21;

	private int[] lastXpLevels = new int[Skills.NUM_SKILLS];
	private long lastTotal = 0;
	private int lastSkillTrained = -1;

	@Override
	public synchronized void tick() {
		int[] xpLevels = ReflectionHooks.getXpLevels();

		long total = 0;
		for (int skill = 0; skill < Skills.NUM_SKILLS; skill++) {
			total += xpLevels[skill];
		}

		if (total == 0) return;
		if (lastTotal == 0 || total - lastTotal == 0) {
			System.arraycopy(xpLevels, 0, lastXpLevels, 0, Skills.NUM_SKILLS);
			lastTotal = total;
			return;
		}

		for (int skill = 0; skill < Skills.NUM_SKILLS; skill++) {
			int delta = xpLevels[skill] - lastXpLevels[skill];

			if (delta > 0) {
				lastSkillTrained = skill;
				System.out.println("+" + delta + "xp in " + Skills.SKILL_NAMES[skill]);
			}
		}

		System.arraycopy(xpLevels, 0, lastXpLevels, 0, Skills.NUM_SKILLS);
		lastTotal = total;
	}

	@Override
	public synchronized void paint(Graphics2D g, int width, int height) {
		if (lastSkillTrained == -1) return;

		int[] levels = ReflectionHooks.getBaseSkills();

		int currentLevel = levels[lastSkillTrained];
		int startXp = Skills.XP_THRESHOLDS[currentLevel - 1];
		int endXp = Skills.XP_THRESHOLDS[currentLevel == 99 ? 99 : currentLevel];

		int range = endXp - startXp;
		int delta = lastXpLevels[lastSkillTrained] - startXp;

		double percent = (double) (delta * 100) / range;

		g.setColor(Color.DARK_GRAY);
		g.fillRect(X + 11, Y + 28, 100, 12);

		g.setColor(GREEN);
		g.fillRect(X + 11, Y + 28, (int) percent, 12);

		g.drawImage(Image.XP_TRACKER_OVERLAY, X, Y, null);

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		g.setFont(g.getFont().deriveFont(12f));

		g.setColor(Color.BLACK);
		g.drawString(Skills.SKILL_NAMES[lastSkillTrained], X + 12, Y + 22);
		g.setColor(Color.WHITE);
		g.drawString(Skills.SKILL_NAMES[lastSkillTrained], X + 11, Y + 21);

		g.setFont(g.getFont().deriveFont(11f));

		String progress = (Math.round(percent)) + "%";
		int offset = g.getFontMetrics().stringWidth(progress) / 2;

		g.setColor(Color.BLACK);
		g.drawString(progress, X + 62 - offset, Y + 39);
		g.setColor(Color.WHITE);
		g.drawString(progress, X + 61 - offset, Y + 38);
	}
}
