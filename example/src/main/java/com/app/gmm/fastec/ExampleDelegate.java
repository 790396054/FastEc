package com.app.gmm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.gmm.latte.delegates.LatteDelegate;

/**
 * Created by gmm on 2017/7/8.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
