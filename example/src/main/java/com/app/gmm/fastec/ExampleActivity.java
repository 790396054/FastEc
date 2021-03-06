package com.app.gmm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.app.gmm.latte.activities.ProxyActivity;
import com.app.gmm.latte.app.Latte;
import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.ec.launcher.LauncherDelegate;
import com.app.gmm.latte.ec.main.EcBottomDelegate;
import com.app.gmm.latte.ec.sign.ISignListener;
import com.app.gmm.latte.ec.sign.SignInDelegate;
import com.app.gmm.latte.ui.launcher.ILauncherListener;
import com.app.gmm.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功2", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，登陆了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，没有登录", Toast.LENGTH_SHORT).show();
//                startWithPop(new SignInDelegate());
                startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }
}
