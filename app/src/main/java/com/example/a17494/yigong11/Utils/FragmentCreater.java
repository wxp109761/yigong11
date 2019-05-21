package com.example.a17494.yigong11.Utils;

import android.support.v4.app.FragmentActivity;


import com.example.a17494.yigong11.Fragment.ActionFragment;
import com.example.a17494.yigong11.Fragment.BaseFragment;
import com.example.a17494.yigong11.Fragment.HomePageFragment;
import com.example.a17494.yigong11.Fragment.MineFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentCreater extends FragmentActivity {
    public  static Map<Integer,BaseFragment> sCashes=new HashMap<>();
    public static BaseFragment getFragmentByPosition(int position){
        BaseFragment baseFragment=sCashes.get(position);
        if(baseFragment!=null){
            return  baseFragment;
        }
        switch (position){
            case Constants.PAGER_TODAY:
                baseFragment=new HomePageFragment();
                break;
            case Constants.PAGER_MESS:
                baseFragment=new ActionFragment();
                break;
            case Constants.PAGER_MINE:
                baseFragment=new MineFragment();
                break;

        }
        sCashes.put(position,baseFragment);
        return baseFragment;
    }
}
