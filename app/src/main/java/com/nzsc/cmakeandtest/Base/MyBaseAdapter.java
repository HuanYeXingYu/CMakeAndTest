package com.nzsc.cmakeandtest.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public abstract class MyBaseAdapter<E> extends BaseAdapter {
    protected List<E> contentList;
    protected Context context;
    protected LayoutInflater inflater;

    public MyBaseAdapter(Context context, List<E> contentList) {
        this.contentList = contentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public E getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return getMyView(position, convertView, parent);
    }

    public abstract View getMyView(int position, View convertView, ViewGroup parent);
}
