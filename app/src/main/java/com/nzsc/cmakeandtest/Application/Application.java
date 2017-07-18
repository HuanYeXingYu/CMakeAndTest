package com.nzsc.cmakeandtest.Application;

import android.content.SharedPreferences;
import android.util.Log;

import com.nzsc.cmakeandtest.MySerial.SerialPort;
import com.nzsc.cmakeandtest.MySerial.SerialPortFinder;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by 夜墨 on 2017/6/14.
 */

public class Application extends android.app.Application {


    private SerialPort mSerialPort = null;


    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }


}
