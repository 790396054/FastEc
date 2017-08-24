package com.app.gmm.latte.ec.sign;

import com.alibaba.fastjson.JSONObject;
import com.app.gmm.latte.app.AccountManager;
import com.app.gmm.latte.ec.database.DatabaseManager;
import com.app.gmm.latte.ec.database.UserProfile;

/**
 * Created by gmm on 2017/8/22.
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener){
        JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final Long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        // 已经注册并登录成功了
        AccountManager.setSignState(true);
        if (signListener != null) {
            signListener.onSignInSuccess();
        }
    }

    public static void onSignUp(String response, ISignListener signListener){
        final JSONObject profileJson = JSONObject.parseObject(response).getJSONObject("data");
        final Long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        // 已经注册并登录成功了
        AccountManager.setSignState(true);
        if (signListener != null) {
            signListener.onSignUpSuccess();
        }
    }
}
