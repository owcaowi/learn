package com.example.owi.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.owi.wallet.DateHelper;
import com.example.owi.wallet.MenuHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.data.DataBase;
import com.example.owi.wallet.data.Limits;

public class AddLimit extends Activity {

    View errorMessage;
    DataBase db;
    EditText categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_limit);

        db = new DataBase(this);
        errorMessage = findViewById(R.id.textView6);
        categoryName   = (EditText)findViewById( R.id.editText5 );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Intent intent = new Intent(this, MenuHelper.onMenuItemSelected(id) );
        startActivity(intent);

        return super.onOptionsItemSelected(item);

    }


    /**
     * Add new limit to database
     * on success: go back home
     *
     * @param view
     */

    public void addNewLimit(View view){

        String today = DateHelper.getTodayDate("yyyyMMdd HH:mm:ss");
        String tmpLimit = categoryName.getText().toString();

        if ( !tmpLimit.isEmpty() ){

            Limits newLimit = new Limits();
            newLimit.setDate(today);
            newLimit.setLimit( Float.valueOf(tmpLimit ) );

            db.addLimit(newLimit);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else{
            errorMessage.setVisibility(View.VISIBLE);
        }

    }


}
