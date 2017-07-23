package com.mithrilclient.config;

public final class Config {
	private String defaultUsername;
	private int defaultWorld;

	public Config(String defaultUsername, int defaultWorld) {
		this.defaultUsername = defaultUsername;
		this.setDefaultWorld(defaultWorld);
	}

	public String getDefaultUsername() {
		return defaultUsername;
	}

	public void setDefaultUsername(String defaultUsername) {
		this.defaultUsername = defaultUsername;
	}

	public int getDefaultWorld() {
		return defaultWorld == 0 ? 1 : defaultWorld;
	}

	public void setDefaultWorld(int defaultWorld) {
		this.defaultWorld = defaultWorld;
	}
}
