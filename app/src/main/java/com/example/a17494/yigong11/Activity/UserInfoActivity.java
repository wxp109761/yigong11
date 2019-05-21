package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Bean.ReturnResultBean;
import com.example.a17494.yigong11.Bean.UserInfoBean;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.Dialogdisplay;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.Utils.TobarUtil;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.example.a17494.yigong11.myServices.MyServices;

import rx.Subscriber;

public class UserInfoActivity extends Activity {
    private MyServices myServices;
    private UserInfoBean.DataBean entity;
    private TextView userId;
    private TextView userName;
    private TextView sex;
    private TextView telPhone;
    private TextView college;
    private TextView major;
    private TextView regDate;
    private TextView inYear;
    private TobarUtil tobarUtil;
    private Dialogdisplay dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_view);
        initView();
        getDataFormApi();
        dialog = new Dialogdisplay(UserInfoActivity.this);

        tobarUtil.setOnbtnClickListenter(new TobarUtil.OnbtnBackClickListenter() {
            @Override
            public void OnBackBtnClick() {
                dialog.dismiss();
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xffffff);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void getDataFormApi(){
        RetrofitClient.getInstance(UserInfoActivity.this).getUserInfo(new Subscriber<UserInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserInfoBean userInfoBean) {
                entity=userInfoBean.getData().get(0);
                userId.setText(SpUtils.getString(getApplicationContext(),Constants.STU_ID));
                userName.setText(entity.getName());
                sex.setText(entity.getSex());
                telPhone.setText(entity.getPhone()+"");
                college.setText(entity.getCollege());
                major.setText(entity.getMajor());
                regDate.setText(entity.getDate());
                inYear.setText(SpUtils.getString(getApplicationContext(),Constants.STU_IN_YEAR));
                tobarUtil.setOnSettingClickListenter(new TobarUtil.OnbtnSettingClickListenter() {
                    @Override
                    public void OnSettingClick() {
                        changeInfo(SpUtils.getString(getApplicationContext(),Constants.STU_ID),entity.getPhone()+"",entity.getMajor(),entity.getSex(),entity.getInYear()+"");
                    }
                });
            }
        }, SpUtils.getString(getApplicationContext(),Constants.STU_ID));
    }

    private void initView(){
        userId = findViewById(R.id.user_id);
        userName=findViewById(R.id.user_name);
        sex=findViewById(R.id.sex);
        telPhone=findViewById(R.id.tel_phone);
        college = findViewById(R.id.college);
        major=findViewById(R.id.major);
        regDate=findViewById(R.id.reg_date);
        inYear=findViewById(R.id.in_year);
        tobarUtil = (TobarUtil) findViewById(R.id.topbar);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();

        }
    }
    private void changeInfo(final String studentId, String phone, String major, String college, String inYear){
        dialog.show();
        dialog.setUserInfo(phone,major,college,inYear);
        dialog.setCanelBtnt(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setConfirmBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserInfoActivity.this, "保存", Toast.LENGTH_SHORT).show();

                String phone=dialog.getPhone();
                String major=dialog.getMajor();
                String college=dialog.getCollege();
                String inYear=dialog.getInYear();
                RetrofitClient.getInstance(UserInfoActivity.this).changeUserInfo(new Subscriber<ReturnResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReturnResultBean returnResultBean) {

                    }
                }, phone, major, college, inYear);

                dialog.dismiss();
                getDataFormApi();
            }
        });
    }





}
