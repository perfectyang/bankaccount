package com.perfectyang.bankaccount.activity;

import android.app.Application;

import com.perfectyang.bankaccount.mydb.DBManager;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }
}
