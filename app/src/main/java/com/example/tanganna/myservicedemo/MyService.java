package com.example.tanganna.myservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by TangAnna on 2017/7/4.
 */
public class MyService extends Service {

    private MyBinder mMyBinder;
    @Override
    public void onCreate() {//只有启动的时候调用一次
        super.onCreate();
        mMyBinder = new MyBinder();
        Log.d("MyService", "==MyService thread id is " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //这里可以开启线程处理耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开始执行后台任务
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//销毁的时候调用一次
        super.onDestroy();
        //处理一些资源的释放
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    class MyBinder extends Binder{

        /**
         * 后台下载
         */
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 开始执行后台任务
                }
            }).start();
        }

    }
}
