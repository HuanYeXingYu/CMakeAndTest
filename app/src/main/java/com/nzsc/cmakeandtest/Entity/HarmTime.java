package com.nzsc.cmakeandtest.Entity;

/**
 * Created by 夜墨 on 2017/6/16.
 */

public class HarmTime {
    private  String time_id;
    private  String time_content;

    public HarmTime(String time_id, String time_content) {
        this.time_id = time_id;
        this.time_content = time_content;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getTime_content() {
        return time_content;
    }

    public void setTime_content(String time_content) {
        this.time_content = time_content;
    }
}
