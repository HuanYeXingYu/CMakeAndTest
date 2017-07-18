package com.nzsc.cmakeandtest.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.nzsc.cmakeandtest.Adapter.MyViewPagerAdapter;
import com.nzsc.cmakeandtest.Base.BaseActivtiy;
import com.nzsc.cmakeandtest.Entity.ComBean;
import com.nzsc.cmakeandtest.Fragment.ErrorFragment;
import com.nzsc.cmakeandtest.Fragment.HarmonicFragment;
import com.nzsc.cmakeandtest.Fragment.OtherFragment;
import com.nzsc.cmakeandtest.Fragment.RoutineFragment;
import com.nzsc.cmakeandtest.Fragment.VectorgraphFragment;
import com.nzsc.cmakeandtest.R;
import com.nzsc.cmakeandtest.View.ScaleTransitionPagerTitleView;
import com.nzsc.cmakeandtest.utils.ScreenAbout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivtiy {
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    Handler handler = new Handler();
    Handler handler1 = new Handler();
    @BindView(R.id.content)
    TextView content;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    Bundle args = new Bundle();



   public static MyViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    ScreenAbout.closeBar();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        serial = getSerialPort();
//        try {
//            //    buffer = new byte[16];
//            if (mFileInputStream == null) {
//                Log.e("SerialPort=", "null");
//                return;
//            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (true) {
//                        int size;
//                        buffer = new byte[512];
//
//                        if (mFileInputStream == null) {
//                            Log.e("SerialPort=", "null");
//                            return;
//                        }
//                        try {
//                            size = mFileInputStream.read(buffer);
//                            if (size > 0) {
//                                ComBean ComRecData = new ComBean(buffer, size);
//                                onDataReceived(ComRecData);
//
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//
//            }).start();
//
//        } catch (Exception e) {
//        }
//        handler1.postDelayed(SendCode, 1000);
    }

    private void onDataReceived(final ComBean comBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                content.setText(ByteArrToHex(comBean.bRec));
            }
        });
    }

    Runnable SendCode = new Runnable() {
        @Override
        public void run() {
           // SendCode("AP");
            handler1.postDelayed(SendCode, 3000);
        }
    };

    @Override
    protected void initEvent() {
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), titleList, fragmentList);

        viewPager.setAdapter(adapter);

        initMagicIndicator();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //初始化title
                String[] titles = getResources().getStringArray(R.array.viewPagerTitles);
                List<String> titleList1 = Arrays.asList(titles);
                titleList.addAll(titleList1);
                //初始化Fragment
                RoutineFragment fragment1 = new RoutineFragment();
                args.putInt("position", 0);
                fragment1.setArguments(args);
                fragmentList.add(fragment1);

                VectorgraphFragment fragment2 = new VectorgraphFragment();
                args.putInt("position", 1);
                fragment2.setArguments(args);
                fragmentList.add(fragment2);

                HarmonicFragment fragment3 = new HarmonicFragment();
                args.putInt("position", 2);
                fragment3.setArguments(args);
                fragmentList.add(fragment3);

                ErrorFragment fragment4 = new ErrorFragment();
                args.putInt("position", 3);
                fragment4.setArguments(args);
                fragmentList.add(fragment4);

                OtherFragment fragment5 = new OtherFragment();
                args.putInt("position", 4);
                fragment5.setArguments(args);
                fragmentList.add(fragment5);
                adapter.notifyDataSetChanged();
                initMagicIndicator();
            }
        }, 0);
    }


    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titleList.get(index));

                simplePagerTitleView.setTextSize(27);
                simplePagerTitleView.setNormalColor(Color.parseColor("#FF4081"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#3F51B5"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                //  indicator.setYOffset(UIUtil.dip2px(context, 39));//设置使下划线显示在上面
                indicator.setLineHeight(UIUtil.dip2px(context, 1));
                indicator.setColors(Color.parseColor("#f57c00"));
                return indicator;
            }

        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

    }

    private void SendCode(String tx) {
        try {
            mFileOutputStream.write(tx.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileInputStream getFileInputStream() {
        return getmFileInputStream();
    }

    public static FileOutputStream getFileOutputStream() {
        return getmFileOutputStream();
    }

}
