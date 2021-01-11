package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.perfectyang.bankaccount.mydb.Mydatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BankListActivity extends AppCompatActivity {
    private Button copy;
    private TextView tvw;
    private EditText editText;
    private ArrayList<String> data = new ArrayList<>();
    private ListView listvw;
    ClipboardManager clipboard;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        initDatabase();
        getAccountList();
        initView();
    }

    private void initDatabase() {
        Mydatabase mydatabase = new Mydatabase(BankListActivity.this);
        database = mydatabase.getWritableDatabase();
    }

    private void getAccountList () {
        String sql = "select * from user";
        Cursor cursor = database.rawQuery(sql, new String[]{});
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            data.add(name + "=" + pwd);
        }
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
        Toast.makeText(BankListActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
    }

    private void pasteCopy () {
        if (clipboard == null) {
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        String resultString = "";
        // 检查剪贴板是否有内容
        if (!clipboard.hasPrimaryClip()) {
            Toast.makeText(BankListActivity.this,"Clipboard is empty", Toast.LENGTH_SHORT).show();
        } else {
            ClipData clipData = clipboard.getPrimaryClip();
            int count = clipData.getItemCount();

            for (int i = 0; i < count; ++i) {

                ClipData.Item item = clipData.getItemAt(i);
                CharSequence str = item.coerceToText(BankListActivity.this);
                resultString += str;
            }
        }
        editText.setText(resultString);
    }

    private void initView() {
        listvw = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listvw.setAdapter(adapter);
        listvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = data.get(position);

                String[] list = str.split("=");
//                setCopy(str);
                int i = deletUser(list[0], list[1]);
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public int deletUser(String username, String password){
        int i = database.delete("user", "username=? and password=?", new String[]{username, password});
        return i;
    }


}