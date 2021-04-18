package com.perfectyang.bankaccount.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.perfectyang.bankaccount.R;

import java.io.File;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    Button login_out;
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        login_out = findViewById(R.id.login_out);
        user_name = findViewById(R.id.user_name);
        String token = findByKey("token");
        user_name.setText(token);
        login_out.setOnClickListener(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_person;
    }


    private void login_out () {
//        deleteFilesByDirectory(new File("/data/data/" + getApplicationContext().getPackageName() + "/shared_prefs"));
        insertVal("token", "");
        insertVal("user_id", "");
        navigateToWithFlag(RegisterActivity.class,
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.login_out:
               login_out();
       }
    }
}