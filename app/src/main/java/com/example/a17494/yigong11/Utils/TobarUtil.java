package com.example.a17494.yigong11.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.a17494.yigong11.R;

public class TobarUtil extends LinearLayout implements View.OnClickListener {
    private Button back_btn;
    private TextView title;
    private Button setting;
    private OnbtnBackClickListenter backListenter;
    private OnbtnSettingClickListenter settingListenter;
    private int back_btn_bg=0xff890c85;;
    private String titleText="xx";
    private String settingText="xx";
    private float titleTextSize=14;
    //设置监听器
    public  void setOnbtnClickListenter(OnbtnBackClickListenter listenters){
        this.backListenter =listenters;
    }
    //按钮点击接口
    public interface OnbtnBackClickListenter {
        void OnBackBtnClick();

    }
    //设置监听器
    public  void setOnSettingClickListenter(OnbtnSettingClickListenter listenter){
        this.settingListenter =listenter;
    }
    //按钮点击接口
    public interface OnbtnSettingClickListenter {
        void OnSettingClick();
    }
    //设置按钮点击可见性

    public TobarUtil(Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.tobar,this);
        back_btn= findViewById(R.id.back);
        title=findViewById(R.id.title_text);
        setting=findViewById(R.id.setting);
        back_btn.setOnClickListener(this);
        setting.setOnClickListener(this);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        back_btn_bg = ta.getResourceId(R.styleable.TopBar_leftBackground,0);
        titleText = ta.getString(R.styleable.TopBar_titleText);
        settingText=ta.getString(R.styleable.TopBar_settingText);
        titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,0);
        // int titleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 00574);
        //释放资源
        ta.recycle();
        initViews();
    }

    private void initViews() {
        back_btn.setBackgroundResource(back_btn_bg);
        title.setText(titleText);
        title.setTextSize(titleTextSize);
        setting.setText(settingText);
        //titleText.setTextColor(titleTextColor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                if(backListenter !=null){
                    backListenter.OnBackBtnClick();
                }
                break;
            case R.id.setting:
                if(settingListenter !=null){
                    settingListenter.OnSettingClick();
                }
                break;
        }
    }
}
