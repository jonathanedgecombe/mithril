package com.mithrilclient.util;

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

	public final static int 
	ATTACK = 0, 
	DEFENCE = 1, 
	STRENGTH = 2, 
	HITPOINTS = 3, 
	RANGED = 4, 
	PRAYER = 5, 
	MAGIC = 6, 
	COOKING = 7, 
	WOODCUTTING = 8, 
	FLETCHING = 9, 
	FISHING = 10, 
	FIREMAKING = 11, 
	CRAFTING = 12, 
	SMITHING = 13, 
	MINING = 14, 
	HERBLORE = 15, 
	AGILITY = 16, 
	THIEVING = 17, 
	SLAYER = 18, 
	FARMING = 19, 
	RUNECRAFTING = 20, 
	HUNTER = 21, 
	CONSTRUCTION = 22;

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
