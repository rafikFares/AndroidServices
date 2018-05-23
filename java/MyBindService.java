package com.example.paramedics.servicestest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {

    private final IBinder myIBinder = new LocalMyIBinder();//2

    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {//1
        return myIBinder;
    }

    public String doSomething(String number){
        Log.d("ServicesTest","MyBindService est dans doSomething");
        return number;
    }

    public class LocalMyIBinder extends Binder {//3 qui retourne une réference de la class pour accéder au fonction de cet class
        MyBindService getService(){
            return MyBindService.this;
        }
    }
}
