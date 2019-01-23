package com.example.owi.wallet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.owi.wallet.activities.Add;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static String newDate = new String();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        DecimalFormat mFormat= new DecimalFormat("00");
        mFormat.setRoundingMode(RoundingMode.DOWN);
        newDate =  mFormat.format(Double.valueOf(year)) +
                mFormat.format(Double.valueOf(month)+1) + //Indexed from 0
                mFormat.format(Double.valueOf(day));

        ((TextView) getActivity().findViewById(R.id.editText)).setText(newDate);

    }

}
