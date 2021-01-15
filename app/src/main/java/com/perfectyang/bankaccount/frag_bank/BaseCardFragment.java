package com.perfectyang.bankaccount.frag_bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.perfectyang.bankaccount.Adapter.BankListAdapter;
import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.mydb.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class BaseCardFragment extends Fragment implements View.OnClickListener {

    BankListAdapter adapter;
    List<BankAccount> bankAccountList;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_card, container, false);
        listView = view.findViewById(R.id.card_list);
        loadData();
        return view;
    }

    public void loadData() {
        bankAccountList = new ArrayList<>();
        adapter = new BankListAdapter(getContext(), bankAccountList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
