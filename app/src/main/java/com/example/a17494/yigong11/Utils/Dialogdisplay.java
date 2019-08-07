package com.example.a17494.yigong11.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a17494.yigong11.R;


public class Dialogdisplay extends Dialog {

    /*
     "work_no": "W1911021",
     "teacher_name": "孙星南",
     "work_hour": 2.0,
     "work_addr": "西区综合楼",
     "require_num": 4,
     "work_name": "扫楼梯",
     "tips": "无备注信息",
     "work_date": "2019-04-11 09:00:00",
     "signup_num": 0
      */
    protected EditText phone;
    protected EditText major;
    protected EditText college;
    protected EditText inYear;


    protected TextView mCanelBtn;
    protected TextView mConfirmBtn;

    public Dialogdisplay(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        // 是否可以撤销
        setContentView(R.layout.dialog_view);
        phone=findViewById(R.id.phone);
        major=findViewById(R.id.major);
        college=findViewById(R.id.college);
        inYear=findViewById(R.id.in_year);
        mCanelBtn = (TextView) findViewById(R.id.canel);
        mConfirmBtn = (TextView) findViewById(R.id.confirm);
    }
    public void setCanelBtnt(View.OnClickListener clickListener) {
        mCanelBtn.setOnClickListener(clickListener);
    }
    public void setConfirmBtn(View.OnClickListener clickListener) {
        mConfirmBtn.setOnClickListener(clickListener);
    }

    /**
     * 设置提示内容     *     * @param str 内容
     */
    public void setUserInfo(String u_phone,String u_major,String u_college,int u_inYear) {
        phone.setText(u_phone);
        major.setText(u_major);
        college.setText(u_college);
        inYear.setText(u_inYear+"");
    }
    public String getPhone() {
       return phone.getText()+"";
    }
    public String getMajor() {
        return major.getText()+"";
    }
    public String getCollege() {
        return college.getText()+"";
    }
    public int getInYear() {
        return Integer.parseInt(inYear.getText().toString());
    }



}


