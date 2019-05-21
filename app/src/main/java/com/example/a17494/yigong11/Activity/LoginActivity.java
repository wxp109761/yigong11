package com.example.a17494.yigong11.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.a17494.yigong11.Bean.LogInBean;
import com.example.a17494.yigong11.Bean.UserInfoBean;
import com.example.a17494.yigong11.MainActivity;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.cookie.RetrofitClient;

import retrofit2.Retrofit;
import rx.Subscriber;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    /**
     * 账号
     */
    private EditText mLoginAccount;
    /**
     * 密码
     */
    private EditText mLoginPass;
    private Button btnLogin;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_in);
        context = this;
        initView();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xffffff);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    //初始化相关控件
    private void initView() {

        mLoginAccount = (EditText) findViewById(R.id.login_in_account);
        mLoginPass = (EditText) findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_btn:
                if (!mLoginAccount.getText().toString().equals("") && !mLoginPass.getText().toString().equals("")) {
                    RetrofitClient.getInstance(context).Login(new Subscriber<LogInBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(LogInBean logInBean) {
                            if(logInBean.getMsg().equals("成功")){
                                SpUtils.putString(getApplicationContext(), Constants.STU_ID,  mLoginAccount.getText().toString());
                                SpUtils.putString(getApplicationContext(), Constants.STU_PASS,  mLoginPass.getText().toString());
                                SpUtils.putString(getApplicationContext(), Constants.STU_JSESSIONID,logInBean.getData().getJSESSIONID());
                                SpUtils.putBoolean(getApplicationContext(),Constants.IS_LOGIN, true);
                                SaveUserInfo();
                                Intent intent=new Intent(context,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(context, "账户或密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, mLoginAccount.getText().toString(), mLoginPass.getText().toString());
                } else {
                    Toast.makeText(context, "账户或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
}
    private void SaveUserInfo(){
        RetrofitClient.getInstance(LoginActivity.this).getUserInfo(new Subscriber<UserInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserInfoBean userInfoBean) {
                SpUtils.putString(getApplicationContext(), Constants.STU_NAME,userInfoBean.getData().get(0).getName());
                //SpUtils.putString(getApplicationContext(), Constants.STU_SEX,userInfoBean.getData().get(0).getSex());
                SpUtils.putString(getApplicationContext(), Constants.STU_IN_YEAR,userInfoBean.getData().get(0).getInYear()+"");

                //telPhone.setText(entity.getPhone()+"");
               // college.setText(entity.getCollege());
                //major.setText(entity.getMajor());
               // regDate.setText(entity.getDate());
                //inYear.setText(entity.getInYear()+"");
            }
        }, mLoginAccount.getText().toString());
    }


}
