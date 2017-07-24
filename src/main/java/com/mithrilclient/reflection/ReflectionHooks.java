package com.mithrilclient.reflection;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.mithrilclient.model.GroundItem;
import com.mithrilclient.model.Vector2i;
import com.mithrilclient.reflection.entry.ClassEntry;
import com.mithrilclient.reflection.entry.FieldEntry;
import com.mithrilclient.reflection.entry.InstanceFieldEntry;
import com.mithrilclient.reflection.entry.ObfuscatedIntInstanceFieldEntry;
import com.mithrilclient.reflection.entry.ObfuscatedIntStaticFieldEntry;
import com.mithrilclient.reflection.entry.StaticFieldEntry;
import com.mithrilclient.reflection.entry.StaticMethodEntry;

public final class ReflectionHooks {
	private final static ClassEntry CANVAS_ENTRY_PARENT_CLASS = new ClassEntry("av");
	private final static StaticFieldEntry<?> CANVAS_ENTRY = new StaticFieldEntry<>(CANVAS_ENTRY_PARENT_CLASS, "ac");

	private final static ClassEntry CANVAS_CLASS = new ClassEntry("bl");
	private final static FieldEntry<Component, ?> COMPONENT_FIELD = new FieldEntry<>(CANVAS_CLASS, CANVAS_ENTRY, "c");

