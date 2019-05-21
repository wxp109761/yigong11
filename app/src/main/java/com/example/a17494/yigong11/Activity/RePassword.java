package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a17494.yigong11.Bean.ReturnResultBean;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.Utils.TobarUtil;
import com.example.a17494.yigong11.cookie.RetrofitClient;

import rx.Subscriber;

public class RePassword extends Activity implements View.OnClickListener {

    private EditText oldPass;
    private EditText newPass;
    private EditText confirmPass;
    private Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repassword);
        initView();
        TobarUtil tobarUtil =(TobarUtil)findViewById(R.id.topbar);
        tobarUtil.setOnbtnClickListenter(new TobarUtil.OnbtnBackClickListenter() {
            @Override
            public void OnBackBtnClick() {
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


    public void initView(){
        oldPass = findViewById(R.id.old_pass);
        newPass=findViewById(R.id.new_pass);
        confirmPass=findViewById(R.id.confirm_pass);
        confirmBtn=findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.confirm_btn:
               initRePass();
               break;
        }
    }
    public void initRePass(){
        if(oldPass.getText().toString().equals("")||newPass.getText().toString().equals("")||confirmPass.getText().toString().equals("")){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else {
            if(!oldPass.getText().toString().equals(SpUtils.getString(getApplicationContext(),Constants.STU_PASS))){
                Toast.makeText(this,"原密码输入错误，请重新输入",Toast.LENGTH_SHORT).show();
            }else{
                if(!newPass.getText().toString().equals(confirmPass.getText().toString())){
                    Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }else{
                    if(newPass.getText().toString().equals(oldPass.getText().toString())){
                        Toast.makeText(this,"新旧密码不能一样，请重新输入",Toast.LENGTH_SHORT).show();
                    }else {
                    RetrofitClient.getInstance(getApplicationContext()).rePassword(new Subscriber<ReturnResultBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ReturnResultBean returnResultBean) {
                            if(returnResultBean.getMsg().equals("成功")){
                                Toast.makeText(RePassword.this,"密码修改成功！",Toast.LENGTH_SHORT).show();
                                StartLogInView();
                            }
                        }
                    },SpUtils.getString(getApplicationContext(),Constants.STU_ID),oldPass.getText().toString(),newPass.getText().toString());
                }
                }
            }

        }
    }
    public void StartLogInView(){
        final boolean isLogin = SpUtils.getBoolean(getApplicationContext(), Constants.IS_LOGIN);
        if (isLogin) {
            SpUtils.clear(getApplicationContext());
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

}
