package com.perfectyang.bankaccount.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import javax.security.auth.callback.Callback;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    CallBack callBack;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("设置日期", year + "--" + month + "----" + dayOfMonth);
        callBack.getResult(year, month, dayOfMonth);
    }
    public void setCallBack (CallBack callBack) {
        this.callBack = callBack;
    }



}
