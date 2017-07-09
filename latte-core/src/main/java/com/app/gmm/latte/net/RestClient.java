package com.app.gmm.latte.net;

import android.content.Context;
import android.support.annotation.NonNull;

import com.app.gmm.latte.net.callBack.IError;
import com.app.gmm.latte.net.callBack.IFailure;
import com.app.gmm.latte.net.callBack.IRequest;
import com.app.gmm.latte.net.callBack.ISuccess;
import com.app.gmm.latte.net.callBack.RequestCallbacks;
import com.app.gmm.latte.ui.LatteLoader;
import com.app.gmm.latte.ui.LoaderStyle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by gmm on 2017/7/8.
 */

public class RestClient {

    private final String URL;
    private final Map<String, Object> PARAMS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url, Map<String, Object> params, ISuccess success, IError error,
                      IFailure failure, IRequest request, RequestBody body, LoaderStyle loaderStyle, Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = request;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    @NonNull
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(SUCCESS, ERROR, FAILURE, REQUEST, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
