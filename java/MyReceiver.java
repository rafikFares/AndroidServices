package com.example.paramedics.servicestest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    Intent myIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ServicesTest","MyReceiver est dans onReceive");

        boolean start = intent.getBooleanExtra("start", true);
        myIntent = new Intent(context, MyServiceForReceiver.class);

        if (start) {
            Log.i("ServicesTest","MyReceiver will start Service");
            context.startService(myIntent);
        } else {
            Log.i("ServicesTest","MyReceiver will stop Service");
            context.stopService(myIntent);
        }

    }

}
