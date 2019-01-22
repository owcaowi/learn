package com.example.aprzybylo.learn.PushService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.aprzybylo.learn.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

}
