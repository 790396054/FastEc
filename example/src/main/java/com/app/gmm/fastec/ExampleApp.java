package com.app.gmm.fastec;

import android.app.Application;

import com.app.gmm.latte.app.Latte;
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
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
