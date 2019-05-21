package com.example.a17494.yigong11.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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


public class MyFinishFragment extends Fragment implements AdapterView.OnItemClickListener {
    public View view;

    private List<MyWorkBean> myFinishedBean;
    private ListView lists;
    private myWorkAdapter adapter=null;
    private Button cancelBtn;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.works_list, container,false);
        lists=view.findViewById(R.id.mlists);
        lists.setTextFilterEnabled(true);
        getDataFromService();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                filterView();
            }
            },0);
        lists.dispatchDisplayHint(View.INVISIBLE);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    private void getDataFromService(){
        RetrofitClient.getInstance(view.getContext()).getAttendWork(new Subscriber<List<MyWorkBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(List<MyWorkBean> workBeans) {
                Log.d("XXCC",workBeans.get(0).isIsfinished()+"");
                lists.setTextFilterEnabled(true);
                myFinishedBean=workBeans;
                adapter = new myWorkAdapter(view.getContext(),myFinishedBean);
                lists.setAdapter(adapter);
            }
        },SpUtils.getString(MyFinishFragment.super.getContext(),Constants.STU_ID));
        lists.setId(0);
        lists.setOnItemClickListener(this);
    }
    public void filterView() {
        lists.setFilterText("true");
        //去除黑框
        lists.dispatchDisplayHint(View.INVISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case 0:
                Intent intent=new Intent(view.getContext(),DetailsActivity.class);
                intent.putExtra("data",adapter.mList.get(position).getWorkId()+"");
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataFromService();
    }
}
