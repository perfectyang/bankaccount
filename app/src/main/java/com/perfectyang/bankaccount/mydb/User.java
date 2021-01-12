package com.perfectyang.bankaccount.mydb;

public class User {
    private int id;
    private String username;
    private String password;


    public User (int id, String username, String password) {
       this.username = username;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
