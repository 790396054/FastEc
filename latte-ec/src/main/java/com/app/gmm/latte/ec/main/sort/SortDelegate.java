package com.app.gmm.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.gmm.latte.delegates.bottom.BottomItemDelegate;
import com.app.gmm.latte.ec.R;

/**
 * Created by gmm on 2017/11/4.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
