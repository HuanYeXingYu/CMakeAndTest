package com.nzsc.cmakeandtest.Base;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nzsc.cmakeandtest.Application.Application;
import com.nzsc.cmakeandtest.MySerial.SerialPort;
import com.nzsc.cmakeandtest.MySerial.SerialPortFinder;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import butterknife.ButterKnife;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public abstract class BaseActivtiy extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    public Application mApplication;
    //  public SerialPortFinder mSerialPortFinder;
    public SharedPreferences.Editor editor;
    public SerialPort serial;
    private static final String TAG = "SerialPort";
    public FileDescriptor mFd;


    public static FileInputStream getmFileInputStream() {
        return mFileInputStream;
    }

    public static FileOutputStream getmFileOutputStream() {
        return mFileOutputStream;
    }

    public static  FileInputStream mFileInputStream;
    public  static FileOutputStream mFileOutputStream;
    public byte[] buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();


    }

    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public SerialPort getSerialPort() {
        if (serial == null) {
            serial = new SerialPort();
            mFd = serial.Open(6, 9600);
            if (mFd == null) {
                Log.e(TAG, "native open returns null");
                //	throw new IOException();
            } else {
                mFileInputStream = new FileInputStream(mFd);
                mFileOutputStream = new FileOutputStream(mFd);

                Log.e(TAG, "native open returns not null");
            }
        }
        return serial;
    }

    public void closeSerialPort() {
        if (serial != null) {
            serial.close();
            serial = null;
        }
    }
    static public String ByteArrToHex(byte[] inBytArr)//字节数组转转hex字符串
    {
        StringBuilder strBuilder=new StringBuilder();
        int j=inBytArr.length;
        for (int i = 0; i < j; i++)
        {
            strBuilder.append(Byte2Hex(inBytArr[i]));
            strBuilder.append(" ");
        }
        return strBuilder.toString();
    }
    static public String Byte2Hex(Byte inByte)//1字节转2个Hex字符
    {
        return String.format("%02x", inByte).toUpperCase();
    }
}
