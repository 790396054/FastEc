package com.app.gmm.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.app.gmm.latte.app.Latte;
import com.app.gmm.latte.net.RestClient;
import com.app.gmm.latte.net.callBack.IError;
import com.app.gmm.latte.net.callBack.IFailure;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.util.log.LatteLogger;
import com.orhanobut.logger.Logger;

/**
 * Created by gmm on 2017/11/8.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESHLAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        REFRESHLAYOUT = refreshLayout;
        REFRESHLAYOUT.setOnRefreshListener(this);
    }

    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("TAG",response);
                    }
                })
                .build()
                .get();
    }

    private void refresh(){
        REFRESHLAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstPage("index_data.json");
                REFRESHLAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {



        refresh();
    }
}
