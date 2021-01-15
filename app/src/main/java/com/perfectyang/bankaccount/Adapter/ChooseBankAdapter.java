package com.perfectyang.bankaccount.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ChooseBankAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragement;
    String[] title = {"银行卡", "信用卡"};

    public ChooseBankAdapter(@NonNull FragmentManager fm, List<Fragment> mFragement) {
        super(fm);
        this.mFragement = mFragement;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragement.get(position);
    }

    @Override
    public int getCount() {
        return mFragement.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
