package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Bean.AllSignupersBean;
import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.Bean.ReturnResultBean;
import com.example.a17494.yigong11.Bean.WorkBean;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.ProgressBar;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.Utils.TobarUtil;
import com.example.a17494.yigong11.cookie.RetrofitClient;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

public class DetailsActivity extends Activity {

    private ReturnResultBean signedBean;
    private ReturnResultBean cancelBean;
    private Button signUp;


    private RelativeLayout relativeLayout;
    private WorkBean entity;

    boolean isUser_idSignedWork_noFlag=false;
    boolean isUser_idCompleteWork_noFlag=false;
    private int flagId=0;
    private ProgressBar progressBar;
    private Button cancelBtn;
    private TextView timeLeft;
    private TobarUtil tobarUtil;
    private ImageView detailBg;
    private ImageView actionsImg;
    private TextView workHour;
    private TextView tips;
    private TextView workName;
    private TextView requireNum;
    private TextView signNum;
    private LinearLayout actionTitleContainer;
    private TextView title;
    private TextView workNo;
    private TextView publisherId;
    private TextView publishTime;
    private TextView workAddr;
    private TextView workDate;
    private TextView workDept;
    private TextView workCampus;
    private TextView workTips;
    private Button mycollect;
    private LinearLayout actionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deatil_view);
        String work_id= getIntent().getStringExtra("data");
        Log.d("CCC",work_id+"CC");
        initView();
        getDataFromService(work_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(0xffffff);
        }
    }
    private void initView(){
        progressBar= (ProgressBar) findViewById(R.id.sign_in_view);
        List<String> viewData = new ArrayList<>();
        viewData.add("报名");
        viewData.add("完成");
        progressBar.setSignInData(viewData);
        relativeLayout= (RelativeLayout) findViewById(R.id.action_process);
        tobarUtil = findViewById(R.id.topbar);
        detailBg = findViewById(R.id.detail_bg);
        actionsImg = findViewById(R.id.action_img);
        workHour = findViewById(R.id.work_hour);
        tips = findViewById(R.id.tips);
        timeLeft = findViewById(R.id.time_left);
        workName = findViewById(R.id.work_name);
        requireNum = findViewById(R.id.require_num);
        signNum = findViewById(R.id.sign_num);
        actionTitleContainer = findViewById(R.id.action_title_container);
        title = findViewById(R.id.title);
        workNo = findViewById(R.id.work_no);
        publisherId = findViewById(R.id.publisher_id);
        publishTime = findViewById(R.id.publish_time);
        workAddr = findViewById(R.id.work_addr);
        workDate = findViewById(R.id.work_date);
        workDept = findViewById(R.id.work_dept);
        workCampus = findViewById(R.id.work_campus);
        workTips = findViewById(R.id.work_tips);
        actionDetail = findViewById(R.id.action_detail);
        tobarUtil.setOnbtnClickListenter(new TobarUtil.OnbtnBackClickListenter() {
            @Override
            public void OnBackBtnClick() {
                finish();
            }
        });

        mycollect= findViewById(R.id.my_collect);
        signUp = findViewById(R.id.sign_up_action);

    }
    private void getDataFromService(String workId){
        RetrofitClient.getInstance(DetailsActivity.this).getAllWorkNoDiff(new Subscriber<List<WorkBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<WorkBean> workBeans) {
                Log.d("CCC", workBeans.get(0).getAttendNum()+"CC");
                entity= workBeans.get(0);

                workHour.setText(entity.getWorkHour()+"");
                workName.setText(entity.getName());
                requireNum.setText("需要人数："+entity.getNeedNum());
                signNum.setText("已报名："+entity.getAttendNum()+"人");
                workNo.setText("活动编号："+entity.getId());
                publisherId.setText("发布者ID："+entity.getPublisherId());
                publishTime.setText("发布时间："+entity.getPublishTime());
                workAddr.setText("活动地点："+entity.getWorkAddr());
                workDate.setText("活动时间："+entity.getStartTime());
                workTips.setText("活动标签："+entity.getWorkTip());
                workDept.setText("活动部门："+entity.getWorkDepartment());
                workCampus.setText("活动校区："+entity.getWorkCampus());
                new TimeThread().start();
                isSignedWorks(entity.getId()+"");
            }
        },Integer.parseInt(workId));

    }
    private void isSignedWorks(String work_id){

        RetrofitClient.getInstance(DetailsActivity.this).getSignedupers(new Subscriber<AllSignupersBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AllSignupersBean allSignupersBean) {
                for (int i = 0; i <allSignupersBean.getData().size() ; i++) {
                    if((allSignupersBean.getData().get(i).getStudentId()+"").equals(SpUtils.getString(getApplicationContext(),Constants.STU_ID))){

                        if(!allSignupersBean.getData().get(i).isIsfinished()){
                            isUser_idSignedWork_noFlag=true;
                        }else{
                            isUser_idCompleteWork_noFlag=true;
                        }
                    }
                }
                if(isUser_idSignedWork_noFlag){
                    addCancelBtn();
                    progressBar.signInEvent();
                    signUp.setText("待完成");
                    signUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //startActivity(new Intent(DetailsActivity.this,MyQRCodeActivity.class));
                        }
                    });
                    Log.d("ZZZ", "该用户已经预约该活动");
                }else{
                    Handle();
                    Log.d("ZZZ", "该用户未预约该活动");
                }


                if(isUser_idCompleteWork_noFlag){
                    progressBar.currentSignTag=2;
                    progressBar.signInEvent();
                    signUp.setText("已完成");
                    signUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //startActivity(new Intent(DetailsActivity.this,MyQRCodeActivity.class));
                        }
                    });
                    Log.d("ZZZ", "该用户已经完成该活动");
                }else{
                    Handle();
                    Log.d("ZZZ", "该用户未完成该活动");
                }
            }
        },work_id);
    }

    private void Handle() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.d("GGG",SpUtils.getString(getApplicationContext(),Constants.STU_ID));


                RetrofitClient.getInstance(DetailsActivity.this).orderWork(new Subscriber<ReturnResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ReturnResultBean returnResultBean) {
                        Log.d("FFF", returnResultBean.getMsg()+"  "+ returnResultBean.getData()+"  "+ returnResultBean.getCode());
                        signedBean = returnResultBean;
                        if (signedBean.getMsg().equals("成功")) {
                            addCancelBtn();
                            progressBar.signInEvent();
                            signUp.setText("待完成");
                            Toast.makeText(DetailsActivity.this, "预约成功", Toast.LENGTH_SHORT).show();
                            signUp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //  startActivity(new Intent(DetailsActivity.this, MyQRCodeActivity.class));
                                }
                            });
                        }else {
                            if(entity.getNeedNum()==entity.getAttendNum()){
                                Toast.makeText(DetailsActivity.this, "预约人满", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailsActivity.this, signedBean.getData(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                },entity.getId() + "");
            }
        });
    }
    public void addCancelBtn(){
        cancelBtn = new Button(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 110);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
       cancelBtn.setText("取消预约");
       relativeLayout.addView(cancelBtn,layoutParams);
       cancelBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RetrofitClient.getInstance(DetailsActivity.this).cancelSignUp(new Subscriber<ReturnResultBean>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(ReturnResultBean returnResultBean) {
                       cancelBean = returnResultBean;
                       if (cancelBean.getMsg().equals("成功")) {
                           Toast.makeText(DetailsActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                           progressBar.resetSignView();
                           signUp.setText("报名");
                           cancelBtn.setVisibility(View.GONE);
                           Handle();
                       }
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               if (cancelBean.getCode().equals("失败")) {
                                   Toast.makeText(DetailsActivity.this, cancelBean.getData(), Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                   }
               }, entity.getId() + "");
           }
       });
    }

    public class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);

        }
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date d1 = df.parse(entity.getStartTime());
                        Date d2 = df.parse(format.format(date));
                        long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                        long days = diff / (1000 * 60 * 60 * 24);
                        long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                        long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                        timeLeft.setText(days+"天"+hours+"时"+minutes+"分");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    break;
            }
            return false;
        }
    });

}