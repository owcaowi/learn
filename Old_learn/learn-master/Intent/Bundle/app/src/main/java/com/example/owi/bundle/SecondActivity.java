package com.example.owi.bundle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView usertext = (TextView) findViewById(R.id.user_text_edit);
        //Get data from bundle
        Bundle bundle = getIntent().getExtras();
        usertext.setText( bundle.get("value")+"" );

    }

}
