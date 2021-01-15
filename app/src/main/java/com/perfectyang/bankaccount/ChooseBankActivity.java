package com.perfectyang.bankaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.perfectyang.bankaccount.Adapter.ChooseBankAdapter;
import com.perfectyang.bankaccount.frag_bank.CashCardFragment;
import com.perfectyang.bankaccount.frag_bank.CreditCardFragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankActivity extends BaseActivity {
    TabLayout tab_title;
    ViewPager view_pager;
    ChooseBankAdapter chooseBankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        tab_title = findViewById(R.id.tab_title);
        view_pager = findViewById(R.id.view_pager);
        initView();
    }

    private void initView() {
        List<Fragment> mFragment = new ArrayList<>();
        CashCardFragment cashCardFragment = new CashCardFragment();
        CreditCardFragment creditCardFragment = new CreditCardFragment();
        mFragment.add(cashCardFragment);
        mFragment.add(creditCardFragment);
        chooseBankAdapter = new ChooseBankAdapter(getSupportFragmentManager(), mFragment);
        view_pager.setAdapter(chooseBankAdapter);
        tab_title.setupWithViewPager(view_pager);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_choose_bank;
    }
}