	private final static ClassEntry CLIENT_CLASS = new ClassEntry("client");
	private final static StaticFieldEntry<int[]> XP_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jt");
	private final static StaticFieldEntry<int[]> SKILLS_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jp");
	private final static StaticFieldEntry<int[]> BASE_SKILLS_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "jn");

	private final static ClassEntry LOGIN_CONTROLLER_CLASS = new ClassEntry("cu");
	private final static StaticFieldEntry<String> USERNAME_FIELD = new StaticFieldEntry<>(LOGIN_CONTROLLER_CLASS, "ad");
	private final static StaticFieldEntry<Integer> CURSOR_FIELD_FIELD = new ObfuscatedIntStaticFieldEntry(LOGIN_CONTROLLER_CLASS, "aa", 1056528165, -155548924);
	private final static StaticFieldEntry<Integer> LOGIN_STATE_FIELD = new ObfuscatedIntStaticFieldEntry(LOGIN_CONTROLLER_CLASS, "ak", -1832063215, 161866225);
	private final static StaticFieldEntry<String> LOGIN_MESSAGE_FIELD = new StaticFieldEntry<>(LOGIN_CONTROLLER_CLASS, "at");

	private final static ClassEntry LINKED_LIST_CLASS = new ClassEntry("ge");
	private final static InstanceFieldEntry<?> LIST_HEAD_FIELD = new InstanceFieldEntry<>(LINKED_LIST_CLASS, "c");

	private final static ClassEntry LINKED_NODE_CLASS = new ClassEntry("gh");
	//private final static InstanceFieldEntry<?> PREVIOUS_NODE_FIELD = new InstanceFieldEntry<>(LINKED_NODE_CLASS, "cz");
	private final static InstanceFieldEntry<?> NEXT_NODE_FIELD = new InstanceFieldEntry<>(LINKED_NODE_CLASS, "cr");

	private final static StaticFieldEntry<Object[][][]> GROUND_ITEMS_FIELD = new StaticFieldEntry<>(CLIENT_CLASS, "il");

	private final static ClassEntry ITEM_CLASS = new ClassEntry("cl");
	private final static InstanceFieldEntry<Integer> ITEM_ID_FIELD = new ObfuscatedIntInstanceFieldEntry(ITEM_CLASS, "c", 0, -678064429);
	private final static InstanceFieldEntry<Integer> ITEM_QUANTITY_FIELD = new ObfuscatedIntInstanceFieldEntry(ITEM_CLASS, "o", 0, -192784633);

	private final static ClassEntry UNK_HEIGHT_CONTAINER = new ClassEntry("r");
	private final static StaticFieldEntry<Integer> PLAYER_HEIGHT_LEVEL_FIELD = new ObfuscatedIntStaticFieldEntry(UNK_HEIGHT_CONTAINER, "iu", 0, -2001195991);

	private final static ClassEntry UNK_GROUND_HEIGHTS_CONTAINER = new ClassEntry("bk");
	private final static StaticFieldEntry<int[][][]> GROUND_HEIGHTS_FIELD = new StaticFieldEntry<>(UNK_GROUND_HEIGHTS_CONTAINER, "c");

	private final static ClassEntry RASTERIZER_CLASS = new ClassEntry("ee");
	private final static StaticFieldEntry<int[]> SIN_LOOKUP_FIELD = new StaticFieldEntry<>(RASTERIZER_CLASS, "d");
	private final static StaticFieldEntry<int[]> COS_LOOKUP_FIELD = new StaticFieldEntry<>(RASTERIZER_CLASS, "ap");

	private final static StaticFieldEntry<Integer> VIEW_DISTANCE_FIELD = new ObfuscatedIntStaticFieldEntry(CLIENT_CLASS, "qb", 0, 1344388827);
	private final static StaticFieldEntry<Integer> VIEW_WIDTH_FIELD = new ObfuscatedIntStaticFieldEntry(CLIENT_CLASS, "ql", 0, -307516803);
	private final static StaticFieldEntry<Integer> VIEW_HEIGHT_FIELD = new ObfuscatedIntStaticFieldEntry(CLIENT_CLASS, "qa", 0, -481660425);

	private final static ClassEntry UNK_CAMERA_X_CONTAINER = new ClassEntry("bb");
	private final static StaticFieldEntry<Integer> CAMERA_X_FIELD = new ObfuscatedIntStaticFieldEntry(UNK_CAMERA_X_CONTAINER, "ge", 0, -607288251);
	private final static StaticFieldEntry<Integer> CAMERA_Y_FIELD = new ObfuscatedIntStaticFieldEntry(CLIENT_CLASS, "gf", 0, 1040194387);
	private final static ClassEntry UNK_CAMERA_Z_CONTAINER = new ClassEntry("bp");
	private final static StaticFieldEntry<Integer> CAMERA_Z_FIELD = new ObfuscatedIntStaticFieldEntry(UNK_CAMERA_Z_CONTAINER, "gc", 0, 1872642347);
	private final static ClassEntry UNK_CAMERA_CURVE_Y_CONTAINER = new ClassEntry("r");
	private final static StaticFieldEntry<Integer> CAMERA_CURVE_Y_FIELD = new ObfuscatedIntStaticFieldEntry(UNK_CAMERA_CURVE_Y_CONTAINER, "gp", 0, -1467917035);
	private final static ClassEntry UNK_CAMERA_CURVE_X_CONTAINER = new ClassEntry("ie");
	private final static StaticFieldEntry<Integer> CAMERA_CURVE_X_FIELD = new ObfuscatedIntStaticFieldEntry(UNK_CAMERA_CURVE_X_CONTAINER, "gs", 0, -322498655);

	private final static ClassEntry UNK_GET_ITEM_DEF_CONTAINER = new ClassEntry("r");
	private final static StaticMethodEntry<?> GET_ITEM_DEF_METHOD = new StaticMethodEntry<>(UNK_GET_ITEM_DEF_CONTAINER, "c", int.class, byte.class);

	private final static ClassEntry ITEM_DEF_CLASS = new ClassEntry("ic");
	private final static InstanceFieldEntry<String> ITEM_NAME_FIELD = new InstanceFieldEntry<>(ITEM_DEF_CLASS, "a");

	public static Component getCanvasParent() {
		return COMPONENT_FIELD.get();
	}

	public static void setCanvasParent(Component component) {
		COMPONENT_FIELD.set(component);
	}

	public static int[] getXpLevels() {
		return XP_FIELD.get();
	}

	public static int[] getSkills() {
		return SKILLS_FIELD.get();
	}

	public static int[] getBaseSkills() {
		return BASE_SKILLS_FIELD.get();
	}

	public static String getUsername() {
		return USERNAME_FIELD.get();
	}

	public static void setUsername(String username) {
		USERNAME_FIELD.set(username);
	}

	public static int getLoginCursorField() {
		return CURSOR_FIELD_FIELD.get();
	}

	public static void setLoginCursorField(int field) {
		CURSOR_FIELD_FIELD.set(field);
	}

	public static int getLoginState() {
		return LOGIN_STATE_FIELD.get();
	}

	public static void setLoginState(int state) {
		LOGIN_STATE_FIELD.set(state);
	}

	public static void setLoginMessage(String message) {
		LOGIN_MESSAGE_FIELD.set(message);
	}

	public static int getPlayerHeightLevel() {
		return PLAYER_HEIGHT_LEVEL_FIELD.get();
	}

	public static List<GroundItem> getGroundItems() {
		List<GroundItem> items = new ArrayList<>();

		Object[][][] groundItems = GROUND_ITEMS_FIELD.get();
		int height = getPlayerHeightLevel();

		for (int x = 0; x < 104; x++) {
			for (int y = 0; y < 104; y++) {
				Object list = groundItems[height][x][y];
				if (list == null) continue;

				Object head = LIST_HEAD_FIELD.get(list);
				Object node = NEXT_NODE_FIELD.get(head);

				while (node != null) {
					if (node == head) break;

					if (ITEM_CLASS.get().isInstance(node)) {
						int id = ITEM_ID_FIELD.get(node);
						int quantity = ITEM_QUANTITY_FIELD.get(node);

						items.add(new GroundItem(x, y, id, quantity));
					}

					Object last = node;
					node = NEXT_NODE_FIELD.get(node);
					if (last == node) break;
				}
			}
		}

		return items;
	}

	public static Vector2i project(float x, float y, int h) {
		int[][][] pixelHeightGroundArray = GROUND_HEIGHTS_FIELD.get();

		int ix = (int) x;
		int iy = (int) y;

		int ex = (int) (x * 128);
		int ey = (int) (y * 128);

		if (ix < 0 || iy < 0 || ix > 103 || iy > 103) return null;

		int k1 = ex & 0x7f;
		int l1 = ey & 0x7f;
		int i2 = pixelHeightGroundArray[h][ix][iy] * (128 - k1)
				+ pixelHeightGroundArray[h][ix + 1][iy] * k1 >> 7;
		int j2 = pixelHeightGroundArray[h][ix][iy + 1] * (128 - k1)
				+ pixelHeightGroundArray[h][ix + 1][iy + 1] * k1 >> 7;
		int ez = (i2 * (128 - l1) + j2 * l1 >> 7);

		ex -= CAMERA_X_FIELD.get();
		ez -= CAMERA_Z_FIELD.get();
		ey -= CAMERA_Y_FIELD.get();

		int[] sin = SIN_LOOKUP_FIELD.get();
		int[] cos = COS_LOOKUP_FIELD.get();

		int sy = sin[CAMERA_CURVE_Y_FIELD.get()];
		int cy = cos[CAMERA_CURVE_Y_FIELD.get()];
		int sx = sin[CAMERA_CURVE_X_FIELD.get()];
		int cx = cos[CAMERA_CURVE_X_FIELD.get()];

		int t = ey * sx + ex * cx >> 16;
		ey = ey * cx - ex * sx >> 16;
		ex = t;
		t = ez * cy - ey * sy >> 16;
		ey = ez * sy + ey * cy >> 16;
		ez = t;

		if (ey < 50) return null;

		int px = (VIEW_WIDTH_FIELD.get() / 2) + (ex * VIEW_DISTANCE_FIELD.get() / ey);
		int py = (VIEW_HEIGHT_FIELD.get() / 2) + (ez * VIEW_DISTANCE_FIELD.get() / ey);

		if (px == 0 && py == 0) return null;
		if (px < 0 || py < 0 || px >= VIEW_WIDTH_FIELD.get() || py >= VIEW_HEIGHT_FIELD.get()) return null;

		return new Vector2i(px, py);
	}

	public static Object getItemDef(int id) {
		return GET_ITEM_DEF_METHOD.call(id, (byte) 0);
	}

	public static String getItemName(Object itemDef) {
		return ITEM_NAME_FIELD.get(itemDef);
	}
}
