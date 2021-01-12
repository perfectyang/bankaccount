package com.perfectyang.bankaccount.mydb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.perfectyang.bankaccount.FingerActivity;
import com.perfectyang.bankaccount.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/*
* 负责管理数据库的类
*   主要对于表当中的内容进行操作，增删改查
* */
public class DBManager {

    private static SQLiteDatabase database;
    /* 初始化数据库对象*/
    public static void initDB(Context context){
        Mydatabase helper = new Mydatabase(context);  //得到帮助类对象
        database = helper.getWritableDatabase();      //得到数据库对象
    }


    public static boolean login (String username, String password) {
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = database.rawQuery(sql, new String[]{username, password});
        if (cursor.getCount() > 0) {
           return true;
        } else {
            return false;
        }
    }

    public static void register (String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        database.insert("user",null,values);
    }



    public static int deletUser(int id){
        int i = database.delete("user", "id=?", new String[]{id + ""});
        return i;
    }



    public static ArrayList<User> userList () {
        String sql = "select * from user";
        Cursor cursor = database.rawQuery(sql, new String[]{});
        ArrayList<User> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            User user = new User(id, username, password);
            data.add(user);
        }
        return data;
    }

    public static User findUser (String username, String password) {
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = database.rawQuery(sql, new String[]{username, password});
        List<User> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String user = cursor.getString(cursor.getColumnIndex("username"));
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            User localUser = new User(id, username, pwd);
            data.add(localUser);
        }
        return data.get(0);
    }


}
