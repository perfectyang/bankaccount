package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.DBManager;
import com.perfectyang.bankaccount.utils.CallBack;
import com.perfectyang.bankaccount.utils.DatePickerFragment;

public class AddAccountActivity extends BaseActivity implements View.OnClickListener {
    private TextView valid_time;
    private Button save_btn;
    private EditText bank_name, bank_number, bank_card_three;
    private BankAccount bankAccount = new BankAccount();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        valid_time = findViewById(R.id.valid_time);
        valid_time.setOnClickListener(this);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);
        bank_name = findViewById(R.id.bank_name);
        bank_number = findViewById(R.id.bank_number);
        bank_card_three = findViewById(R.id.bank_card_three);
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
                        valid_time.setText(year + "" + (month + 1) + "" + dayOfMonth);
                        bankAccount.setValid_time(year + "" + (month + 1) + "" + dayOfMonth);
                        showToast(year + "====" + (month + 1) + "====" + dayOfMonth);
                    }
                });
                break;
            case R.id.save_btn:
                bankAccount.setBack_card_tree(bank_card_three.getText().toString().trim());
                bankAccount.setBank_name(bank_name.getText().toString().trim());
                bankAccount.setBank_number(bank_number.getText().toString().trim());
                bankAccount.setUser_id(Integer.parseInt(findByKey("user_id")));
                DBManager.saveBankAccount(bankAccount);
                showToast("添加成功");
                finish();
                break;
        }
    }
}