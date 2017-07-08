package com.app.gmm.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by gmm on 2017/7/7.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigrations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String, Object> getConfigrations() {
        return Configurator.getInstance().getLatteConfigs();
    }
}
