package com.example.a17494.yigong11.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.FragmentCreater;

public class MainContentPagerAdapter extends FragmentPagerAdapter {

    public MainContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentCreater.getFragmentByPosition(position);
    }

    @Override
    public int getCount() {
        return Constants.TAB_COUNT;
    }
}
