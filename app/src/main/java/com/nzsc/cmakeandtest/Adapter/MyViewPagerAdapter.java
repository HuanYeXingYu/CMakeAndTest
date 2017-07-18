package com.nzsc.cmakeandtest.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import eu.inloop.pager.UpdatableFragmentPagerAdapter;

/**
 * Created by 夜墨 on 2017/6/14.
 *
 */

public class MyViewPagerAdapter extends UpdatableFragmentPagerAdapter {

    private List<String> titleList;
    private List<Fragment> fragmentList;

    public MyViewPagerAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
        super(fm);
        this.titleList = titleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment) object;
        return fragment.getArguments().getInt("position");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}

