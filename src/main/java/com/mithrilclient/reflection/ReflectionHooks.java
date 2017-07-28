package com.mithrilclient.reflection;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.mithrilclient.CanvasWrapper;
import com.mithrilclient.model.GroundItem;
import com.mithrilclient.model.Vector2i;
import com.mithrilclient.reflection.hook.ClassHook;
import com.mithrilclient.reflection.hook.FieldHook;
import com.mithrilclient.reflection.hook.MethodHook;
import com.mithrilclient.reflection.hook.ObfuscatedIntHook;

public final class ReflectionHooks {
	private final static int UNDEFINED = 0;

	private final FieldHook<int[]> xpLevelsHook, levelsHook, baseLevelsHook;

	private final FieldHook<?> canvasHook;
	private final FieldHook<Component> componentHook;

	private final FieldHook<String> usernameHook, loginMessageHook;
	private final FieldHook<Integer> loginCursorField, loginStateHook;

	private final FieldHook<?> listHeadHook, nextNodeHook;
	private final FieldHook<Object[][][]> groundItemsHook;

	private final ClassHook itemClassHook;
	private final FieldHook<Integer> itemIdHook, itemQuantityHook;
	private final MethodHook<?> getItemDefHook;
	private final FieldHook<String> itemNameHook;

	private final FieldHook<Integer> playerHeightHook;

	private final FieldHook<Integer> cameraXHook, cameraYHook, cameraZHook, cameraCurveXHook, cameraCurveYHook;
	private final FieldHook<Integer> viewDistanceHook, viewWidthHook, viewHeightHook;

	private final FieldHook<int[]> sinTableHook, cosTableHook;

	private final FieldHook<int[][][]> groundHeightsHook;

	public ReflectionHooks(ClassLoader classLoader) {
		xpLevelsHook = new FieldHook<>(classLoader, "client", "jt");
		levelsHook = new FieldHook<>(classLoader, "client", "jp");
		baseLevelsHook = new FieldHook<>(classLoader, "client", "jn");

		canvasHook = new FieldHook<>(classLoader, "av", "ac");
		componentHook = new FieldHook<>(classLoader, "bl", "c");

		usernameHook = new FieldHook<>(classLoader, "cu", "ad");
		loginCursorField = new ObfuscatedIntHook(classLoader, "cu", "aa", -155548924, 1056528165);
		loginStateHook = new ObfuscatedIntHook(classLoader, "cu", "ak", 161866225, -1832063215);
		loginMessageHook = new FieldHook<>(classLoader, "cu", "at");

		listHeadHook = new FieldHook<>(classLoader, "ge", "c");
		nextNodeHook = new FieldHook<>(classLoader, "gh", "cr");

		groundItemsHook = new FieldHook<>(classLoader, "client", "il");

		itemClassHook = new ClassHook(classLoader, "cl");
		itemIdHook = new ObfuscatedIntHook(classLoader, "cl", "c", -678064429, UNDEFINED);
		itemQuantityHook = new ObfuscatedIntHook(classLoader, "cl", "o", -192784633, UNDEFINED);

		getItemDefHook = new MethodHook<>(classLoader, "r", "c", int.class, byte.class);
		itemNameHook = new FieldHook<>(classLoader, "ic", "a");

		playerHeightHook = new ObfuscatedIntHook(classLoader, "r", "iu", -2001195991, UNDEFINED);

		cameraXHook = new ObfuscatedIntHook(classLoader, "bb", "ge", -607288251, UNDEFINED);
		cameraYHook = new ObfuscatedIntHook(classLoader, "client", "gf", 1040194387, UNDEFINED);
		cameraZHook = new ObfuscatedIntHook(classLoader, "bp", "gc", 1872642347, UNDEFINED);

		cameraCurveXHook = new ObfuscatedIntHook(classLoader, "ie", "gs", -322498655, UNDEFINED);
		cameraCurveYHook = new ObfuscatedIntHook(classLoader, "r", "gp", -1467917035, UNDEFINED);

		viewDistanceHook = new ObfuscatedIntHook(classLoader, "client", "qb", 1344388827, UNDEFINED);
		viewWidthHook = new ObfuscatedIntHook(classLoader, "client", "ql", -307516803, UNDEFINED);
		viewHeightHook = new ObfuscatedIntHook(classLoader, "client", "qa", -481660425, UNDEFINED);

		sinTableHook = new FieldHook<>(classLoader, "ee", "d");
		cosTableHook = new FieldHook<>(classLoader, "ee", "ap");

		groundHeightsHook = new FieldHook<>(classLoader, "bk", "c");
	}

