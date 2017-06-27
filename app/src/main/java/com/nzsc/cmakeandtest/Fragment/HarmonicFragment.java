package com.nzsc.cmakeandtest.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nzsc.cmakeandtest.Adapter.TestAfapter;
import com.nzsc.cmakeandtest.Base.BaseFragment;
import com.nzsc.cmakeandtest.Entity.HarmTime;
import com.nzsc.cmakeandtest.R;
import com.nzsc.cmakeandtest.View.CircleIndexView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public class HarmonicFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_harm)
    TextView tvHarm;
    @BindView(R.id.gv_harmonic)
    GridView gvHarmonic;

    TestAfapter adapter;
    @BindView(R.id.circle_index_view)
    CircleIndexView circleIndexView;
    @BindView(R.id.firstChoose)
    TextView firstChoose;
    @BindView(R.id.secondChoose)
    TextView secondChoose;
    Unbinder unbinder;
    static int start = 0;
    List<HarmTime> harmList;
    @BindView(R.id.tv_lostAct)
    TextView tvLostAct;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_harmonic;
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
        tvHarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleIndexView.ifNeedData = false;
            }
        });
        circleIndexView.setOnIndexChangeListener(new CircleIndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String letter, boolean ifShow) {
                tvHarm.setText(letter);
            }

            @Override
            public void indexChangeFinish(final String itemChoose) {
                Toast.makeText(getActivity(), "eeeeeee", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstChoose:
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
            case R.id.secondChoose:
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
