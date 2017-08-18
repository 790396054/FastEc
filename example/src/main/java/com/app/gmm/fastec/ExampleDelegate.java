package com.app.gmm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.app.gmm.latte.delegates.LatteDelegate;
import com.app.gmm.latte.net.RestClient;
import com.app.gmm.latte.net.callBack.IError;
import com.app.gmm.latte.net.callBack.IFailure;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.ui.loader.LoaderStyle;

/**
 * Created by gmm on 2017/7/8.
 */

public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }

    private void test() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, response);
//                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, code + "==" + msg);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d(TAG, "onFailure()");
                    }
                })
                .loader(getContext(), LoaderStyle.BallPulseIndicator)
                .build()
                .get();

    }
}
