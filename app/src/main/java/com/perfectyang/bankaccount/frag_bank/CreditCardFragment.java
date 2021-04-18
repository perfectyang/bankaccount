package com.perfectyang.bankaccount.frag_bank;

import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.DBManager;

import java.util.List;

public class CreditCardFragment extends BaseCardFragment {
    String userId;
    public CreditCardFragment (String userId) {
        this.userId = userId;
    }
    @Override
    public void loadData () {
        super.loadData();
        List<BankAccount> inlist = DBManager.accountList(userId, 1);
        bankAccountList.addAll(inlist);
        adapter.notifyDataSetChanged();
    }
}