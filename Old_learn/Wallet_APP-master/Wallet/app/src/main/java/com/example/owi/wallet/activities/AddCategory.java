package com.example.owi.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.owi.wallet.MenuHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.data.Categories;
import com.example.owi.wallet.data.DataBase;

public class AddCategory extends Activity {

    View errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        errorMessage = findViewById(R.id.textView5);

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

    /**
     * Add category
     * on success return home
     *
     * @param view
     */

    public void addCategory(View view) {

        DataBase db = new DataBase(this);
        EditText categoryName = (EditText) findViewById(R.id.editText4);
        String tmpName = categoryName.getText().toString();

        if (!tmpName.isEmpty()) {

            Categories newCategory = new Categories();
            newCategory.setName(tmpName);
            db.addCategory(newCategory);
            db.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            errorMessage.setVisibility(View.VISIBLE);
        }

    }

}