	public int[] getXpLevels() {
		return xpLevelsHook.getStatic();
	}

	public int[] getLevels() {
		return levelsHook.getStatic();
	}

	public int[] getBaseLevels() {
		return baseLevelsHook.getStatic();
	}

	public Component getCanvasParent() {
		return componentHook.get(canvasHook.getStatic());
	}

	public void setCanvasParent(CanvasWrapper canvasWrapper) {
		componentHook.set(canvasHook.getStatic(), canvasWrapper);
	}

	public void setUsername(String username) {
		usernameHook.set(null, username);
	}

	public int getLoginState() {
		return loginStateHook.getStatic();
	}

	public void setLoginState(int state) {
		loginStateHook.set(null, state);
	}

	public void setLoginCursorField(int field) {
		loginCursorField.set(null, field);
	}

	public void setLoginMessage(String message) {
		loginMessageHook.set(null, message);
	}

	public int getPlayerHeightLevel() {
		return playerHeightHook.getStatic();
	}
	public Object getItemDef(int id) {
		return getItemDefHook.invoke(null, id, (byte) 0);
	}

	public String getItemName(Object itemDef) {
		return itemNameHook.get(itemDef);
	}

	public List<GroundItem> getGroundItems() {
		List<GroundItem> items = new ArrayList<>();

		Object[][][] groundItems = groundItemsHook.getStatic();
		int height = getPlayerHeightLevel();

		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				Object list = groundItems[height][x][y];
				if (list == null) continue;

				Object head = listHeadHook.get(list);
				Object node = nextNodeHook.get(head);

				while (node != null) {
					if (node == head) break;

					if (itemClassHook.get().isInstance(node)) {
						int id = itemIdHook.get(node);
						int quantity = itemQuantityHook.get(node);

						items.add(new GroundItem(x, y, id, quantity));
					}

					Object last = node;
					node = nextNodeHook.get(node);
					if (last == node) break;
				}
			}
		}

		return items;
	}

	public Vector2i project(float x, float y, int h) {
		int[][][] pixelHeightGroundArray = groundHeightsHook.getStatic();

		int ix = (int) x;
		int iy = (int) y;

		int ex = (int) (x * 128);
		int ey = (int) (y * 128);

		if (ix < 0 || iy < 0 || ix > 103 || iy > 103) return null;

		int mx = ex & 0x7f;
		int my = ey & 0x7f;
		int h1 = pixelHeightGroundArray[h][ix][iy] * (128 - mx)
				+ pixelHeightGroundArray[h][ix + 1][iy] * mx >> 7;
		int h2 = pixelHeightGroundArray[h][ix][iy + 1] * (128 - mx)
				+ pixelHeightGroundArray[h][ix + 1][iy + 1] * mx >> 7;
		int ez = (h1 * (128 - my) + h2 * my >> 7);

		ex -= cameraXHook.getStatic();
		ez -= cameraZHook.getStatic();
		ey -= cameraYHook.getStatic();

		int[] sin = sinTableHook.getStatic();
		int[] cos = cosTableHook.getStatic();

		int sy = sin[cameraCurveYHook.getStatic()];
		int cy = cos[cameraCurveYHook.getStatic()];
		int sx = sin[cameraCurveXHook.getStatic()];
		int cx = cos[cameraCurveXHook.getStatic()];

		int t = ey * sx + ex * cx >> 16;
		ey = ey * cx - ex * sx >> 16;
		ex = t;
		t = ez * cy - ey * sy >> 16;
		ey = ez * sy + ey * cy >> 16;
		ez = t;

		if (ey < 50) return null;

		int px = (viewWidthHook.getStatic() / 2) + (ex * viewDistanceHook.getStatic() / ey);
		int py = (viewHeightHook.getStatic() / 2) + (ez * viewDistanceHook.getStatic() / ey);

		if (px == 0 && py == 0) return null;
		if (px < 0 || py < 0 || px >= viewWidthHook.getStatic() || py >= viewHeightHook.getStatic()) return null;

		return new Vector2i(px, py);
	}
}
