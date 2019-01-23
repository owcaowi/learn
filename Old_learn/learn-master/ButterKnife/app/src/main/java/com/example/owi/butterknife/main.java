package com.example.owi.butterknife;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class main extends ActionBarActivity {

    /* CAST THE VIEW  */
    @Bind(R.id.hello)
    TextView textView;

    /* RESOURCE BINDING */
    @BindColor(R.color.my_blue)
    int blue;

    /* VIEW LISTS */
    @Bind({ R.id.first, R.id.second, R.id.third })
    List<EditText> list;

    /*
        ONCLICK LISTENER
        Add nullable if id might be not there
    */
    @Nullable
    @OnClick(R.id.hello)
    public void submit() {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textView.setText("click me");
        textView.setTextColor(blue);

        ButterKnife.apply(list, ALLCAPS);
        ButterKnife.apply(list, COLOR, blue);

    }


    static final ButterKnife.Action<TextView> ALLCAPS = new ButterKnife.Action<TextView>() {

        @Override public void apply(TextView view, int index) {
            view.setBackgroundColor(Color.YELLOW);

        }
    };

    static final ButterKnife.Setter<TextView, Integer> COLOR = new ButterKnife.Setter<TextView, Integer>() {
        @Override
        public void set(TextView view, Integer value, int index) {
            view.setTextColor(value);
        }
    };

}
