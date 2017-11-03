package com.app.gmm.latte.wechat.templates;

import com.app.gmm.latte.wechat.BaseWXEntryActivity;
import com.app.gmm.latte.wechat.LatteWeChat;

/**
 * Created by gmm on 2017/11/2.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
