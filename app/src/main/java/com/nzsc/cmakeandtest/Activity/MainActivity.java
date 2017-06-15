package com.nzsc.cmakeandtest.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.nzsc.cmakeandtest.Adapter.MyViewPagerAdapter;
import com.nzsc.cmakeandtest.Base.BaseActivtiy;
import com.nzsc.cmakeandtest.Utils.PageFragment;
import com.nzsc.cmakeandtest.Utils.PageFragment2;
import com.nzsc.cmakeandtest.View.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static android.view.Gravity.CENTER;

public class MainActivity extends BaseActivtiy {
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    Bundle args = new Bundle();
    MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //   initMagicIndicator();


//
//        findViewById(R.id.btn_shuffle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Collections.shuffle(sDataSet);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sDataSet.add(sDataSet.size());
//                adapter.notifyDataSetChanged();
//            }
//        });
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
        fragmentList = new ArrayList<>();
        //初始化title
        String[] titles = getResources().getStringArray(R.array.viewPagerTitles);
        titleList = Arrays.asList(titles);
        //初始化Fragment
        PageFragment fragment1 = new PageFragment();
        args.putInt("position", 0);
        fragment1.setArguments(args);
        fragmentList.add(fragment1);
        PageFragment2 fragment2 = new PageFragment2();
        args.putInt("position", 1);
        fragment2.setArguments(args);
        fragmentList.add(fragment2);
        PageFragment fragment3 = new PageFragment();
        args.putInt("position", 2);
        fragment3.setArguments(args);
        fragmentList.add(fragment3);
        PageFragment2 fragment4 = new PageFragment2();
        args.putInt("position", 3);
        fragment4.setArguments(args);
        fragmentList.add(fragment4);

    }

    @Override
    protected void initEvent() {
        adapter = new MyViewPagerAdapter(getSupportFragmentManager(), titleList, fragmentList);
        viewPager.setAdapter(adapter);
        initMagicIndicator();

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

                simplePagerTitleView.setTextSize(18);
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


}
