package com.perfectyang.bankaccount.activity;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.perfectyang.bankaccount.Adapter.BankListAdapter;
import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.DBManager;

import java.util.ArrayList;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {
    ImageButton imageButton, user_info;
    ListView bank_list;
    BankListAdapter adapter;
    ArrayList<BankAccount> data = new ArrayList<>();
    ClipboardManager  clipboard;
    String userId;
    TextView get_more, tv_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        imageButton = findViewById(R.id.addAccount);
        imageButton.setOnClickListener(this);
        get_more = findViewById(R.id.get_more);
        get_more.setOnClickListener(this);
        user_info = findViewById(R.id.user_info);
        user_info.setOnClickListener(this);
        getDataList();
        initView();
    }

    private void getDataList() {
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        if (userId == null) {
            userId = findByKey("user_id");
        }
        data = DBManager.accountList(userId, 0);

    }

    private void initView() {
        bank_list = findViewById(R.id.bank_list);
        adapter = new BankListAdapter(this, data);
        bank_list.setAdapter(adapter);
        tv_empty = findViewById(R.id.tv_empty);
        bank_list.setEmptyView(tv_empty);
        bank_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Alert(data.get(position));
                return true;
            }
        });
    }

    private void Alert(BankAccount account) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("您确定要删除这条用户信息么？")
                .setCancelable(false)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = account.getId();
                        //执行删除的操作
                        DBManager.deletAccount(id);
                        data.remove(account);   //实时刷新，移除集合当中的对象
                        adapter.notifyDataSetChanged();   //提示适配器更新数据
                    }
                });
        builder.create().show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<BankAccount> list = DBManager.accountList(userId, 0);
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
        switch (id) {
            case R.id.addAccount:
                navigateWithParamTo(AddAccountActivity.class, userId);
                break;
            case  R.id.get_more:
                navigateWithParamTo(ChooseBankActivity.class, userId);
                break;
            case R.id.user_info:
                navigateWithParamTo(PersonActivity.class, userId);
                break;
        }
    }
}