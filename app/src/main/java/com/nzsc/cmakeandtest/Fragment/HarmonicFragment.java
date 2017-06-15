package com.nzsc.cmakeandtest.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nzsc.cmakeandtest.Adapter.TestAfapter;
import com.nzsc.cmakeandtest.Base.BaseFragment;
import com.nzsc.cmakeandtest.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public class HarmonicFragment extends BaseFragment {
    @BindView(R.id.tv_harm)
    TextView tvHarm;
    @BindView(R.id.gv_harmonic)
    GridView gvHarmonic;

    TestAfapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_harmonic;
    }

    @Override
    protected void initData() {
        String[] titles = getResources().getStringArray(R.array.sixUI);
        List<String> list = Arrays.asList(titles);
        adapter = new TestAfapter(getContext(), list);

        gvHarmonic.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }


}
