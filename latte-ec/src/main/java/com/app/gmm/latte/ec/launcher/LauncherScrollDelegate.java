package com.app.gmm.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.gmm.latte.app.AccountManager;
import com.app.gmm.latte.app.IUserChecker;
import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.ec.R;
import com.app.gmm.latte.ui.launcher.ILauncherListener;
import com.app.gmm.latte.ui.launcher.LauncherHolderCreator;
import com.app.gmm.latte.ui.launcher.OnLauncherFinishTag;
import com.app.gmm.latte.ui.launcher.ScrollLauncherTag;
import com.app.gmm.latte.util.storage.LattePreference;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * 第一次翻页页面
 * Created by gmm on 2017/8/18.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;

    private ILauncherListener mILauncherListener;

    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner(getContext());
        return mConvenientBanner;
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        // 如果点击的是之后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            // 检测用户是否已经登录
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
}
