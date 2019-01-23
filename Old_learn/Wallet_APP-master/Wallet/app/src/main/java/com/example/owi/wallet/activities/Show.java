package com.example.owi.wallet.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.owi.wallet.MenuHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.adapters.itemsListAdapter;
import com.example.owi.wallet.data.Categories;
import com.example.owi.wallet.data.DataBase;
import com.example.owi.wallet.data.Items;

import java.util.LinkedList;
import java.util.List;

public class Show extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        final DataBase db = new DataBase(this);
        List<Items> allItems = db.getAllItems();

        ListView list = getListView();
        itemsListAdapter adapter = new itemsListAdapter(this, allItems, db);
        setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        Intent intent = new Intent(this, MenuHelper.onMenuItemSelected(id));
        startActivity(intent);

        return super.onOptionsItemSelected(item);

    }


}
