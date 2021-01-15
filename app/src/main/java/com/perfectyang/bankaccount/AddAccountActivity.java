package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
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
import com.perfectyang.bankaccount.utils.DialogTimer;

public class AddAccountActivity extends BaseActivity implements View.OnClickListener {
    private TextView valid_time;
    private Button save_btn, cancel_btn;
    private EditText bank_name, bank_number, bank_card_three;
    private BankAccount bankAccount = new BankAccount();
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        valid_time = findViewById(R.id.valid_time);
        valid_time.setOnClickListener(this);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);

        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);

        bank_name = findViewById(R.id.bank_name);
        bank_number = findViewById(R.id.bank_number);
        bank_card_three = findViewById(R.id.bank_card_three);

        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
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
                DialogTimer dialogTimer = new DialogTimer(this);
                dialogTimer.show();
                dialogTimer.setOnEnsureListener(new DialogTimer.OnEnsureListener() {
                    @Override
                    public void onEnsure(int year, String month, String day) {
                        valid_time.setText(month + "" + day);
                        bankAccount.setValid_time(month + "" + day);
                        showToast(year + "====" + month + "====" + day);
                    }
                });
                break;
            case R.id.save_btn:
                bankAccount.setBack_card_tree(bank_card_three.getText().toString().trim());
                bankAccount.setBank_name(bank_name.getText().toString().trim());
                bankAccount.setBank_number(bank_number.getText().toString().trim());
                bankAccount.setUser_id(Integer.parseInt(userId));
                DBManager.saveBankAccount(bankAccount);
                showToast("添加成功");
                finish();
                break;
            case R.id.cancel_btn:
                finish();
                break;
        }
    }
}