package com.app.gmm.latte.app;

import com.app.gmm.latte.util.storage.LattePreference;

/**
 * Created by gmm on 2017/8/22.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG;
    }

    // 保存用户登陆状态，登录后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
