package com.jonathanedgecombe.osrsclient.module;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.jonathanedgecombe.osrsclient.reflection.ReflectionHooks;
import com.jonathanedgecombe.osrsclient.util.Skills;

public final class XPTrackerModule extends Module {
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
	public synchronized void paint(Graphics2D g) {
		if (lastSkillTrained == -1) return;

		int[] levels = ReflectionHooks.getBaseSkills();

		int currentLevel = levels[lastSkillTrained];
		int startXp = Skills.XP_THRESHOLDS[currentLevel - 1];
		int endXp = Skills.XP_THRESHOLDS[currentLevel == 99 ? 99 : currentLevel];

		int range = endXp - startXp;
		int delta = lastXpLevels[lastSkillTrained] - startXp;

		double percent = (double) (delta * 100) / range;

		g.setComposite(AlphaComposite.SrcOver.derive(0.67f));

		g.setColor(Color.GRAY);
		g.fillRect(X, Y, 109, 38);
		g.setColor(Color.WHITE);
		g.drawRect(X, Y, 109, 38);

		g.setComposite(AlphaComposite.SrcOver);

		g.drawString(Skills.SKILL_NAMES[lastSkillTrained], X + 4, Y + 14);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(X + 5, Y + 20, 100, 14);

		g.setColor(Color.WHITE);
		g.fillRect(X + 5, Y + 20, (int) percent, 14);
	}
}
