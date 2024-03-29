package com.perfectyang.bankaccount.frag_bank;
import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.DBManager;

import java.util.List;

public class CashCardFragment extends BaseCardFragment {
    String userId;
    public int len;
    public CashCardFragment (String userId) {
        this.userId = userId;
    }

    @Override
    public void loadData () {
        super.loadData();
        List<BankAccount> inlist = DBManager.accountList(userId, 2);
        bankAccountList.addAll(inlist);
        adapter.notifyDataSetChanged();
        len = inlist.size();
    }

    public int getLen () {
        return len;
    }

}