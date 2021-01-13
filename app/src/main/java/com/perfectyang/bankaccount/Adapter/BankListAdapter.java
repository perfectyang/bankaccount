package com.perfectyang.bankaccount.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.mydb.BankAccount;
import com.perfectyang.bankaccount.mydb.User;

import java.util.List;

public class BankListAdapter extends BaseAdapter {
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
        holder.bank_number.setText(curUser.getBank_number());
        holder.valid_time.setText(curUser.getValid_time());
        holder.bank_card_three.setText(curUser.getBack_card_tree());
        return convertView;
    }
    class ViewHolder{
        TextView bank_name,bank_number, valid_time, bank_card_three;
        public ViewHolder(View view){
            bank_name = view.findViewById(R.id.bank_name);
            bank_number = view.findViewById(R.id.bank_number);
            valid_time = view.findViewById(R.id.valid_time);
            bank_card_three = view.findViewById(R.id.bank_card_three);
        }
    }
}
