package com.nzsc.cmakeandtest.Fragment;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.nzsc.cmakeandtest.Adapter.ConnectWayAdapter;
import com.nzsc.cmakeandtest.Base.BaseFragment;
import com.nzsc.cmakeandtest.Entity.ComBean;
import com.nzsc.cmakeandtest.Entity.ConstantBean;
import com.nzsc.cmakeandtest.R;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static com.nzsc.cmakeandtest.Base.BaseActivtiy.ByteArrToHex;

/**
 * Created by 夜墨 on 2017/6/22.
 */

public class RoutineFragment extends BaseFragment {
    @BindView(R.id.lv_routine_connectionMode)
    ListView lv_connectWay;
    @BindView(R.id.tv_routine_constant)
    TextView constant;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_routine;
    }

    @Override
    protected void onDataReceived() {
        int size;
        buffer = new byte[512];
        try {
            size = mFileInputStream.read(buffer);
            if (size > 0) {
                ComBean ComRecData = new ComBean(buffer, size);
                onDataReceived(ComRecData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {
        //初始化title
        String[] connectWays = getResources().getStringArray(R.array.ConnectWay);
        List<String> connectList = Arrays.asList(connectWays);
        ConnectWayAdapter adapter = new ConnectWayAdapter(getContext(), connectList);
        lv_connectWay.setAdapter(adapter);
        setRequestCommand("AP");

    }

    @Override
    protected void initListener() {

    }

    private void onDataReceived(final ComBean comBean) {
        String content=ByteArrToHex(comBean.bRec);
        // 50 00 A2 4A 04 F0
        String[] contentList=content.split(" ");
//        byte[] identifier=contentList[0].getBytes();
//        byte[] constant=;
//        byte[] checkSum;
//        ConstantBean bean=new ConstantBean(identifier,constant,checkSum);
//        constant.setText();

       // myViewPagerAdapter.notifyDataSetChanged();
    }

}
