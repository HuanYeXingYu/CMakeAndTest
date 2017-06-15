package com.nzsc.cmakeandtest.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nzsc.cmakeandtest.Base.MyBaseAdapter;
import com.nzsc.cmakeandtest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public class TestAfapter extends MyBaseAdapter<String> {

    public TestAfapter(Context context, List<String> contentList) {
        super(context, contentList);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        String text = getItem(position);
        final ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_harm_buttom, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.itemSixUI.setText(text);

        return convertView;

    }


     static class ViewHolder {
        @BindView(R.id.item_sixUI)
        TextView itemSixUI;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
