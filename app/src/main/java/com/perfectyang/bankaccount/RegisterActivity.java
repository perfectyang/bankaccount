package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectyang.bankaccount.mydb.Mydatabase;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private SQLiteDatabase database;
    private EditText username,password;
    private TextView register;
    private Button deleteBtn, loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDatabase();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    public void initView() {
        username = findViewById(R.id.user);
        password = findViewById(R.id.password);

        loginBtn = findViewById(R.id.login);
        register = findViewById(R.id.register);
        deleteBtn = findViewById(R.id.delete);

        loginBtn.setOnClickListener(this);
        register.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();

        String user = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if (user == null || user.length() == 0) {
            showToast("用户名不能为空!!");
        } else if (pwd == null || pwd.length() == 0) {
            showToast("密码不能为空!!");
        } else {
            if (id == R.id.login) {
                login(user, pwd);
            } else if (id == R.id.register) {
                register(user, pwd);
            } else if (id == R.id.delete) {
                deletUser(user, pwd);
            }

        }
    }

    private void login (String username, String password) {
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = database.rawQuery(sql, new String[]{username, password});
        if (cursor.getCount() > 0) {
            insertVal("token", username + password);
            navigateToWithFlag(BankListActivity.class,
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            showToast("登录成功");
        } else {
            showToast("登录失败");
        }
    }

    public void register (String username, String password) {
        String sql = "insert into user(username, password)values(?,?)";
        database.execSQL(sql, new Object[]{username, password});
        showToast("注册成功");
    }



    public int deletUser(String username, String password){
        int i = database.delete("user", "username=? and password=?", new String[]{username, password});
        showToast("删除成功");
        return i;
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