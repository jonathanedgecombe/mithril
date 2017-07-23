package com.mithrilclient.resource;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public final class Image {
	public static BufferedImage load(String name) {
		URL url = Image.class.getResource("/resource/" + name);
		try (InputStream in = new BufferedInputStream(url == null ? Files.newInputStream(Paths.get("resource/" + name)) : url.openStream())) {
			return ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public final static BufferedImage XP_TRACKER_OVERLAY = load("xp-tracker-overlay.png");
}
