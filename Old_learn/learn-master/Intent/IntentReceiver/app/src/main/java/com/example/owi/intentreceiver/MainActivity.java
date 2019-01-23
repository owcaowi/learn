package com.example.owi.intentreceiver;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usertext = (TextView) findViewById(R.id.user_text_edit);
        //Receiving data from another aplication
        Bundle bundle = getIntent().getExtras();
        usertext.setText(bundle.get(Intent.EXTRA_TEXT) + "");
    }

}
