package com.example.a17494.yigong11;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;


import com.example.a17494.yigong11.Adapter.MainContentPagerAdapter;
import com.example.a17494.yigong11.Bean.LogInBean;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.cookie.RetrofitClient;

import rx.Subscriber;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RadioGroup mRadiogroup;
    private ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitClient.getInstance(MainActivity.this).Login(new Subscriber<LogInBean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(LogInBean logInBean) {
                Log.d("CCC",logInBean.getMsg()+"  "+logInBean.getCode());
            }
        }, "162210700000", "123456");

        initview();
        initListenter();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xffffff);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initListenter() {
        myViewPager.addOnPageChangeListener(this);
    }
    private void initview() {
        FragmentManager fragmentManager= this.getSupportFragmentManager();
        MainContentPagerAdapter fragmentAdapter=new MainContentPagerAdapter(fragmentManager);
        myViewPager=(ViewPager)this.findViewById(R.id.contentView);
        myViewPager.setAdapter(fragmentAdapter);
        mRadiogroup=(RadioGroup)this.findViewById(R.id.tabs_group);
        mRadiogroup.setOnCheckedChangeListener(this);
        mRadiogroup.check(R.id.today_tab);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=Constants.PAGER_TODAY;
        switch (checkedId){
            case R.id.today_tab:
                index=Constants.PAGER_TODAY;
                break;
            case R.id.mess_tab:
                index=Constants.PAGER_MESS;
                break;
            case R.id.mine_tab:
                index=Constants.PAGER_MINE;
                break;
        }
        myViewPager.setCurrentItem(index,false);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        //在页面被选中的时候切换tab状态
        switch (i){
            case Constants.PAGER_TODAY:
                mRadiogroup.check(R.id.today_tab);
                break;
            case Constants.PAGER_MESS:
                mRadiogroup.check(R.id.mess_tab);
                break;
            case Constants.PAGER_MINE:
                mRadiogroup.check(R.id.mine_tab);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
