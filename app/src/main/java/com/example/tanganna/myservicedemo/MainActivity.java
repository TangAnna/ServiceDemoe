package com.example.tanganna.myservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MyService.MyBinder mMyBinder;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MyService.MyBinder) service;//获得到MyBinder对象后可以调用里面的方法
//            mMyBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "==MainActivity thread id is " + Thread.currentThread().getId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    /**
     * 绑定服务
     */
    public void bind(){
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     */
    public void unBind(){
        unbindService(mServiceConnection);
    }

}
