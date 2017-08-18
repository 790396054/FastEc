package com.app.gmm.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by gmm on 2017/8/18.
 */

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mCompatImageView = null;

    @Override
    public View createView(Context context) {
        mCompatImageView = new AppCompatImageView(context);
        return mCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mCompatImageView.setBackgroundResource(data);
    }
}
