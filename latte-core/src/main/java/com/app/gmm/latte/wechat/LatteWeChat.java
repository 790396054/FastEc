package com.app.gmm.latte.wechat;

import com.app.gmm.latte.app.ConfigKeys;
import com.app.gmm.latte.app.Latte;

/**
 * Created by gmm on 2017/11/2.
 */

public class LatteWeChat {

    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);


    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }
}
