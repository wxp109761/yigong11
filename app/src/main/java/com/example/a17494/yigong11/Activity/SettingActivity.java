package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.Utils.TobarUtil;

public class SettingActivity extends Activity implements View.OnClickListener {
    private TextView changePass;
    private TextView clearCash;
    private TextView detectionUpdate;
    private TextView about;



    private Button LogoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_view);
        TobarUtil tobarUtil =(TobarUtil)findViewById(R.id.topbar);


        changePass=findViewById(R.id.change_password);
        clearCash=findViewById(R.id.clear_cash);
        detectionUpdate=findViewById(R.id.detection_update);
        about=findViewById(R.id.about);
        LogoutBtn=findViewById(R.id.logout_btn);
        tobarUtil.setOnbtnClickListenter(new TobarUtil.OnbtnBackClickListenter() {
            @Override
            public void OnBackBtnClick() {
                finish();
            }
        });

        changePass.setOnClickListener(this);
        clearCash.setOnClickListener(this);
        detectionUpdate.setOnClickListener(this);
        about.setOnClickListener(this);
        LogoutBtn.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0xffffff);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_password :

                break;
            case R.id.clear_cash :

                break;
            case R.id.detection_update:

                break;
            case R.id.about:

                break;
            case R.id.logout_btn:
                Logout();
                break;

        }
    }
    public void Logout(){
        final boolean isLogin = SpUtils.getBoolean(getApplicationContext(), Constants.IS_LOGIN);
        if (isLogin) {
            SpUtils.clear(getApplicationContext());
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
