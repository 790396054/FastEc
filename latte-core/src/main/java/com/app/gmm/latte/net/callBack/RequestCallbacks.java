package com.app.gmm.latte.net.callBack;

import android.os.Handler;

import com.app.gmm.latte.ui.LatteLoader;
import com.app.gmm.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gmm on 2017/7/9.
 */

public class RequestCallbacks implements Callback<String> {

    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler mHandler = new Handler();

    public RequestCallbacks(ISuccess success, IError error, IFailure failure,
                            IRequest request, LoaderStyle loaderStyle) {
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = request;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        onRequestFinish();
    }

    private void onRequestFinish(){
        if (LOADER_STYLE != null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 2000);
        }
    }
}
