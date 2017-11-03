package com.app.gmm.latte.wechat;

import com.alibaba.fastjson.JSONObject;
import com.app.gmm.latte.net.RestClient;
import com.app.gmm.latte.net.callBack.IError;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by gmm on 2017/11/3.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 用户登录成功后的回调
     * @param userInfo 用户信息
     */
    protected abstract void onSignSuccess(String userInfo);

    // 微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
        .append(LatteWeChat.APP_ID)
        .append("&secret=")
        .append(LatteWeChat.APP_SECRET)
        .append("&code=")
        .append(code)
        .append("&grant_type=authorization_code");

        LatteLogger.d("authUrl:", authUrl.toString());
        getAuth(authUrl.toString());
    }

    // 获取授权信息
    private void getAuth(String authUrl) {
        RestClient.builder()
            .url(authUrl)
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    final JSONObject jsonObject = JSONObject.parseObject(response);
                    final String accessToken = jsonObject.getString("access_token");
                    final String openId = jsonObject.getString("openid");

                    final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("")
                            .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                            .append(accessToken)
                            .append("&openid=")
                            .append(openId)
                            .append("&lang=")
                            .append("zh_CN");
                        LatteLogger.d("userInfoUrl:", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                }
            })
            .error(new IError() {
                @Override
                public void onError(int code, String msg) {

                }
            })
            .build()
            .get();
    }

    // 获取用户信息
    private void getUserInfo(String userInfoUrl) {
        RestClient.builder()
            .url(userInfoUrl)
            .success(new ISuccess() {
                @Override
                public void onSuccess(String response) {
                    onSignSuccess(response);
                }
            })
            .error(new IError() {
                @Override
                public void onError(int code, String msg) {

                }
            })
            .build()
            .get();
    }
}
