package com.example.a17494.yigong11.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a17494.yigong11.Utils.GlideImageLoader;
import com.example.a17494.yigong11.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment{
    private View rootView;


    private static List<Integer> looper_imges = new ArrayList<>();

    private Handler mHandler= new Handler();;
    private boolean mIsTouch = false;
    private LinearLayout pointContainer;
    static {
        looper_imges.add(R.drawable.lunbo1);
        looper_imges.add(R.drawable.lunbo2);
        looper_imges.add(R.drawable.lunbo3);
        looper_imges.add(R.drawable.lunbo4);
    }

    @Override
    protected void setSubListenter() {

    }
    @Override
    protected View getSubView(LayoutInflater inflater, ViewGroup container) {
        rootView=inflater.inflate(R.layout.home_page_view_fg,container,false);
        ImgViewLoopPage();
        return rootView;
    }


    private void ImgViewLoopPage(){
        Banner banner = (Banner) rootView.findViewById(R.id.banner);
        //设置banner样式
       // banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(looper_imges);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
       // banner.setBannerTitles(titles);
       // banner.setti
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }







}
