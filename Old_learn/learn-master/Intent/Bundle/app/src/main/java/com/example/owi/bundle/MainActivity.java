package com.example.owi.bundle;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    String userText = new String();
    EditText userValue;
    Button goToButtonSecond;
    Button goToButtonSeparate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userValue = (EditText) findViewById(R.id.editText);
        goToButtonSecond = (Button)findViewById(R.id.button);
        goToButtonSeparate = (Button)findViewById(R.id.button2);


        // Send data to another activity
        goToButtonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userText = userValue.getText().toString();

                //Create new Intent
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                //Saving data Method I
                Bundle basket = new Bundle();
                basket.putString("value", userText);
                intent.putExtras(basket);

                startActivity(intent);


            }
        });

        // Send data to another app
        goToButtonSeparate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userText = userValue.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Method II
                intent.putExtra(Intent.EXTRA_TEXT, userText);
                startActivity(Intent.createChooser(intent,"Chooser"));


            }
        });


    }



}
