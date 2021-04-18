package com.perfectyang.bankaccount.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.perfectyang.bankaccount.frag_bank.CashCardFragment;
import com.perfectyang.bankaccount.frag_bank.CreditCardFragment;

import java.util.List;

public class ChooseBankAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragement;

    String[] title = {"银行卡", "信用卡"};
    int num1;
    int num2;

    public ChooseBankAdapter(@NonNull FragmentManager fm, List<Fragment> mFragement, int num1, int num2) {
        super(fm);
        this.mFragement = mFragement;
        Log.d("ssss", "" + num1);
        Log.d("ssss2", "" + num2);
        this.num1 = num1;
        this.num2 = num2;
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
//        Fragment fragment = mFragement.get(position);
//        if (position == 0) {
//            return title[position] + num1;
//        } else {
//            return title[position] + num2;
//        }
        return title[position];
    }
    public void setPageTitle(int position, String t) {
        title[position] = t;
//        notifyDataSetChanged();
    }
}
