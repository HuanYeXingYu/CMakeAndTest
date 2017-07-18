package com.nzsc.cmakeandtest.Entity;

import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * @author benjaminwan
 *串口数据
 */
public class ComBean {
		public byte[] bRec=null;

		public ComBean( byte[] buffer, int size){

			bRec=new byte[size];
			System.arraycopy(buffer, 0, bRec, 0, size);

		}
}