package com.example.aprzybylo.learn;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class OtherActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);

		setValueToSharedPreferences();

	}


	public void setValueToSharedPreferences(){

		// Option 1
		// Use this if you need multiple shared preference files identified by name
//		Context context = this;
//		SharedPreferences sharedPref = context.getSharedPreferences(
//				getString(R.string.preference_file_key), Context.MODE_PRIVATE);

		// Option 2
		// Use this from an Activity if you need to use only one shared preference file for the activity.
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(getString(R.string.saved_high_score_key), 7);
		editor.commit();


		int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
		int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

		Log.d("","");

	}

}
