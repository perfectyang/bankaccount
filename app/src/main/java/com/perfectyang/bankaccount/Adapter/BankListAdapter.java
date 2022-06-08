package com.perfectyang.bankaccount.Adapter;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.utils.ShareText;

import java.util.List;

public class BankListAdapter extends BaseAdapter {
    ClipboardManager clipboard;
    Context context;
    public List<BankAccount> mDatas;
    public LayoutInflater inflater;
    public BankListAdapter(@NonNull Context context, List<BankAccount> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bank_list_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        BankAccount curUser = mDatas.get(position);
        holder.bank_name.setText(curUser.getBank_name());
        holder.bank_number.setText(curUser.getBank_number() + "  (" + curUser.getBank_number().length() + "位)");
        holder.valid_time.setText(curUser.getValid_time());
        holder.bank_card_three.setText(curUser.getBack_card_tree());
        ViewHolder finalHolder = holder;
        holder.bank_card_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCopy(finalHolder.bank_card_three.getText().toString());
            }
        });
        holder.bank_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCopy(curUser.getBank_number());
            }
        });
        holder.valid_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCopy(finalHolder.valid_time.getText().toString());
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareText.share(curUser.getBank_name() + "： " + curUser.getBank_number() + "  杨国唯", context);
            }
        });



        int category = curUser.get_category();
        if (category == 2) {
            holder.bank_card_three_wrap.setVisibility(View.GONE);
            holder.valid_time_wrap.setVisibility(View.GONE);
            holder.card_mark.setText("储蓄卡");
        } else {
            holder.card_mark.setText("信用卡");
            holder.bank_card_three_wrap.setVisibility(View.VISIBLE);
            holder.valid_time_wrap.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
    class ViewHolder{
        TextView bank_name,bank_number, valid_time, bank_card_three, card_mark, share;
        LinearLayout bank_card_three_wrap, valid_time_wrap;
        public ViewHolder(View view){
            bank_name = view.findViewById(R.id.bank_name);
            bank_number = view.findViewById(R.id.bank_number);
            valid_time = view.findViewById(R.id.valid_time);
            card_mark = view.findViewById(R.id.card_mark);
            bank_card_three = view.findViewById(R.id.bank_card_three);
            bank_card_three_wrap = view.findViewById(R.id.bank_card_three_wrap);
            valid_time_wrap = view.findViewById(R.id.valid_time_wrap);
            share = view.findViewById(R.id.share);
        }
    }
    private void setCopy (String content) {
        if (clipboard == null) {
            clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        //创建ClipData对象
        //第一个参数只是一个标记，随便传入。
        //第二个参数是要复制到剪贴版的内容
        ClipData clip = ClipData.newPlainText(content, content);
        //传入clipdata对象.
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "复制成功:" + content, Toast.LENGTH_SHORT).show();
    }
}
