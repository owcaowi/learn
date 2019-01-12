package com.example.aprzybylo.learn;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aprzybylo.learn.Date.DatePickerFragment;
import com.example.aprzybylo.learn.Date.TimePickerFragment;
import com.example.aprzybylo.learn.Utils.DateTimeUtils;

import java.sql.Time;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DateDialogFragment extends Fragment {


    private static final String DIALOG_DATE = "DialogDate";

    private static final int DATE_REQUEST_CODE = 0;
    private static final int TIME_REQUEST_CODE = 1;

    @BindView(R.id.date_view)
    protected TextView mDateView;

    @BindView(R.id.time_view)
    protected TextView mTimeView;

    @OnClick(R.id.show_date_picker)
    void onShowDatePicker (View view ){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment dialog = DatePickerFragment.newInstance(new Date(0));
        dialog.setTargetFragment(DateDialogFragment.this, DATE_REQUEST_CODE);
        dialog.show(manager, DIALOG_DATE);
    }

    @OnClick(R.id.show_time_picker)
    void onShowTimePicker( View view ){
        FragmentManager manager = getFragmentManager();
        TimePickerFragment dialog = TimePickerFragment.newInstance(new Date(0));
        dialog.setTargetFragment(DateDialogFragment.this, TIME_REQUEST_CODE);
        dialog.show(manager, DIALOG_DATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        /* Set Date */
        if (requestCode == DATE_REQUEST_CODE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.RETURN_DATE);
            String dateInFormat = DateTimeUtils.formatDateToPattern(date, DateTimeUtils.PATTERN_1);
            mDateView.setText(dateInFormat);
        }

        /* Set Time */
        if (requestCode == TIME_REQUEST_CODE) {
            Time time = (Time) data.getSerializableExtra(TimePickerFragment.RETURN_TIME);
            String timeInFormat = DateTimeUtils.formatDateToPattern(time, DateTimeUtils.PATTERN_2);
            mTimeView.setText(timeInFormat);
        }
    }

}
