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

import com.perfectyang.bankaccount.mydb.DBManager;
import com.perfectyang.bankaccount.mydb.Mydatabase;
import com.perfectyang.bankaccount.mydb.User;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private SQLiteDatabase database;
    private EditText username,password;
    private TextView register;
    private Button deleteBtn, loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIsLogin();
        initView();
    }

    private void initIsLogin() {
        String token = findByKey("token");
//        if(!token.isEmpty()) {
//            navigateToWithFlag(FingerActivity.class,
//                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
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
//                deletUser(user, pwd);
//                DBManager.register(user, pwd);
            }

        }
    }

    private void login (String username, String password) {
        boolean bool = DBManager.login(username, password);
        if (bool) {
            insertVal("token", username + password);
//            User user = DBManager.findUser(username, password);
//            insertVal("user_id", user.getId() + "");
            navigateToWithFlag(AccountListActivity.class,
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            showToast("登录成功");
        } else {
            showToast("登录失败");
        }
    }

    public void register (String username, String password) {
        DBManager.register(username, password);
        showToast("注册成功");
    }





}