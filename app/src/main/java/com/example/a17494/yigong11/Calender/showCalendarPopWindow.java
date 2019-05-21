package com.example.a17494.yigong11.Calender;

import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.a17494.yigong11.Calender.bean.DateEntity;
import com.example.a17494.yigong11.Calender.utils.DataUtils;
import com.example.a17494.yigong11.Calender.view.DatePopupWindow;

import java.util.ArrayList;

public class showCalendarPopWindow {

    //日期控件变量
    private ArrayList<DateEntity> datas =new ArrayList<>();;
    private DatePopupWindow popupWindow ;
    private ArrayList<DateEntity> millisList =new ArrayList<>();;
    private String dataFormate = "yyyy-MM-dd";
    private String currentData ;
    /**
     * 显示日期控件
     * @param asPositionView
     */
    public void showCalendarPopupWindow(View rootView,View asPositionView) {
        popupWindow = new DatePopupWindow(rootView.getContext(),currentData);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(asPositionView);

        popupWindow.setOnItemClick(new DatePopupWindow.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                getData(date);
            }
        });
    }
    public String getData(String dateNumber){
        String dataSel = null;
        datas.clear();
        millisList.clear();
        if (TextUtils.isEmpty(dateNumber)){
            dateNumber = DataUtils.getCurrDate(dataFormate);
        }
        millisList = DataUtils.getWeek(dateNumber);
        if (millisList==null || millisList.size()<=0){
            return "";
        }
        datas.addAll(millisList);
        for (int i=0;i<millisList.size();i++){
            if (dateNumber.equals(millisList.get(i).date)){
                currentData = millisList.get(i).date;
                dataSel=currentData;
                //Toast.makeText(rootView.getContext(),currentData,Toast.LENGTH_SHORT).show();
            }
        }
        if (TextUtils.isEmpty(currentData)){
            currentData = millisList.get(0).date ;
            //Toast.makeText(rootView.getContext(),millisList.get(0).date,Toast.LENGTH_SHORT).show();
            dataSel=millisList.get(0).date;
        }
        return dataSel;
    }


}
