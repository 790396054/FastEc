package com.app.gmm.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by gmm on 2017/7/7.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigrations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigrations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigrations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
