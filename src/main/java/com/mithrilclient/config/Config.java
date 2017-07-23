package com.mithrilclient.config;

public final class Config {
	private String defaultUsername;

	public Config(String defaultUsername) {
		this.defaultUsername = defaultUsername;
	}

	public String getDefaultUsername() {
		return defaultUsername;
	}

	public void setDefaultUsername(String defaultUsername) {
		this.defaultUsername = defaultUsername;
	}
}
