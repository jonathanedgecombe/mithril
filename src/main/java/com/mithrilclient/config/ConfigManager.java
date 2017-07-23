package com.mithrilclient.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import com.google.gson.Gson;
import com.mithrilclient.MithrilClient;

public final class ConfigManager {
	private final static Config DEFAULT_CONFIG = new Config();

	public static Config CONFIG;

	static {
		try {
			CONFIG = loadConfig();
		} catch (IOException e) {
			CONFIG = DEFAULT_CONFIG;
			try {
				writeConfig(CONFIG);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public static Config loadConfig() throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(MithrilClient.CONFIG_PATH)) {
			return new Gson().fromJson(reader, Config.class);
		}
	}

	public static void writeConfig(Config config) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(MithrilClient.CONFIG_PATH)) {
			new Gson().toJson(config, writer);
		}
	}
}
