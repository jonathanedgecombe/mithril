package com.mithrilclient.config;

public final class Config {
	private String defaultUsername = "";
	private int defaultWorld = 1;

	public String getDefaultUsername() {
		return defaultUsername;
	}

	public void setDefaultUsername(String defaultUsername) {
		this.defaultUsername = defaultUsername;
	}

	public int getDefaultWorld() {
		return defaultWorld;
	}

	public void setDefaultWorld(int defaultWorld) {
		this.defaultWorld = defaultWorld;
	}
}
