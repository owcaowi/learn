package com.example.aprzybylo.learn.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.aprzybylo.learn.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimePickerFragment extends DialogFragment {

    public static final String RETURN_TIME = "returnTime";
    private static final String ARG_TIME = "time";

    @BindView(R.id.dialog_time_picker)
    TimePicker mTimePicker;


    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ){

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        ButterKnife.bind(this, view);

        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        setDate(date);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Speichern",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = mTimePicker.getCurrentHour();
                                int minute = mTimePicker.getCurrentMinute();

                                Time time = new Time(hour,minute, 0);

                                sendResult(Activity.RESULT_OK, time);
                            }
                        })
                .create();

    }


    public void setDate( Date date ){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);

    }


    private void sendResult(int resultCode, Time time) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(RETURN_TIME, time);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
