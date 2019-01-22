package com.example.aprzybylo.learn.Service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import com.example.aprzybylo.learn.R;


public class ServiceActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);


        Intent intent = new Intent(this, MyService.class);
        startForegroundService(intent);

          //  stopService(new Intent(this, MyService.class));

    }

}
