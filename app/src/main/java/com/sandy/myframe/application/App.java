package com.sandy.myframe.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 初始化一些全局的控间，及全局变量
 * 例如初始化：
 * 1、activeandroid数据库
 * 2、有盟统计
 */
public class App extends Application {
    private static List<Activity> activityList = new ArrayList<>();
    private  AppInterface mAppInterface = new  AppInterfaceImpl();

    //常量区
    public static boolean isDebug = true;
    public static boolean isLogin = false;// 判断用户是否登录

    private static String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String FILEPATH_ROOT = SDCARD_ROOT + File.separator + AppConfig.FILEPATH_ROOT_NAME;
    public static final String FILEPATH_CACHE = FILEPATH_ROOT + File.separator + AppConfig.FILEPATH_CACHE_NAME;
    public static final String FILEPATH_VOICE = FILEPATH_ROOT + File.separator + AppConfig.FILEPATH_VOICE_NAME;
    public static final String FILEPATH_UPAPK = "frame" + File.separator + AppConfig.FILEPATH_UPAPK_NAME;
    public static final String FILEPATH_CAMERA = FILEPATH_ROOT + File.separator + AppConfig.FILEPATH_CAMERA_NAME;
    public static final String FILEPATH_VOICE_RECORD = FILEPATH_VOICE + File.separator + AppConfig.FILEPATH_VOICE_RECORD_NAME;

    public static final String SERVICE_ACTION = AppConfig.SERVICE_ACTION;

    public static final ExecutorService pool = Executors.newFixedThreadPool(5);

    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        List<String> dirs = new ArrayList<>();
        {
            dirs.add(FILEPATH_CACHE);
            dirs.add(FILEPATH_VOICE);
            dirs.add(FILEPATH_UPAPK);
            dirs.add(FILEPATH_CAMERA);
            dirs.add(FILEPATH_VOICE_RECORD);
        }
        instance = this;
        mAppInterface.initDir(dirs);

        mAppInterface.initThirdPlugin(this);

    }

    // 单例模式获取唯一的MyApplication实例
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppInterface.destroy();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}