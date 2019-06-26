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

public class PagerAdapter extends FragmentPagerAdapter {

   final int PAGE_COUNT = 3;
   final Context context;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
               fragment = InfoFragment.newInstance();
               break;
            case 1 :
                fragment = CriteriaFragment.newInstance();
                break;
            case 2 :
                fragment = RejectFragment.newInstance();
                break;
        }
        return fragment;
    }

    public CharSequence getPqageTitle(int position) {
        switch (position) {
            case 0:
                return "INFO";
            case 1:
                return "CRITERIA";
            case 2:
                return "REJECT";
        }
        return null;
    }


    //Mengembalikan jumlah tampilan yang tersedia.
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
