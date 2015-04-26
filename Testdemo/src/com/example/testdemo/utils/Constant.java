package com.example.testdemo.utils;

import android.R.string;

import com.example.testdemo.R;
import com.example.testdemo.TabActivityHome;
import com.example.testdemo.TabActivityPersonalSetting;
import com.example.testdemo.TabActivityRecord;

public class Constant {
	public static String netPath = "http://222.196.200.68/smartHome/main/";
	public static Class tabClassArray[]={ TabActivityHome.class,TabActivityRecord.class,TabActivityPersonalSetting.class};
    public static int tabImageViewArray[] = {R.drawable.table_record,R.drawable.table_home,R.drawable.table_person };
    public static String tabTextViewArray[] = {"历史", "首页", "个人"};

}
