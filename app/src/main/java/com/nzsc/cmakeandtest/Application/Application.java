package com.nzsc.cmakeandtest.Application;
import com.squareup.leakcanary.LeakCanary;
/**
 * Created by 夜墨 on 2017/6/14.
 *
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
