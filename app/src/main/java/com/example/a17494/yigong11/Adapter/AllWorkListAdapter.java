package com.example.a17494.yigong11.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a17494.yigong11.Bean.AllWorkBean;
import com.example.a17494.yigong11.R;


import java.util.ArrayList;
import java.util.List;

public class AllWorkListAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private LayoutInflater inflater;
    public List<AllWorkBean.DataBean.IsonBean> mList;
    public List<AllWorkBean.DataBean.IsonBean> backList;
    MyFilter mFilter ;
    public AllWorkListAdapter(Context context, List<AllWorkBean.DataBean.IsonBean> mList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mList=mList;
        backList=mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_layout, null);
            holder=new ViewHolder();
            holder.worksImg=(ImageView)convertView.findViewById(R.id.works_icon);
            holder.addrName=(TextView)convertView.findViewById(R.id.works_name);

            holder.worksTime=(TextView)convertView.findViewById(R.id.works_info);
            convertView.setTag(holder);

        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        AllWorkBean.DataBean.IsonBean entiey=mList.get(position);
        holder.worksTime.setText(entiey.getStartTime());
        holder.addrName.setText(entiey.getWorkAddr());
        holder.worksImg.setBackgroundResource(R.drawable.ic_logo);
        return convertView;

    }

    @Override
    public Filter getFilter() {
        if (mFilter ==null){
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    static class ViewHolder {
        ImageView worksImg;
        TextView addrName;
        TextView worksTime;
    }

    private class MyFilter extends Filter {

        //定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();
            List<AllWorkBean.DataBean.IsonBean> list ;
            if (TextUtils.isEmpty(charSequence)){
                //当过滤的关键字为空的时候，我们则显示所有的数据
                list = backList;
                }else{
                //否则把符合条件的数据对象添加到集合中
                    list = new ArrayList<>();
                    for (AllWorkBean.DataBean.IsonBean entity:backList){
                        if ((entity.getWorkDepartment()+entity.getWorkCampus()+entity.getStartTime()).contains(charSequence)){
                            Log.d("Filter-->","performFiltering:"+entity.toString());
                            list.add(entity);
                        }
                    }
                }
                result.values = list;
                //将得到的集合保存到FilterResults的value变量中
                result.count = list.size();
                //将集合的大小保存到FilterResults的count变量中
                return result;
            }
            //告诉适配器更新界面
            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                mList = (List<AllWorkBean.DataBean.IsonBean>)filterResults.values;
                if (filterResults.count>0){
                    notifyDataSetChanged();
                //通知数据发生了改变
                    Log.d("Filter-->","publishResults:notifyDataSetChanged");
                }else {
                    notifyDataSetInvalidated();
                    //通知数据失效
                    Log.d("Filter-->","publishResults:notifyDataSetInvalidated");
                    }
            }
    }
}
