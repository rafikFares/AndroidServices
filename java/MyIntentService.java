package com.example.paramedics.servicestest;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;


public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d("ServicesTest","MyIntentService est dans onHandleIntent");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("ServicesTest","Verifier les autorisations");
            } else {

                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String os = System.getProperty("os.version");       // OS version
                String sdk = android.os.Build.VERSION.SDK;          // API Level
                int sdkInt = android.os.Build.VERSION.SDK_INT;      // API Level 2
                String device = android.os.Build.DEVICE;            // Device
                String model = android.os.Build.MODEL;              // Model
                String produit = android.os.Build.PRODUCT;          // Product
                String imei = telephonyManager.getDeviceId();       // IMEI
                String imsi = telephonyManager.getSubscriberId();   // IMSI

                Log.d("ServicesTest","MyIntentService os "+os);
                Log.d("ServicesTest","MyIntentService sdk "+sdk);
                Log.d("ServicesTest","MyIntentService sdkInt "+sdkInt);
                Log.d("ServicesTest","MyIntentService device "+device);
                Log.d("ServicesTest","MyIntentService model "+model);
                Log.d("ServicesTest","MyIntentService produit "+produit);
                Log.d("ServicesTest","MyIntentService imei "+imei);
                Log.d("ServicesTest","MyIntentService imsi "+imsi);

                boolean gpsOn = false;

                try {
                    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                    gpsOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception e) {}


                if (gpsOn) {
                    Log.d("ServicesTest","MyIntentService GPS is ON");
                }
            }

        }
    }

}


