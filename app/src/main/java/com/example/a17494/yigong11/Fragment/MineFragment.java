package com.example.a17494.yigong11.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Activity.HourStatisticActivity;
import com.example.a17494.yigong11.Activity.MyActionsActivity;
import com.example.a17494.yigong11.Activity.MyCollectActivity;
import com.example.a17494.yigong11.Activity.MyMessActivity;
import com.example.a17494.yigong11.Activity.SettingActivity;
import com.example.a17494.yigong11.Activity.UserInfoActivity;
import com.example.a17494.yigong11.R;

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private View rootView;

    private TextView mActions;
    private TextView mMess;
    private TextView hourStatistic;
    private TextView settings;
    private TextView user_info;
    private TextView mCollect;
    @Override
    protected void setSubListenter() {

    }
    @Override
    protected View getSubView(LayoutInflater inflater, ViewGroup container) {
        rootView=inflater.inflate(R.layout.mine_view_fg,container,false);
        initView();
        return rootView;
    }
    private void initView() {
        mActions = rootView.findViewById(R.id.my_action);
        hourStatistic=rootView.findViewById(R.id.hour_statistic);
        mMess=rootView.findViewById(R.id.my_mess);
        user_info=rootView.findViewById(R.id.use_info);
        mCollect = rootView.findViewById(R.id.my_collect);
        settings = rootView.findViewById(R.id.settings);

        mActions.setOnClickListener(this);
        mMess.setOnClickListener(this);
        hourStatistic.setOnClickListener(this);
        user_info.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        settings.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_action:
                Toast.makeText(rootView.getContext(),"我的活动",Toast.LENGTH_SHORT).show();
                initMyActions();
                break;
            case R.id.my_mess:
                Toast.makeText(rootView.getContext(),"我的消息,待开放",Toast.LENGTH_SHORT).show();
                //initMyMess();
                break;
            case R.id.hour_statistic:
                Toast.makeText(rootView.getContext(),"工时统计",Toast.LENGTH_SHORT).show();
                initHourStatistic();
                break;
            case R.id.use_info:
                Toast.makeText(rootView.getContext(),"个人信息",Toast.LENGTH_SHORT).show();
                initUserInfo();
                break;
            case R.id.my_collect:
                Toast.makeText(rootView.getContext(),"我的收藏,待开放",Toast.LENGTH_SHORT).show();
                //initMyCollect();
                break;
            case R.id.settings:
                Toast.makeText(rootView.getContext(),"设置",Toast.LENGTH_SHORT).show();
                initSettings();
                break;
        }
    }

    private void initMyActions() {
        Intent intent=new Intent(getActivity(),MyActionsActivity.class);
        startActivity(intent);
    }
    private void initMyMess() {
        Intent intent=new Intent(rootView.getContext(),MyMessActivity.class);
        startActivity(intent);
    }

    private void initHourStatistic() {
        Intent intent=new Intent(rootView.getContext(),HourStatisticActivity.class);
        startActivity(intent);
    }
    private void initUserInfo() {
        Intent intent=new Intent(rootView.getContext(),UserInfoActivity.class);
        startActivity(intent);
    }
    private void initMyCollect() {
        Intent intent=new Intent(getActivity(),MyCollectActivity.class);
        startActivity(intent);
    }
    private void initSettings() {
        Intent intent=new Intent(rootView.getContext(),SettingActivity.class);
        startActivity(intent);
    }

}
