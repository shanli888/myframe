package com.sandy.myframe.http;

import android.content.Context;

import com.sandy.myframe.http.jsonhandle.JsonWebBusinessHandler;
import com.sandy.myframe.util.ToastUtils;
import com.will.web.HttpManager;
import com.will.web.handle.HttpBusinessCallback;
import com.will.web.okhttp3.OkHttpManager;

import java.util.Map;

/**
 */
public class HttpFunction {

    public static int SUC_OK = 1000;

    public static final int SOURCES_ANDROID = 2;

    public static final int HEAD_1 = 1;

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private HttpManager mHttpManager = new OkHttpManager();
    protected Context mContext;

    public HttpFunction(Context context){
        mContext = context;
    }

    public void httpGet(String url, Map<String,String> params, HttpBusinessCallback callback) {
        JsonWebBusinessHandler jsonWebBusinessHandler = new JsonWebBusinessHandler(mContext);
        callback.setWebHandler(jsonWebBusinessHandler);
        mHttpManager.Request(HttpManager.Method.GET,url, params,callback);
    }

    public void httpPost(String url, Map<String,String> params, HttpBusinessCallback callback){
        JsonWebBusinessHandler jsonWebBusinessHandler = new JsonWebBusinessHandler(mContext);
        callback.setWebHandler(jsonWebBusinessHandler);
        mHttpManager.Request(HttpManager.Method.POST,url,params,callback);
    }

    public static boolean isSuc(String code){
        double dCode=0;
        try {
            dCode= Double.parseDouble(code);
        }catch (Exception e){
            e.printStackTrace();
        }

        return SUC_OK==dCode;//(SUC_OK + "").equals(code);
    }

    public String getHttpError(Context context, String code){
        return "";//ErrorHelper.getErrorHint(context,code);
    }

    /**
     *
     *  @param context
     *  @param code
     *  提供默认的错误处理
     *
     */
    public void defaultHandleError(Context context, String code){
        ToastUtils.showToast(context,getHttpError(context,code));
    }
}
