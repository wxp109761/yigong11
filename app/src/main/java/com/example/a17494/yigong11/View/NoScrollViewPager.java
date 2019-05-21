package com.example.a17494.yigong11.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }
    public NoScrollViewPager(@NonNull Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //不能滑动
        return false;//or false
        //可以滑动
        //return super.onTouchEvent(ev);
    }
}
