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

    public static void saveBankAccount (BankAccount bankAccount) {
        ContentValues values = new ContentValues();
        values.put("user_id", bankAccount.getUser_id());
        values.put("bank_name", bankAccount.getBank_name());
        values.put("bank_number", bankAccount.getBank_number());
        values.put("valid_time", bankAccount.getValid_time());
        values.put("back_card_three", bankAccount.getBack_card_tree());
        database.insert("bankAccount", null, values);
    }

    /**
     * 获取访用户的银行卡信息
     * @param userId 用户id
     * @return ArrayList
     */

    public static ArrayList<BankAccount> accountList (String userId) {
        String sql = "select * from bankAccount where user_id=?";
        Cursor cursor = database.rawQuery(sql, new String[]{userId});
        ArrayList<BankAccount> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            BankAccount bank = new BankAccount(
                    cursor.getInt(cursor.getColumnIndex("user_id")),
                    cursor.getString(cursor.getColumnIndex("bank_name")),
                    cursor.getString(cursor.getColumnIndex("bank_number")),
                    cursor.getString(cursor.getColumnIndex("valid_time")),
                    cursor.getString(cursor.getColumnIndex("back_card_three")),
                    cursor.getInt(cursor.getColumnIndex("id"))
            );
            data.add(bank);
        }
        return data;
    }
    public static int deletAccount(int id) {
        int i = database.delete("bankAccount", "id=?", new String[]{id + ""});
        return i;
    }



}
