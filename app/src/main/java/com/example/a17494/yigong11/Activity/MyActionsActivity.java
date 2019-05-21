package com.example.a17494.yigong11.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.a17494.yigong11.Fragment.MyAllActionsFragment;
import com.example.a17494.yigong11.Fragment.MyFinishFragment;
import com.example.a17494.yigong11.Fragment.MySignedFragment;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.TobarUtil;
import com.example.a17494.yigong11.Utils.ViewPagerHtil;

import java.util.ArrayList;
import java.util.List;

public class MyActionsActivity extends FragmentActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.my_actions_view);
        TobarUtil tobarUtil =(TobarUtil)findViewById(R.id.topbar);
        tobarUtil.setOnbtnClickListenter(new TobarUtil.OnbtnBackClickListenter() {
            @Override
            public void OnBackBtnClick() {
                finish();
            }
        });
        init();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xffffff);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
    }
    public void init(){
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager)findViewById(R.id.mViewPager);
        mTitle = new ArrayList<>();
        mTitle.add("所有活动");
        mTitle.add("已预约");
        mTitle.add("已完成");

        mFragment = new ArrayList<>();
        mFragment.add(new MyAllActionsFragment());
        mFragment.add(new MySignedFragment());
        mFragment.add(new MyFinishFragment());
        ViewPagerHtil.addTab(mTabLayout,mViewPager,mFragment,mTitle,getSupportFragmentManager());
    }

}
