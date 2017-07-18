package com.nzsc.cmakeandtest.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.nzsc.cmakeandtest.Adapter.TestAfapter;
import com.nzsc.cmakeandtest.Base.BaseFragment;
import com.nzsc.cmakeandtest.Entity.ComBean;
import com.nzsc.cmakeandtest.Entity.HarmTime;
import com.nzsc.cmakeandtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public class HarmonicFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.gv_harmonic_times)
    GridView gvHarmonic;

    TestAfapter adapter;
    @BindView(R.id.tv_harmonic_leftTo)
    TextView firstChoose;
    @BindView(R.id.tv_harmonic_rightTo)
    TextView secondChoose;
    Unbinder unbinder;
    static int start = 0;
    List<HarmTime> harmList;



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_harmonic;
    }

    @Override
    protected void onDataReceived() {

    }

    @Override
    protected void initData() {

        harmList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {

            harmList.add(new HarmTime(getContext().getString(R.string.harm_time_id, "0" + (i + start) + ""), "-----"));
        }
        for (int i = 10; i < 17; i++) {

            harmList.add(new HarmTime(getContext().getString(R.string.harm_time_id, i + start + ""), "-----"));
        }

        adapter = new TestAfapter(getContext(), harmList);
        gvHarmonic.setAdapter(adapter);


    }

    @Override
    protected void initListener() {
        firstChoose.setOnClickListener(this);
        secondChoose.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_harmonic_leftTo:
                if (start == 1) {
                    break;
                }
                harmList.clear();
                start = 1;
                for (int i = 0; i < 9; i++) {

                    harmList.add(new HarmTime(getContext().getString(R.string.harm_time_id, "0" + (i + start) + ""), mul(0.3, i) + ""));
                }
                for (int i = 9; i < 16; i++) {

                    harmList.add(new HarmTime(getContext().getString(R.string.harm_time_id, i + start + ""), mul(0.3, i) + ""));
                }
                break;
            case R.id.tv_harmonic_rightTo:
                if (start == 17) {
                    break;
                }
                harmList.clear();
                start = 17;
                for (int i = 0; i < 16; i++) {
                    harmList.add(new HarmTime(getContext().getString(R.string.harm_time_id, i + start + ""), mul(0.3, i) + ""));
                }
                break;
        }

        adapter.notifyDataSetChanged();
    }


}
