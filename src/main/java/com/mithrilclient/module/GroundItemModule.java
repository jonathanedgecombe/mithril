package com.mithrilclient.module;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import com.mithrilclient.model.GroundItem;
import com.mithrilclient.model.Vector2i;
import com.mithrilclient.reflection.ReflectionHooks;

public final class GroundItemModule extends Module {
	@Override
	public void paint(ReflectionHooks hooks, Graphics2D g, int width, int height) {
		List<GroundItem> items = hooks.getGroundItems();

		g.setFont(g.getFont().deriveFont(11f));
		for (GroundItem item : items) {
			Vector2i pos = hooks.project(item.getX() + 0.5f, item.getY() + 0.5f, hooks.getPlayerHeightLevel());
			if (pos == null) continue;

			String name = hooks.getItemName(hooks.getItemDef(item.getId()));
			String text = name + (item.getQuantity() > 1 ? " x" + item.getQuantity() : "");
			int offset = g.getFontMetrics().stringWidth(text) / 2;

			g.setColor(Color.BLACK);
			g.drawString(text, pos.getX() - offset + 1, pos.getY() + 1);
			g.setColor(Color.WHITE);
			g.drawString(text, pos.getX() - offset, pos.getY());
		}
	}
}
