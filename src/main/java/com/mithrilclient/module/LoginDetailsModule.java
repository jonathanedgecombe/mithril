package com.mithrilclient.module;

import com.mithrilclient.config.ConfigManager;
import com.mithrilclient.reflection.ReflectionHooks;

public final class LoginDetailsModule extends Module {
	@Override
	public void init() {
		ReflectionHooks.setUsername(ConfigManager.CONFIG.getDefaultUsername());
		ReflectionHooks.setLoginState(2);
		ReflectionHooks.setLoginCursorField(1);
		ReflectionHooks.setLoginMessage("Enter your username/email & password.");
	}
}
