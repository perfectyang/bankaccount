package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        imageButton = findViewById(R.id.addAccount);
        imageButton.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_account_list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addAccount) {
            navigateTo(AddAccountActivity.class);
        }
    }
}