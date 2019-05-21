package com.example.a17494.yigong11.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private View rootview=null;
    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
       rootview=getSubView(inflater,container);
       setSubListenter();
        return rootview;
    }

    protected abstract void setSubListenter();
    protected abstract View getSubView(LayoutInflater inflater, ViewGroup container);
}
