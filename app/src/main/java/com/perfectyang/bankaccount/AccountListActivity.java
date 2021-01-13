package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.perfectyang.bankaccount.Adapter.AccountAdapter;
import com.perfectyang.bankaccount.Adapter.BankListAdapter;
import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.DBManager;
import com.perfectyang.bankaccount.mydb.User;

import java.util.ArrayList;
import java.util.List;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {
    ImageButton imageButton;
    ListView bank_list;
    BankListAdapter adapter;
    ArrayList<BankAccount> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        imageButton = findViewById(R.id.addAccount);
        imageButton.setOnClickListener(this);
        getDataList();
        initView();
    }

    private void getDataList() {
        data = DBManager.accountList(findByKey("user_id"));
    }

    private void initView() {
        bank_list = findViewById(R.id.bank_list);
        adapter = new BankListAdapter(this, data);
        bank_list.setAdapter(adapter);
        bank_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<BankAccount> list = DBManager.accountList(findByKey("user_id"));
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_account_list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addAccount) {
            navigateTo(AddAccountActivity.class);
        }
    }
}