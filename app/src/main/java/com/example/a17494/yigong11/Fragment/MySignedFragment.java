package com.example.a17494.yigong11.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.a17494.yigong11.Activity.DetailsActivity;
import com.example.a17494.yigong11.Adapter.myWorkAdapter;
import com.example.a17494.yigong11.Bean.MyWorkBean;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.example.a17494.yigong11.myServices.MyServices;


import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MySignedFragment extends Fragment{
    public View view;
    private MyServices myServices;
    private List<MyWorkBean> mySignedBean;
    ListView lists;
    boolean flag=false;//是否已经预约该活动
    private myWorkAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.works_list, container,false);
       lists=view.findViewById(R.id.mlists);
       getDataFromServices();
        return view;
    }
    //获取数据
    private void getDataFromServices(){
        RetrofitClient.getInstance(view.getContext()).getMysignedWork(new Subscriber<List<MyWorkBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<MyWorkBean> workBeans) {
                Log.d("XX",workBeans.get(0).getStudentName());
                mySignedBean=workBeans;
                adapter = new myWorkAdapter(view.getContext(),mySignedBean);
                lists.setAdapter(adapter);
            }
        }, SpUtils.getString(MySignedFragment.super.getContext(),Constants.STU_ID));


        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(view.getContext(),DetailsActivity.class);
                intent.putExtra("data",adapter.mList.get(position).getWorkId()+"");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        getDataFromServices();
    }


}
