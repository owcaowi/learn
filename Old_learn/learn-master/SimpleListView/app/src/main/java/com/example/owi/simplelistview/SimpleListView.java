package com.example.owi.simplelistview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.*;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.*;


public class SimpleListView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);
        Button button = (Button) findViewById(R.id.button);

        String[] listElements = {"Item 1", "Item 2", "Item 3"};
        ListView list = (ListView) findViewById(R.id.listView);
        myListAdater adapter = new myListAdater(this, listElements);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                arg1.setSelected(true);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( view.getContext() , myListActivity.class);
                startActivity(intent);

            }
        });

    }



}
