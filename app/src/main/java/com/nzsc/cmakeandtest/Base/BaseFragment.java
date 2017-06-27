package com.nzsc.cmakeandtest.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzsc.cmakeandtest.Activity.MainActivity;
import com.nzsc.cmakeandtest.Adapter.MyViewPagerAdapter;

import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public abstract class BaseFragment extends Fragment {
    public Unbinder unbinder;
    public View rootView; // 缓存Fragment view
    public MyViewPagerAdapter myViewPagerAdapter;

    /**
     * 初始化布局
     */
    protected abstract int getLayoutRes();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
            initData();
            initListener();
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }


    public static double mul(double num1, int num2) {
        BigDecimal bd = new BigDecimal(Double.toString(num1));
        BigDecimal bd2 = new BigDecimal(num2);
        return bd.multiply(bd2).doubleValue();
    }


}
