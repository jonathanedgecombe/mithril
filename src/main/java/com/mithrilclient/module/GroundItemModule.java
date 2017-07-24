package com.mithrilclient.module;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import com.mithrilclient.model.GroundItem;
import com.mithrilclient.model.Vector2i;
import com.mithrilclient.reflection.ReflectionHooks;

public final class GroundItemModule extends Module {
	@Override
	public void paint(Graphics2D g, int width, int height) {
		List<GroundItem> items = ReflectionHooks.getGroundItems();

		g.setFont(g.getFont().deriveFont(11f));
		for (GroundItem item : items) {
			Vector2i pos = ReflectionHooks.project(item.getX() + 0.5f, item.getY() + 0.5f, ReflectionHooks.getPlayerHeightLevel());
			if (pos == null) continue;

			String name = ReflectionHooks.getItemName(ReflectionHooks.getItemDef(item.getId()));
			String text = name + (item.getQuantity() > 1 ? " x" + item.getQuantity() : "");
			int offset = g.getFontMetrics().stringWidth(text) / 2;

			g.setColor(Color.BLACK);
			g.drawString(text, pos.getX() - offset + 1, pos.getY() + 1);
			g.setColor(Color.WHITE);
			g.drawString(text, pos.getX() - offset, pos.getY());
		}
	}
}
