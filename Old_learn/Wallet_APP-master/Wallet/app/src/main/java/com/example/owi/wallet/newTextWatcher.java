package com.example.owi.wallet;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;


public class newTextWatcher implements TextWatcher {

    private EditText itemName;
    private EditText itemPrice;
    private Button addButton;


    public newTextWatcher( EditText itemName, EditText itemPrice, Button addButton  ) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.addButton = addButton;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }


    /**
     * Enable the button if all the data is set
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {

        if (!this.itemName.getText().toString().isEmpty() &&
                !this.itemPrice.getText().toString().isEmpty()) {
            this.addButton.setEnabled(true);
            this.addButton.setBackgroundColor(Color.parseColor("#ff2ca1ee"));
        }
    }

}
