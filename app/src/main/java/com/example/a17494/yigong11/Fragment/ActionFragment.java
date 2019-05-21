package com.example.a17494.yigong11.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.example.a17494.yigong11.Activity.DetailsActivity;
import com.example.a17494.yigong11.Adapter.AllWorkListAdapter;
import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.Calender.bean.DateEntity;
import com.example.a17494.yigong11.Calender.utils.DataUtils;
import com.example.a17494.yigong11.Calender.view.DatePopupWindow;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.qlh.dropdownmenu.DropDownMenu;
import com.qlh.dropdownmenu.view.SingleMenuView;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;

public class ActionFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private View rootView;
    private String header[]={"部门","校区","日期"};
    private  String campus[]={"不限","东校区","西校区","南校区"};
    private String departSelected="不限";
    private String campusSelected="不限";
    private List<View> popupViews = new ArrayList<>();
    private DropDownMenu mDropDownMenu;
    private SingleMenuView departSingleMenuView;//单级菜单

    private SingleMenuView campusSingleMenuView;//单级菜单
    //内容视图
    private View contentView;

    //日期控件变量
    private ArrayList<DateEntity> datas ;
    private DatePopupWindow popupWindow ;
    private ArrayList<DateEntity> millisList ;
    private String dataFormate = "yyyy-MM-dd";
    private String currentData ;


    private List<AllWorkBean.DataBean.IsonBean> entity=null;
    private AllWorkListAdapter adapter=null;
    private ListView listView=null;
    @Override
    protected void setSubListenter() {
    }
    @Override
    protected View getSubView(LayoutInflater inflater, ViewGroup container) {
        rootView=inflater.inflate(R.layout.action_view_fg,container,false);
        initView();
        return rootView;
    }
    private void initView() {
        RetrofitClient.getInstance(rootView.getContext()).getAllWorks(new Subscriber<AllWorkBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AllWorkBean allWorkBean) {
                entity= allWorkBean.getData().getIson();
                adapter=new AllWorkListAdapter(rootView.getContext(),entity);
                listView.setAdapter(adapter);
            }

        });

        mDropDownMenu = (DropDownMenu) rootView.findViewById(R.id.DropMenu);
        datas = new ArrayList<>();
        millisList = new ArrayList<>();

        initMenus();
        initListener();
        listView.setId(0);
        listView.setOnItemClickListener(this);
        listView.setTextFilterEnabled(true);
    }

    public void initFilter(){
        if (departSelected.equals("不限")&&campusSelected.equals("不限")){
            listView.clearTextFilter();
        }else if(departSelected.equals("不限")&&!campusSelected.equals("不限")){
            listView.setFilterText(campusSelected);
            //去除黑框
            contentView.dispatchDisplayHint(View.INVISIBLE);
        }else if(!departSelected.equals("不限")&&campusSelected.equals("不限")){
            listView.setFilterText(departSelected);
            //去除黑框
            contentView.dispatchDisplayHint(View.INVISIBLE);
        }else {
            listView.setFilterText(departSelected+campusSelected);
            //去除黑框
            contentView.dispatchDisplayHint(View.INVISIBLE);
        }
    }
    private void initMenus() {

        departSingleMenuView = new SingleMenuView(ActionFragment.super.getContext(),getResources().getStringArray(R.array.department));
        popupViews.add(departSingleMenuView);
        campusSingleMenuView = new SingleMenuView(ActionFragment.super.getContext(),campus);
        popupViews.add(campusSingleMenuView);
        popupViews.add( new SingleMenuView(ActionFragment.super.getContext(),header));
        popupViews.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarPopupWindow(campusSingleMenuView);
            }
        });

        //初始化内容视图
        contentView = LayoutInflater.from(ActionFragment.super.getContext()).inflate(R.layout.content_view,null);
        listView=contentView.findViewById(R.id.action_list);
        //装载
        mDropDownMenu.setDropDownMenu(Arrays.asList(header),popupViews,contentView);
    }

    private void initListener() {

        departSingleMenuView.setOnSelectListener(new SingleMenuView.OnSelectListener() {
            @Override
            public void getValue(int position, String showText) {
                mDropDownMenu.setTabText(showText);
                mDropDownMenu.closeMenu();
                departSelected=showText;
                initFilter();
            }
        });
        campusSingleMenuView.setOnSelectListener(new SingleMenuView.OnSelectListener() {
            @Override
            public void getValue(int position, String showText) {
                mDropDownMenu.setTabText(showText);
                mDropDownMenu.closeMenu();
                campusSelected=showText;
                initFilter();
            }
        });
    }





    /**
     * 显示日期控件
     * @param v
     */
    private void showCalendarPopupWindow(View v) {
        popupWindow = new DatePopupWindow(rootView.getContext(),currentData);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(v);

      popupWindow.setOnItemClick(new DatePopupWindow.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                getData(date);
            }
        });
    }
    public void getData(String dateNumber){
        datas.clear();
        millisList.clear();
        if (TextUtils.isEmpty(dateNumber)){
            dateNumber = DataUtils.getCurrDate(dataFormate);
        }
        millisList = DataUtils.getWeek(dateNumber);
        if (millisList==null || millisList.size()<=0){
            return;
        }
       datas.addAll(millisList);
        for (int i=0;i<millisList.size();i++){
            if (dateNumber.equals(millisList.get(i).date)){
                currentData = millisList.get(i).date;

                Toast.makeText(rootView.getContext(),currentData,Toast.LENGTH_SHORT).show();
            }
        }
        if (TextUtils.isEmpty(currentData)){
            currentData = millisList.get(0).date ;
            Toast.makeText(rootView.getContext(),millisList.get(0).date,Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case 0:
                Intent intent=new Intent(rootView.getContext(),DetailsActivity.class);
                intent.putExtra("data",adapter.mList.get(position).getId()+"");
                startActivity(intent);
                break;
        }
    }
}
