package com.mithrilclient.model;

public final class GroundItem {
	private final int x, y;
	private final int id, quantity;

	public GroundItem(int x, int y, int id, int quantity) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.quantity = quantity;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}
}
