package com.app.gmm.latte.net;

import android.content.Context;

import com.app.gmm.latte.net.callBack.IError;
import com.app.gmm.latte.net.callBack.IFailure;
import com.app.gmm.latte.net.callBack.IRequest;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by gmm on 2017/7/9.
 */

public class RestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private IRequest mIRequest;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset-UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mISuccess, mIError, mIFailure, mIRequest,
                mBody, mLoaderStyle, mContext);
    }
}
