package com.example.a17494.yigong11.myServices;



import com.example.a17494.yigong11.Bean.AllSignupersBean;
import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.Bean.LogInBean;
import com.example.a17494.yigong11.Bean.MyWorkBean;
import com.example.a17494.yigong11.Bean.OrderOrCancelBean;
import com.example.a17494.yigong11.Bean.UserInfoBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface MyServices {
    //获取所有有效的活动信息
    @GET("public/getworkinformation")
    Observable<AllWorkBean>getAllWorks();
    //根据活动ID获得活动信息
    @GET("public/getworkinformation")
    Observable<AllWorkBean>getAllWorkById(@Query("id") String work_no);

    //?workid=1
    //返回所有已经预约该活动的所有义工人员信息
    @GET("public/getworkattendbyworkid")
    Observable<AllSignupersBean> getSignedupers(@Query("workid") String work_no);

    //获取已经预约的活动(未完成的活动)
    @GET("student/getattendfinishbystuid")
    Observable<List<MyWorkBean>> getMySignedWork(@Query("studentid") String user_id);
    //获取已经参与的活动
    @GET("student/getattendbystuid")
    Observable<List<MyWorkBean>> getAttendWork(@Query("studentid") String user_id);


    //取消义工活动
    @FormUrlEncoded
    @POST("student/deleteattendwork")
    Observable<OrderOrCancelBean> cancelSignUp(@Field("studentid") String user_id, @Field("workid") String work_id);
   //修改义工信息

    @FormUrlEncoded
    @POST("student/updatestudentinformation")
    Observable<OrderOrCancelBean> changeUserInfo(@Field("studentid") String student_id, @Field("phone") String phone, @Field("major") String major, @Field("sex") String sex, @Field("inyear") String inYear);


    //用户登陆
    @FormUrlEncoded
    @POST("login")
    Observable<LogInBean> logoIn(@Field("username") String user_id, @Field("password") String password);
    //获取学生用户信息
    @GET("public/getstudentinformation")
    Observable<UserInfoBean> getUserInfo(@Query("studentid") String user_id);
    //预约义工活动
    @FormUrlEncoded
    @POST("student/orderwork")
    Observable<OrderOrCancelBean> orderWork(@Field("workid") String work_id,@Field("studentid") String user_id);
}
