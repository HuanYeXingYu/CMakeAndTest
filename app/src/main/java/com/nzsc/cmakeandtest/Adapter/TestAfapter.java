package com.nzsc.cmakeandtest.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nzsc.cmakeandtest.Base.MyBaseAdapter;
import com.nzsc.cmakeandtest.Entity.HarmTime;
import com.nzsc.cmakeandtest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夜墨 on 2017/6/15.
 */

public class TestAfapter extends MyBaseAdapter<HarmTime> {


    public TestAfapter(Context context, List<HarmTime> contentList) {
        super(context, contentList);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        HarmTime text = getItem(position);
        final ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_harm_buttom, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.timeContent.setText(text.getTime_content());
        vh.timeId.setText(text.getTime_id());

        return convertView;

    }


    static class ViewHolder {
        @BindView(R.id.time_content)
        TextView timeContent;
        @BindView(R.id.time_id)
        TextView timeId;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
