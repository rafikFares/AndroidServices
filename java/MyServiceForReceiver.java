package com.example.paramedics.servicestest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class MyServiceForReceiver extends Service {

    NotificationManager notificationManager;
    PendingIntent pendingIntent;
    private final IBinder mBinder = new MyBinder();

    public MyServiceForReceiver() {
    }


    public class MyBinder extends Binder {
        MyServiceForReceiver getService() {
            return MyServiceForReceiver.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ServicesTest", "MyServiceForReceiver est dans onBind");
        starting();
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ServicesTest", "MyServiceForReceiver est dans onStartCommand");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    starting();
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

    public void starting(){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notify(1,monBuilder().build());
        }
    }

    private Notification.Builder monBuilder() {
        Notification.Builder builder = new Notification.Builder(this);

        pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        return builder.setSmallIcon(android.R.drawable.star_on) //dessin
                .setContentTitle("ServicesTestTitre")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText("ServicesTestMessage");
    }
}
