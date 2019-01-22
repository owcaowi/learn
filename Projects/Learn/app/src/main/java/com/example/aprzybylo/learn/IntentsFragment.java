package com.example.aprzybylo.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class IntentsFragment extends Fragment {

    public Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_intents, container, false);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity();
            }
        });

        return view;
    }


    public void goToActivity(){

        MapActivity newActivity = new MapActivity();

        Intent shareIntent = ShareCompat.IntentBuilder.from(newActivity)
                .setType("text/plain")
                .setText("Abcd")
                .getIntent();
        if (shareIntent.resolveActivity( getContext().getPackageManager()) != null ) {
            startActivity(shareIntent);
        }

    }

}
