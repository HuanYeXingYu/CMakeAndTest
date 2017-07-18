package com.nzsc.cmakeandtest.Entity;

import com.nzsc.cmakeandtest.utils.CharUtils;

/**
 * Created by 夜墨 on 2017/7/18.
 */

public class ConstantBean {

    byte[] identifier;
    byte[] constant;
    byte[] checkSum;

    public ConstantBean(byte[] identifier, byte[] constant, byte[] checkSum) {
        this.identifier = identifier;
        this.constant = constant;
        this.checkSum = checkSum;
    }

    public String getIdentifier() {
        return CharUtils.convertHexToString(new String(identifier));
    }

    public int getConstant() {
        return CharUtils.hexStr2Str(constant);
    }

    public String getCheckSum() {
        return CharUtils.convertHexToString(new String(checkSum));
    }
}
