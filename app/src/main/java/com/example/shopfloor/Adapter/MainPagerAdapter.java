package com.example.shopfloor.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shopfloor.Activity.DetailOpenDocActivity;
import com.example.shopfloor.Fragment.CriteriaFragment;
import com.example.shopfloor.Fragment.InfoFragment;
import com.example.shopfloor.Fragment.RejectFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitleFragments = new ArrayList<>();


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void addFragments(Fragment fragment, String title) {
        mFragments.add(fragment);
        mTitleFragments.add(title);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public CharSequence getPageTitle(int position) {
        return mTitleFragments.get(position);
    }
}
