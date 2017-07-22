package com.mithrilclient.module;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.mithrilclient.reflection.ReflectionHooks;
import com.mithrilclient.util.Skills;

public final class PrayerPotionModule extends Module {
	private final static int X = 216, Y = 102;
	private final static String DRINK_MESSAGE = "Drink";

	private final static Color PRAYER_POTION_COLOUR = new Color(80, 210, 165);
	private final static Color SUPER_RESTORE_POTION_COLOUR = new Color(170, 50, 100);

	@Override
	public void tick() {
		
	}

	@Override
	public void paint(Graphics2D g, int width, int height) {
		int[] currentLevels = ReflectionHooks.getSkills();
		int[] baseLevels = ReflectionHooks.getBaseSkills();

		int prayer = currentLevels[Skills.PRAYER];
		int basePrayer = baseLevels[Skills.PRAYER];

		if (basePrayer == 0) return;

		int prayerPotion = 7 + (baseLevels[Skills.PRAYER] / 4);
		int superRestore = 8 + (baseLevels[Skills.PRAYER] / 4);

		int delta = basePrayer - prayer;
		if (delta < prayerPotion) return;

		Color color = PRAYER_POTION_COLOUR;
		if (delta >= superRestore) {
			color = SUPER_RESTORE_POTION_COLOUR;
		}

		g.setColor(color);

		g.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		g.fillRect(width - X - 64, Y, 64, 19);

		g.setComposite(AlphaComposite.SrcOver);
		g.drawRect(width - X - 64, Y, 64, 19);

		int stringWidth = g.getFontMetrics().stringWidth(DRINK_MESSAGE);

		g.setColor(Color.BLACK);
		g.drawString(DRINK_MESSAGE, width - X - 31 - (stringWidth / 2), Y + 15);

		g.setColor(color);
		g.drawString(DRINK_MESSAGE, width - X - 32 - (stringWidth / 2), Y + 14);
	}
}
