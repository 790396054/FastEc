package com.app.gmm.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.ec.R;
import com.app.gmm.latte.ec.R2;
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

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTextView;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){

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
                        }
                    }
                }
            }
        });
    }
}
