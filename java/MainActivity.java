package com.example.paramedics.servicestest;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bMyService,bMyBindService,bTestBinding,bDeclancherReceiver;
    EditText myEditText;
    TextView textView;
    MyBindService myBindService;
    boolean bound = false;
    PendingIntent pendingIntent;//pour intéragir avec le broadcastReceiver

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyBindService.LocalMyIBinder localMyIBinder = (MyBindService.LocalMyIBinder) service;
            myBindService = localMyIBinder.getService();
            bound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            bound = false;

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = findViewById(R.id.myEditText);
        textView = findViewById(R.id.textView);
        bMyService = findViewById(R.id.bMyService);
        bMyBindService = findViewById(R.id.bMyBindService);
        bTestBinding = findViewById(R.id.bTestBinding);
        bDeclancherReceiver = findViewById(R.id.bDeclancherReceiver);
        bMyService.setOnClickListener(this);
        bMyBindService.setOnClickListener(this);
        bDeclancherReceiver.setOnClickListener(this);
        bTestBinding.setOnClickListener(this);

        Intent intentService = new Intent(this, MyIntentService.class);
        startService(intentService);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bMyService){

            Intent serviceNormal = new Intent(this, MyService.class);
            startService(serviceNormal);

        }else if (v.getId() == R.id.bMyBindService){

            Intent bindServiceIntent = new Intent(this, MyBindService.class);
            bindService(bindServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        }else if (v.getId() == R.id.bTestBinding && bound){

            textView.setText(myBindService.doSomething(myEditText.getText().toString()));

        }else if (v.getId() == R.id.bDeclancherReceiver){

            Intent intent = new Intent(this, MyReceiver.class);
            intent.putExtra("start", true);
            sendBroadcast(intent);

        }
    }



}
