package com.example.a17494.yigong11.Fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Activity.ConsultActivity;
import com.example.a17494.yigong11.Activity.HourStatisticActivity;
import com.example.a17494.yigong11.Activity.WebpageGuideActivity;
import com.example.a17494.yigong11.Utils.GlideImageLoader;
import com.example.a17494.yigong11.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment implements View.OnClickListener{
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
        init();
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
    public void init(){
        TextView hour_check=rootView.findViewById(R.id.hour_check);
        TextView psy_consult=rootView.findViewById(R.id.psy_consult);
        TextView academic_guide=rootView.findViewById(R.id.academic_guide);
        TextView web_page_guide=rootView.findViewById(R.id.web_page_guide);
        TextView more=rootView.findViewById(R.id.more);
        TextView action_remind=rootView.findViewById(R.id.action_remind);
        hour_check.setOnClickListener(this);
        psy_consult.setOnClickListener(this);
        academic_guide.setOnClickListener(this);
        web_page_guide.setOnClickListener(this);
        more.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hour_check:
                Toast.makeText(rootView.getContext(),"工时查看",Toast.LENGTH_SHORT).show();
                Intent intent_hour=new Intent(rootView.getContext(),HourStatisticActivity.class);
                startActivity(intent_hour);
                break;
            case R.id.psy_consult:
                Toast.makeText(rootView.getContext(),"心理咨询",Toast.LENGTH_SHORT).show();
                Intent intent_psy=new Intent(rootView.getContext(),ConsultActivity.class);
                startActivity(intent_psy);
                break;
            case R.id.academic_guide:
                Toast.makeText(rootView.getContext(),"学业指导",Toast.LENGTH_SHORT).show();
                Intent intent_academic=new Intent(rootView.getContext(),ConsultActivity.class);
                startActivity(intent_academic);
                break;
            case R.id.web_page_guide:
                Intent intent_web_guide=new Intent(rootView.getContext(),WebpageGuideActivity.class);
                startActivity(intent_web_guide);
                Toast.makeText(rootView.getContext(),"网址导航",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more:
                Intent intent_more=new Intent(rootView.getContext(),HourStatisticActivity.class);
                startActivity(intent_more);
                Toast.makeText(rootView.getContext(),"更多功能，待开放",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
