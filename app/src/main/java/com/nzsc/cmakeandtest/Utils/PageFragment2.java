package com.nzsc.cmakeandtest.Utils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.nzsc.cmakeandtest.R;

import java.util.Locale;

/**
 * Created by 夜墨 on 2017/6/14.
 */

public class PageFragment2 extends Fragment {

    public static PageFragment2 newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        PageFragment2 fragment = new PageFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.fragment_title)).setText(String.format(Locale.US, "%d", getSomeIdentifier())+"szdfasdfasdfasdf");
    }

    public int getSomeIdentifier() {
        return getArguments().getInt("position");
    }
}