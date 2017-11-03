package com.app.gmm.fastec;

import android.app.Application;

import com.app.gmm.latte.app.Latte;
import com.app.gmm.latte.ec.database.DatabaseManager;
import com.app.gmm.latte.ec.icon.FontEcModule;
import com.app.gmm.latte.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by gmm on 2017/7/7.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();

        DatabaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho(){
        Stetho.initializeWithDefaults(this);
    }
}
