package com.example.owi.wallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owi.wallet.DateHelper;
import com.example.owi.wallet.DatePickerFragment;
import com.example.owi.wallet.MenuHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.data.Categories;
import com.example.owi.wallet.data.DataBase;
import com.example.owi.wallet.data.Items;
import com.example.owi.wallet.newTextWatcher;

import java.util.LinkedList;
import java.util.List;

public class Add extends ActionBarActivity {


    private DataBase db = new DataBase(this);
    private EditText itemName;
    private EditText itemPrice;
    private Spinner itemCategory;
    private TextView catLabel;
    public EditText itemDate;
    private Button addButton;
    public static String newDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        itemName = (EditText) findViewById(R.id.editText2);
        itemPrice = (EditText) findViewById(R.id.editText3);
        catLabel = (TextView) findViewById(R.id.textView);
        itemCategory = (Spinner) findViewById(R.id.spinner1);
        itemDate = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.add_button);


        itemDate.setText(DateHelper.getTodayDate("yyyyMMdd"));
        this.setTextWatcher();

        // Fill the dropdownlist
        final Spinner itemCategory = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, this.getListOfCategories());

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (this.getListOfCategories().size() != 0) {
            itemCategory.setAdapter(dataAdapter);
        }

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


    public void setTextWatcher(){

        newTextWatcher watch = new newTextWatcher(itemName, itemPrice,addButton);

        itemName.addTextChangedListener(watch);
        itemPrice.addTextChangedListener(watch);
        itemDate.addTextChangedListener(watch);

    }

    /**
     * Return list of Categories
     *
     * @return
     */

    public List<String> getListOfCategories() {

        List<String> listOfNames = new LinkedList<String>();
        List<Categories> list = new LinkedList<Categories>();

        list = db.getAllCategories();

        for (int i = 0; i < list.size(); i++) {
            Categories tmp = new Categories();
            tmp = list.get(i);
            listOfNames.add(tmp.getName().toString());
        }

        return listOfNames;
    }

    /**
     * Add the Product to list if any category exists
     *
     * @param view
     */

    public void addProduct(View view) {

        String categoryName = itemCategory.getSelectedItem().toString();
        Categories category = new Categories();
        category = db.getCategoryByName(categoryName);
        Integer categoryId = category.getId();

        if (categoryId != null) {

            Items newItem = new Items();
            newItem.setName(itemName.getText().toString());
            newItem.setPrice(Float.parseFloat(itemPrice.getText().toString()));
            newItem.setCategory_id(categoryId);
            newItem.setDate(Integer.parseInt(itemDate.getText().toString()));

            db.addItem(newItem);
            db.close();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.item_error_message), Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Show the date Picker Fragment
     *
     * @param v
     */

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
