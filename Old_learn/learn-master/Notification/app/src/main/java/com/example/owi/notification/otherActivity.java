package com.example.owi.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class otherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        if( !this.getIntent().getExtras().isEmpty() ) {
            String actionName = this.getIntent().getExtras().getString("ACTION", "NO_ACTION");

            /* Show Name */
            TextView textView = (TextView) findViewById(R.id.textView3);
            textView.setText(actionName);
        }

    }

    @Override
    protected void onDestroy() {
        /* Stop Service */
        Intent serviceIntent = new Intent(this, newService.class);
        this.stopService(serviceIntent);
        super.onDestroy();
    }
}
