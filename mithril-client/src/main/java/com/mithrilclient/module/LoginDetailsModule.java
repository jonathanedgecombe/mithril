package com.mithrilclient.module;

import com.mithrilclient.config.ConfigManager;
import com.mithrilclient.reflection.ReflectionHooks;

public final class LoginDetailsModule extends Module {
	private int lastLoginState = 0;

	@Override
	public void tick(ReflectionHooks hooks) {
		int loginState = hooks.getLoginState();

		if (loginState == 2 && lastLoginState == 0) {
			init(hooks);
		}

		lastLoginState = loginState;
	}

	@Override
	public void init(ReflectionHooks hooks) {
		String username = ConfigManager.CONFIG.getDefaultUsername();

		if (username == null) return;
		if (username.isEmpty()) return;

		hooks.setUsername(username);
		hooks.setLoginState(2);
		hooks.setLoginCursorField(1);
		hooks.setLoginMessage("Enter your username/email & password.");
	}
}
