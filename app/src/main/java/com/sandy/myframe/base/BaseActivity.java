package com.sandy.myframe.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;

import com.sandy.myframe.handler.CommonDoHandler;
import com.sandy.myframe.handler.CommonHandler;

import butterknife.ButterKnife;

/**
 * Created by Activity 基类 on 2017/11/25.
 */

public abstract class BaseActivity extends Activity implements CommonDoHandler {
    public abstract int getContentViewId();

    //hand 状态码
    protected final static int HAND_CODE_OK = 10001;

    protected CommonHandler<BaseActivity> uiHandler;
    protected CommonHandler<BaseActivity> backgroundHandler;

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        init();
    }

    //私有方法区域
    private void init() {
        uiHandler = new CommonHandler<>(this);
        HandlerThread handlerThread = new HandlerThread(getClass().getName());
        handlerThread.start();
        backgroundHandler = new CommonHandler<>(this, handlerThread.getLooper());
    }
    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    public void doHandler(Message msg) {
        uiHandler.handleMessage(msg);
    }
}
