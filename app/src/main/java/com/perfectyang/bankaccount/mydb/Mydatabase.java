package com.perfectyang.bankaccount.mydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    private static final String name = "bank.db";
    private static final int version = 1;

    public Mydatabase(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id integer primary key autoincrement, username varchar(10), password varchar(10))";
        db.execSQL(sql);
        //创建银行卡信息
        String sqlbank = "create table bankAccount(id integer primary key autoincrement," +
                "user_id integer, bank_name varchar(10),bank_number varchar(30), valid_time varchar(8)," +
                "back_card_three varchar(5), category integer)";
        db.execSQL(sqlbank);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
