package com.example.a17494.yigong11.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.example.a17494.yigong11.R;
import com.sctdroid.app.uikit.CurveView;

import java.util.HashSet;
import java.util.Set;

public class HourStatisticActivity extends Activity {
    int array[]={17,28,30,57};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_statistic);
        initView();
    }
    public void initView(){
        CurveView curveView = (CurveView) findViewById(R.id.curve_view);
        // mAdapter.notifyDataSetChanged();
        CurveView.Adapter mAdapter = new CurveView.Adapter() {
            String year[]={"2016-1017","2017-2018","2018-2019","2019-2010"};
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
                return 15;
            }
            /**
             * @return y 轴上限
             */
            @Override
            public int getMaxLevel() {
                return 80;
            }
            /**
             * 设置点上的文字，每个mark是一个，可同时设置点的 8 个方向的文字
             * 注意: Gravity 应使用 CurveView.Gravity 类
             *
             * @param position
             * @return
             */
            @Override
            public Set<CurveView.Mark> onCreateMarks(int position) {
                Set<CurveView.Mark> marks = new HashSet<CurveView.Mark>();
                CurveView.Mark mark = new CurveView.Mark(getLevel(position) + "°", Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 8, 0, 0);
                CurveView.Mark mark1 = new CurveView.Mark(getLevel(position) + "°", Gravity.START | Gravity.CENTER_HORIZONTAL, 0, 0, 0, 8);
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
