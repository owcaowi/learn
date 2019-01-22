package com.example.aprzybylo.learn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StorageFragment extends Fragment {

	public StorageFragment() {
	}

	public static StorageFragment newInstance(String param1, String param2) {
		StorageFragment fragment = new StorageFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_storage, container, false);
	}


	public void setValueToSharedPreferences(){

		// Option 1
		// Use this if you need multiple shared preference files identified by name
		Context context = getActivity();
		SharedPreferences sharedPref = context.getSharedPreferences(
				getString(R.string.preference_file_key), Context.MODE_PRIVATE);

		// Option 2
		// Use this from an Activity if you need to use only one shared preference file for the activity.
//		SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(getString(R.string.saved_high_score_key), 7);
		//editor.commit();


		int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
		int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

		Log.d("","");

	}

}
