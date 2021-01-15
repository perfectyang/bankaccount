package com.perfectyang.bankaccount.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectyang.bankaccount.Adapter.AccountAdapter;
import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.mydb.DBManager;
import com.perfectyang.bankaccount.mydb.User;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends BaseActivity {
    private Button copy;
    private TextView tvw;
    private EditText editText;
    private List<User> data = new ArrayList<>();
    private AccountAdapter adapter;
    private ListView listvw;
    ClipboardManager clipboard;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        getAccountList();
        initView();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_user_list;
    }

    private void getAccountList () {
          data = DBManager.userList();
    }

    private void setCopy (String content) {
        if (clipboard == null) {
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        //创建ClipData对象
        //第一个参数只是一个标记，随便传入。
        //第二个参数是要复制到剪贴版的内容
        ClipData clip = ClipData.newPlainText(content, content);
        //传入clipdata对象.
        clipboard.setPrimaryClip(clip);
        Toast.makeText(UserListActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
    }

    private void pasteCopy () {
        if (clipboard == null) {
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        String resultString = "";
        // 检查剪贴板是否有内容
        if (!clipboard.hasPrimaryClip()) {
            Toast.makeText(UserListActivity.this,"Clipboard is empty", Toast.LENGTH_SHORT).show();
        } else {
            ClipData clipData = clipboard.getPrimaryClip();
            int count = clipData.getItemCount();

            for (int i = 0; i < count; ++i) {

                ClipData.Item item = clipData.getItemAt(i);
                CharSequence str = item.coerceToText(UserListActivity.this);
                resultString += str;
            }
        }
        editText.setText(resultString);
    }

    private void initView() {
        listvw = findViewById(R.id.listView);
        adapter = new AccountAdapter(this, data);
        listvw.setAdapter(adapter);
        listvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = data.get(position);
                navigateWithParamTo(AccountListActivity.class, user.getId() + "");
//                Alert(user);
            }
        });
    }

    private void Alert(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("您确定要删除这条用户信息么？")
                .setCancelable(false)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = user.getId();
                        //执行删除的操作
                        DBManager.deletUser(id);
                        data.remove(user);   //实时刷新，移除集合当中的对象
                        adapter.notifyDataSetChanged();   //提示适配器更新数据
                    }
                });
        builder.create().show();
    }


}