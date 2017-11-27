package com.sandy.myframe.application;

import android.app.Service;
import android.content.Context;
import android.content.Intent;


import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * app 初始化 接口实现
 */
public class AppInterfaceImpl implements AppInterface {

    private ExecutorService pool = Executors.newFixedThreadPool(1);

    @Override
    public void initThirdPlugin(final Context context) {
        pool.execute(new Runnable() {
            @Override
            public void run() {
              //  FrescoHelper.frescoInit(context);
            }
        });
    }

    @Override
    public void initDir(List<String> dirs) {
        for (String dir : dirs) {
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }
    }

    @Override
    public void destroy() {
        App.pool.shutdownNow();//关闭线程池里面所有任务
    }

    public void initService(Context context, Class<? extends Service> service, String action) {
        Intent serviceIntent = new Intent(context, service);
        serviceIntent.setAction(action);
        context.startService(serviceIntent);
    }

    @Override
    public void initDB(Context context, String DBName, int DBVersion) {
    }

}
