package com.example.owi.simplelistview;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by owi on 09.12.2015.
 */
public class myList extends ListView {

     public ListView list;

    public myList(Context context) {
        super(context);
       // this.list = (ListView) findViewById(R.id.listView);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            arg1.setSelected(true);
       }
   };

}
