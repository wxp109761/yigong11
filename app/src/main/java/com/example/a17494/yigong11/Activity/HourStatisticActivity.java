package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.example.a17494.yigong11.Adapter.myWorkAdapter;
import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.Bean.MyWorkBean;
import com.example.a17494.yigong11.Bean.WorkBean;
import com.example.a17494.yigong11.Bean.hourDisplayTableBean;
import com.example.a17494.yigong11.Fragment.MyAllActionsFragment;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.TimerPicker.TimePickerDialog;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.sctdroid.app.uikit.CurveView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Subscriber;

public class HourStatisticActivity extends Activity {
    private SmartTable table;
    int array[] = {24, 28, 24, 20};
    private Dialog startTimeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_statistic);
        //设置数据
        table = findViewById(R.id.table);
        //普通列
        final Column<String> num = new Column<>("序号", "num");
        final Column<Integer> activity_name = new Column<>("活动名称", "activity_name");
        final Column<Integer> activity_time = new Column<>("活动时间", "activity_time");
        final Column<Integer> hour = new Column<>("活动工时", "hour");

        //设置该列当字段相同时自动合并
        // city.setAutoMerge(true);
        //设置单元格内容
        final List<hourDisplayTableBean> list = new ArrayList<>();

        RetrofitClient.getInstance(HourStatisticActivity.this).getAttendWork(new Subscriber<List<MyWorkBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<MyWorkBean> attendWorkBeans) {
                TableData<hourDisplayTableBean> tableData = null;

                for (int i = 0; i <attendWorkBeans.size() ; i++) {
                    final int finalI = i;
                    RetrofitClient.getInstance(HourStatisticActivity.this).getAllWorkNoDiff(new Subscriber<List<WorkBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<WorkBean> workBeans) {
                            list.add(new hourDisplayTableBean(finalI,workBeans.get(0).getName(),workBeans.get(0).getStartTime(),workBeans.get(0).getWorkHour()+""));

                        }
                    },attendWorkBeans.get(i).getWorkId());
                    tableData= new TableData<>("表格名", list,num,activity_name,activity_time,hour);
                }



                table.setTableData(tableData);
                table.getConfig().setContentStyle(new FontStyle(50, Color.BLUE));
                table.getConfig().setShowXSequence(false);
                table.getConfig().setHorizontalPadding(1);
            }
        }, SpUtils.getString(HourStatisticActivity.this,Constants.STU_ID));

        initView();
    }

    public void initView() {
        CurveView curveView = (CurveView) findViewById(R.id.curve_view);
        // mAdapter.notifyDataSetChanged();


/*
        TimePickerDialog.Builder builder = new TimePickerDialog.Builder(this);
        startTimeDialog = builder.setOnTimeSelectedListener(new TimePickerDialog.OnTimeSelectedListener() {
            @Override
            public void onTimeSelected(String[] times) {
                Toast.makeText(HourStatisticActivity.this,times[0]+":"+times[1],Toast.LENGTH_SHORT).show();

            }
        }).create();

        startTimeDialog.show();

*/


        CurveView.Adapter mAdapter = new CurveView.Adapter() {
            String year[] = {"2016-2017", "2017-2018", "2018-2019", "2019-2020"};

            /**
             * @return 点的数量
             */
            @Override
            public int getCount() {
                return 4;
            }

            /**
             * level 是 y 轴高度，在 minLevel 和 maxLevel 之间
             * @param position
             * @return 返回当前 position 的 level
             */
            @Override
            public int getLevel(int position) {
                return array[position];
            }

            /**
             * @return y 轴下限
             */
            @Override
            public int getMinLevel() {
                return 18;
            }

            /**
             * @return y 轴上限
             */
            @Override
            public int getMaxLevel() {
                return 30;
            }

            /**
                CurveView.Mark mark = new CurveView.Mark(getLevel(position) + "", Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 8, 0, 0);
                CurveView.Mark mark1 = new CurveView.Mark(getLevel(position) + "", Gravity.START | Gravity.CENTER_HORIZONTAL, 0, 0, 0, 8);
                marks.add(mark);
                marks.add(mark1);
                return marks;
            }

            /**
             * 获取第 i 个点 x 轴上的文字
             * @param i
             * @return
             */
            @Override
            public String getXAxisText(int i) {
                return year[i];
            }
        };
        curveView.setAdapter(mAdapter);
    }


}
