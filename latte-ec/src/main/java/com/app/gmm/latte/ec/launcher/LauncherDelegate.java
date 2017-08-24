package com.app.gmm.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.app.gmm.latte.app.AccountManager;
import com.app.gmm.latte.app.IUserChecker;
import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.ec.R;
import com.app.gmm.latte.ec.R2;
import com.app.gmm.latte.ui.launcher.ILauncherListener;
import com.app.gmm.latte.ui.launcher.OnLauncherFinishTag;
import com.app.gmm.latte.ui.launcher.ScrollLauncherTag;
import com.app.gmm.latte.util.storage.LattePreference;
import com.app.gmm.latte.util.timer.BaseTimerTask;
import com.app.gmm.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gmm on 2017/7/19.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    private Timer mTimer;

    private int count = 5;

    private ILauncherListener mILauncherListener;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTextView;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 检测是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检测用户是否登录了 APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTextView != null) {
                    mTextView.setText(MessageFormat.format("跳过\n{0}s",count));
                    count --;
                    if (count < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
