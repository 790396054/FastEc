package com.app.gmm.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.app.gmm.latte.delegates.bottom.BottomItemDelegate;
import com.app.gmm.latte.ec.R;
import com.app.gmm.latte.ec.R2;
import com.app.gmm.latte.net.RestClient;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.ui.recycler.MultipleItemEntity;
import com.app.gmm.latte.ui.refresh.RefreshHandler;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by gmm on 2017/11/4.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mRefreshLayout);

    }

    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 200);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        RestClient.builder()
                .url("index_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConvert convert = new IndexDataConvert();
                        convert.setJsonData(response);
                        ArrayList<MultipleItemEntity> convert1 = convert.convert();
                        Log.d("convert", convert1.toString());
                    }
                })
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
