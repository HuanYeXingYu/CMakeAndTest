package com.nzsc.cmakeandtest.Base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzsc.cmakeandtest.Activity.MainActivity;
import com.nzsc.cmakeandtest.Adapter.MyViewPagerAdapter;
import com.nzsc.cmakeandtest.Entity.ComBean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public abstract class BaseFragment extends Fragment {
    public Unbinder unbinder;
    public View rootView; // 缓存Fragment view

    public Context context;
    public Handler handler = new Handler();
    public byte[] buffer;
    public FileInputStream mFileInputStream;
    public FileOutputStream mFileOutputStream;

    public String getRequestCommand() {
        return RequestCommand;
    }

    /**
     * 必须复写
     */
    public void setRequestCommand(String requestCommand) {
        RequestCommand = requestCommand;
    }

    public String RequestCommand;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFileInputStream = MainActivity.getFileInputStream();
        mFileOutputStream = MainActivity.getFileOutputStream();

    }


    @Override
    public Context getContext() {
        return context;
    }


    /**
     * 初始化布局
     */
    protected abstract int getLayoutRes();

    protected abstract void onDataReceived();

    //接收
    protected Runnable GetCode = new Runnable() {
        @Override
        public void run() {
            if (mFileInputStream == null) {
                Log.e("mFileInputStream","null");
                return;
            }

            onDataReceived();
            handler.postDelayed(GetCode, 0);


        }
    };
    //发送
    Runnable SendCode = new Runnable() {
        @Override
        public void run() {
            try {
                if (getRequestCommand() != null) {
                    mFileOutputStream.write(getRequestCommand().getBytes());
                    handler.postDelayed(SendCode, 1000);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
            context = getActivity();
            unbinder = ButterKnife.bind(this, rootView);


            initData();
            initListener();
            handler.postDelayed(SendCode, 1000);
            handler.postDelayed(GetCode, 0);
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

    static public String Byte2Hex(Byte inByte)//1字节转2个Hex字符
    {
        return String.format("%02x", inByte).toUpperCase();
    }
}
