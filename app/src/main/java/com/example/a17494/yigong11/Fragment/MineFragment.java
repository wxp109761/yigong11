package com.example.a17494.yigong11.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Activity.HourStatisticActivity;
import com.example.a17494.yigong11.Activity.MyActionsActivity;
import com.example.a17494.yigong11.Activity.MyCollectActivity;
import com.example.a17494.yigong11.Activity.MyMessActivity;
import com.example.a17494.yigong11.Activity.SettingActivity;
import com.example.a17494.yigong11.Activity.UserInfoActivity;
import com.example.a17494.yigong11.Bean.HourBean;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import rx.Subscriber;


public class MineFragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    ImageView headerImg;
    private TextView userName;
    private TextView mActions;
    private TextView mMess;
    private TextView hourStatistic;
    private TextView settings;
    private TextView user_info;
    private TextView mCollect;
    private TextView mAllHour;
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
        headerImg=rootView.findViewById(R.id.header_img);
        userName=rootView.findViewById(R.id.user_name);
        mAllHour = rootView.findViewById(R.id.all_hour);
        mActions = rootView.findViewById(R.id.my_action);
        hourStatistic=rootView.findViewById(R.id.hour_statistic);
        mMess=rootView.findViewById(R.id.my_mess);
        user_info=rootView.findViewById(R.id.use_info);
        mCollect = rootView.findViewById(R.id.my_collect);
        settings = rootView.findViewById(R.id.settings);
        headerImg.setOnClickListener(this);
        mAllHour.setOnClickListener(this);
        mActions.setOnClickListener(this);
        mMess.setOnClickListener(this);
        hourStatistic.setOnClickListener(this);
        user_info.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        settings.setOnClickListener(this);
        Log.d("GGG",SpUtils.getString(getContext(),Constants.STU_NAME)+"");
        userName.setText(SpUtils.getString(getContext(),Constants.STU_NAME));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_img:
                changeHeaderImg();
                break;
            case R.id.all_hour:
                Toast.makeText(rootView.getContext(),"gongshi",Toast.LENGTH_SHORT).show();
               getAllHour();
                break;
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

    private void changeHeaderImg() {
        String stringItems[]={"拍照","从相册选择"};
        final ActionSheetDialog dialog = new ActionSheetDialog(rootView.getContext(), stringItems, null);
        dialog.isTitleShow(true).show();
        dialog.title("更换头像");
        dialog.itemPressColor(Color.parseColor("#e9857d"));
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(rootView.getContext(),"更换头像",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(rootView.getContext(),"从相册选择",Toast.LENGTH_SHORT).show();
                        break;
                }
                dialog.dismiss();
            }
        });

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

    public void getAllHour() {
        RetrofitClient.getInstance(getContext()).getAllWorkHour(new Subscriber<HourBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HourBean hourBean) {

            }
        },SpUtils.getString(getContext(),Constants.STU_ID));
    }
}
