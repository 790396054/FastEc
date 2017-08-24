package com.app.gmm.latte.app;

/**
 * 用户状态接口
 * Created by gmm on 2017/8/22.
 */

public interface IUserChecker {
    /**
     * 已经登录
     */
    void onSignIn();

    /**
     * 没有登录
     */
    void onNotSignIn();
}
