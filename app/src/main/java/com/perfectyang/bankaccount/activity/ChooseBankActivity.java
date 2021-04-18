package com.perfectyang.bankaccount.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.perfectyang.bankaccount.Adapter.ChooseBankAdapter;
import com.perfectyang.bankaccount.R;
import com.perfectyang.bankaccount.frag_bank.CashCardFragment;
import com.perfectyang.bankaccount.frag_bank.CreditCardFragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankActivity extends BaseActivity {
    TabLayout tab_title;
    ViewPager view_pager;
    ChooseBankAdapter chooseBankAdapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        tab_title = findViewById(R.id.tab_title);
        view_pager = findViewById(R.id.view_pager);
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        initView();
    }

    private void initView() {
        List<Fragment> mFragment = new ArrayList<>();
        CashCardFragment cashCardFragment = new CashCardFragment(userId);
        CreditCardFragment creditCardFragment = new CreditCardFragment(userId);
        mFragment.add(cashCardFragment);
        mFragment.add(creditCardFragment);
        chooseBankAdapter = new ChooseBankAdapter(getSupportFragmentManager(), mFragment, cashCardFragment.getLen(), creditCardFragment.getLen());
        view_pager.setAdapter(chooseBankAdapter);
        tab_title.setupWithViewPager(view_pager);
        tab_title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               int idx = tab.getPosition();
               if (idx == 0) {
                   tab_title.getTabAt(0).setText("银行卡" + cashCardFragment.getLen());
               } else {
                   tab_title.getTabAt(idx).setText("信用卡" + creditCardFragment.getLen());
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_choose_bank;
    }
}