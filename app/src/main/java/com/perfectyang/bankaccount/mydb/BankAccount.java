package com.perfectyang.bankaccount.mydb;

public class BankAccount {
    private int id;
    private int user_id;
    private String bank_name;
    private String bank_number;
    private String valid_time;
    private String back_card_tree;


    public BankAccount () {}

    public BankAccount(int user_id, String bank_name, String bank_number, String valid_time, String back_card_tree) {
        this.user_id = user_id;
        this.bank_name = bank_name;
        this.bank_number = bank_number;
        this.valid_time = valid_time;
        this.back_card_tree = back_card_tree;
    }

    public BankAccount(int user_id, String bank_name, String bank_number, String valid_time, String back_card_tree, int id) {
        this.user_id = user_id;
        this.bank_name = bank_name;
        this.bank_number = bank_number;
        this.valid_time = valid_time;
        this.back_card_tree = back_card_tree;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBank_number() {
        return bank_number;
    }

    public String getValid_time() {
        return valid_time;
    }

    public String getBack_card_tree() {
        return back_card_tree;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public void setValid_time(String valid_time) {
        this.valid_time = valid_time;
    }

    public void setBack_card_tree(String back_card_tree) {
        this.back_card_tree = back_card_tree;
    }






}
