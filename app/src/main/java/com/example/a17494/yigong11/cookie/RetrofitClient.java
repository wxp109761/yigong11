package com.example.a17494.yigong11.cookie;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.a17494.yigong11.Bean.AllSignupersBean;
import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.Bean.HourBean;
import com.example.a17494.yigong11.Bean.LogInBean;
import com.example.a17494.yigong11.Bean.MyWorkBean;
import com.example.a17494.yigong11.Bean.ReturnResultBean;
import com.example.a17494.yigong11.Bean.UserInfoBean;
import com.example.a17494.yigong11.myServices.MyServices;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RetrofitClient
 * Created by Tamic on 2016-06-15.
 */
public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 5;

    private MyServices apiService;

    private OkHttpClient okHttpClient;

   public static final String  baseUrl=" http://biggsai.com/volunteer/";

    private static Context mContext;

    private static RetrofitClient sNewInstance;



    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(
                mContext);
    }

    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            Log.v("RetrofitClient", "-->error");
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }
        sNewInstance = new RetrofitClient(context, url);
        return sNewInstance;
    }

    private RetrofitClient(Context context) {

        this(context, null);
    }

    private RetrofitClient(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }


        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new NovateCookieManger(context))
                .addInterceptor(new ReceivedCookiesInterceptor(context)).addInterceptor(new AddCookiesInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        apiService = retrofit.create(MyServices.class);
    }

    public void getAllWorks(Subscriber<AllWorkBean> subscriber) {
        apiService.getAllWorks()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getAllWorksById(Subscriber<AllWorkBean> subscriber,String work_no) {
        apiService.getAllWorkById(work_no)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getSignedupers(Subscriber<AllSignupersBean> subscriber, String work_no) {
        apiService.getSignedupers(work_no)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getMysignedWork(Subscriber<List<MyWorkBean>> subscriber, String user_id) {
        apiService.getMySignedWork(user_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getAttendWork(Subscriber<List<MyWorkBean>> subscriber, String user_id) {
        apiService.getAttendWork(user_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void cancelSignUp(Subscriber<ReturnResultBean> subscriber, String work_no) {
        apiService.cancelSignUp(work_no)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void changeUserInfo(Subscriber<ReturnResultBean> subscriber, String phone, String major, String sex, String inYear) {
        apiService.changeUserInfo(phone,major,sex,inYear)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void Login(Subscriber<LogInBean> subscriber, String id, String pass) {
        apiService.logoIn(id,pass)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getUserInfo(Subscriber<UserInfoBean> subscriber,String student_id) {
        apiService.getUserInfo(student_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void orderWork(Subscriber<ReturnResultBean> subscriber, String work_id) {
        apiService.orderWork(work_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取所有义工工时
    public void getAllWorkHour(Subscriber<HourBean> subscriber,String student_id){
        apiService.getAllWorkHour(student_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取所有义工工时
    public void getWorkHourThisTerm(Subscriber<HourBean> subscriber,String student_id){
        apiService.getWorkHourofthisTerm(student_id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //修改用户密码
    public void rePassword(Subscriber<ReturnResultBean> subscriber, String student_id, String old_pass, String new_pass){
        apiService.rePassword(student_id,old_pass,new_pass)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
