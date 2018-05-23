package com.example.paramedics.servicestest;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServicesTest","MyService est dans onStartCommand");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    for (int i=0;i<1;i++){
                        try {
                            Log.d("ServicesTest","MyService est dans est actuellement dans run");
                            //faire des trucs
                            wait(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServicesTest","MyService est dans onDestroy");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
