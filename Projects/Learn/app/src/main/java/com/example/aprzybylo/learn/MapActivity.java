package com.example.aprzybylo.learn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.io.Serializable;

public class MapActivity extends FragmentActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = this.getIntent();
        int id = intent.getIntExtra("ID", 0);
        Car car = (Car) intent.getSerializableExtra("SERIALIZABLE");

        FragmentManager manager = getSupportFragmentManager();

        ListFragment fragment = new ListFragment();
        manager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();

    }

}
