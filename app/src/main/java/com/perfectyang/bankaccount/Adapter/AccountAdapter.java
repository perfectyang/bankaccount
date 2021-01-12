package com.perfectyang.bankaccount.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.perfectyang.bankaccount.mydb.User;
import com.perfectyang.bankaccount.R;

import java.util.List;

public class AccountAdapter extends BaseAdapter {
    Context context;
    public List<User> mDatas;
    public LayoutInflater inflater;
    public AccountAdapter(@NonNull Context context, List<User> mDatas) {
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
            convertView = inflater.inflate(R.layout.bank_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        User curUser = mDatas.get(position);
        holder.username.setText(curUser.getUsername());
        holder.password.setText(curUser.getPassword());
        return convertView;
    }
    class ViewHolder{
        TextView username,password;
        public ViewHolder(View view){
            username = view.findViewById(R.id.username);
            password = view.findViewById(R.id.password);
        }
    }
}
