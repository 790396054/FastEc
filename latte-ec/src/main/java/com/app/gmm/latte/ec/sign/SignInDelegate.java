package com.app.gmm.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.ec.R;
import com.app.gmm.latte.ec.R2;
import com.app.gmm.latte.net.RestClient;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gmm on 2017/8/22.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {
            RestClient.builder()
                    .url("http://116.196.95.67/RestServer/api/user_profile.php")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChatSignIn(){

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickSignUp(){
        start( new SignUpDelegate());
    }

    private boolean checkForm() {
        boolean isPass = true;
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写不少于6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
