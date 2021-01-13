package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectyang.bankaccount.utils.CallBack;
import com.perfectyang.bankaccount.utils.DatePickerFragment;

public class AddAccountActivity extends BaseActivity implements View.OnClickListener {
    private TextView valid_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        valid_time = findViewById(R.id.valid_time);
        valid_time.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_add_account;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.valid_time:
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setCallBack(new CallBack() {
                    @Override
                    public void getResult(int year, int month, int dayOfMonth) {
                        showToast(year + "====" + (month + 1) + "====" + dayOfMonth);
                    }
                });
                break;
        }
    }
}