package com.example.testdemo;

import com.example.testdemo.utils.Constant;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TabActivityMain extends TabActivity {
	private TabHost tabHost;
	private LayoutInflater layoutInflater;
	public static String username;
	public static String equipmentId;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity_main);
		Intent intent = getIntent();//接受登陆页面传送的数据
		username =intent.getStringExtra("username");
		tabHost = getTabHost();
		layoutInflater = LayoutInflater.from(this);
		int activityCount = Constant.tabClassArray.length;
		for(int i = 0; i < activityCount; i++){	
			TabSpec tabSpec = tabHost.newTabSpec(Constant.tabTextViewArray[i])
					.setIndicator(getTabItemView(i)).setContent(getTabItemIntent(i));
			tabHost.addTab(tabSpec);
		}
		
	}

	//给每个Tab设置图标和文字
	private View getTabItemView(int i){
		View view = layoutInflater.inflate(R.layout.tabwidget_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		if (imageView != null){
			imageView.setImageResource(Constant.tabImageViewArray[i]);
		}		
		return view;
	}
	private Intent getTabItemIntent(int i){
		Intent intent = new Intent(this, Constant.tabClassArray[i]);
		return intent;
	}
		
		
	
	
	}
	


