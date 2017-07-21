package com.jonathanedgecombe.osrsclient.util;

public final class Skills {
	public final static String[] SKILL_NAMES = {
			"Attack",
			"Defence",
			"Strength",
			"Hitpoints",
			"Ranged",
			"Prayer",
			"Magic",
			"Cooking",
			"Woodcutting",
			"Fletching",
			"Fishing",
			"Firemaking",
			"Crafting",
			"Smithing",
			"Mining",
			"Herblore",
			"Agility",
			"Thieving",
			"Slayer",
			"Farming",
			"Runecrafting",
			"Hunter",
			"Construction"
	};

	public final static int NUM_SKILLS = SKILL_NAMES.length;

	public final static int[] XP_THRESHOLDS = new int[100];

	static {
		int total = 0;
		for (int level = 0; level < 99; level++) {
			int nextLevel = level + 1;
			int delta = (int) ((double) nextLevel + 300D * Math.pow(2D, (double) nextLevel / 7D));
			total += delta;
			XP_THRESHOLDS[level + 1] = total / 4;
		}
	}
}
