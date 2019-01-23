package com.example.aprzybylo.fragmentsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SecondFragment fragment = new SecondFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.Container, fragment)
                .commit();
    }
}
