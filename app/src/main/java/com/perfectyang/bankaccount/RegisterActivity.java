package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.perfectyang.bankaccount.mydb.Mydatabase;


public class RegisterActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initDatabase();
    }

    private void initView() {
        username = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    public void onClick(View v) {
        int id = v.getId();
//        insert();
        login();
    }

    private void login () {
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = database.rawQuery(sql, new String[]{username.getText().toString(), password.getText().toString()});
        if (cursor.getCount() > 0) {
            Intent intent = new Intent(RegisterActivity.this, BankListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, "账号密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    public void register (View v) {
        String sql = "insert into user(username, password)values(?,?)";
        database.execSQL(sql, new Object[]{username.getText().toString(), password.getText().toString()});
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }


    public void onSearch (View v) {
        String sql = "select * from user";
        Cursor cursor = database.rawQuery(sql, new String[]{});
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            if (name.equalsIgnoreCase("perfectyang")) {
                Log.d("name===", name);
                Log.d("name===pwd---", pwd);
            }
        }
    }

    private void initDatabase() {
        Mydatabase mydatabase = new Mydatabase(RegisterActivity.this);
        database = mydatabase.getWritableDatabase();
    }





}