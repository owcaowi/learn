package com.example.owi.wallet.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.owi.wallet.DateHelper;
import com.example.owi.wallet.R;
import com.example.owi.wallet.data.DataBase;
import com.example.owi.wallet.data.Items;
import com.example.owi.wallet.fonts.OSLightTextView;

import java.text.ParseException;
import java.util.List;


public class itemsListAdapter extends BaseAdapter {

    private Context context;
    private List<Items> data;
    private DataBase db;

    public itemsListAdapter(Context context, List<Items> data, DataBase db) {

        this.context = context;
        this.data = data;
        this.db = db;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_list_item, parent, false);
        }

        OSLightTextView listItemName = (OSLightTextView) convertView.findViewById(R.id.item_name);
        OSLightTextView listItemPrice = (OSLightTextView) convertView.findViewById(R.id.item_price);
        OSLightTextView listItemDate = (OSLightTextView) convertView.findViewById(R.id.item_date);

        //Set data
        try {
            String newDate = DateHelper.changeDataFormat(this.data.get(position).getDate().toString(), "dd.MM.yyyy", "yyyyMMdd");
            listItemDate.setText(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listItemName.setText(this.data.get(position).getName());
        listItemPrice.setText(this.data.get(position).getPrice().toString() + " " + convertView.getResources().getString(R.string.currency));

        // On delete button listener
        Button deleteButton = (Button)  convertView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = itemsListAdapter.this.data.get(position).getId();
                itemsListAdapter.this.db.deleteItem(id);
                itemsListAdapter.this.data.remove(position);
                itemsListAdapter.this.notifyDataSetChanged();
            }

        });



        return convertView;

    }

}
