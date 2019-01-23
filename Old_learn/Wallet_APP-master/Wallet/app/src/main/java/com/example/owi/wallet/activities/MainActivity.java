package com.example.owi.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.owi.wallet.DateHelper;
import com.example.owi.wallet.MenuHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.data.DataBase;
import com.example.owi.wallet.data.Items;

import java.util.List;

public class MainActivity extends Activity {

    public DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView account_balance_label = (TextView) findViewById(R.id.balance_label);
        TextView account_balance = (TextView) findViewById(R.id.account_balance);
        TextView limit_label = (TextView) findViewById(R.id.limitLabel);
        TextView limit_amount = (TextView) findViewById(R.id.limit);


        this.db = new DataBase(this);
        float limit = db.getLimit();

        account_balance.setText(String.valueOf(limit - getSum()) + " " + getResources().getString(R.string.currency));
        limit_amount.setText(limit + " " + getResources().getString(R.string.currency));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
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
     * Count how much money has been spent this mounth
     *
     * @return Sum
     */

    public Float getSum() {

        float count = 0;
        List<Items> allItems = db.getAllItemsByDate(DateHelper.getFirstDayOfTheMounth("yyyyMMdd"));

        for (int i = 0; i < allItems.size(); i++) {
            Items tmp = new Items();
            tmp = allItems.get(i);
            count += tmp.getPrice();
        }

        return count;
    }

}
