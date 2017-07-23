package com.mithrilclient.module;

import com.mithrilclient.config.ConfigManager;
import com.mithrilclient.reflection.ReflectionHooks;

public final class LoginDetailsModule extends Module {
	private boolean set = false;

	@Override
	public void tick() {
		if (!set) {
			ReflectionHooks.setUsername(ConfigManager.CONFIG.getDefaultUsername());
			ReflectionHooks.setLoginState(2);
			ReflectionHooks.setLoginCursorField(1);
			ReflectionHooks.setLoginMessage("Enter your username/email & password.");
			set = true;
		}
	}
}
