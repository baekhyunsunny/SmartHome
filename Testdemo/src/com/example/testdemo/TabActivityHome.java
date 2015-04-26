package com.example.testdemo;


import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testdemo.utils.ConnectionNet;
import com.example.testdemo.utils.Constant;


public class TabActivityHome extends Activity implements OnClickListener{

	private TextView home_textview2;
	private RelativeLayout layout1,layout2;
	private Button home_button1,home_button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity_home);
		init(); 
		doRefesh();        //有无设备号的验证

	}
    
	public void doRefesh() {
		// TODO Auto-generated method stub
		new Thread(){
			public void run(){
     
					 try {
					    JSONObject jsonObj = netWorking();
                        TabActivityMain.equipmentId = jsonObj.getString("results");
                        System.out.println(TabActivityMain.equipmentId);
					} catch (Exception e) {
						// TODO: handle exception
					}
				if(TabActivityMain.equipmentId.equals("fail")){
					/*
					 * 更新主线程的UI
					 * 
					 * */
					runOnUiThread(new  Runnable() {
						public void run() {
							
							
							layout2.setVisibility(View.VISIBLE);
							layout1.setVisibility(View.INVISIBLE);
							
							
						}
					});
					
					
				}else{
					runOnUiThread(new  Runnable() {
						public void run() {
							
							
							layout1.setVisibility(View.VISIBLE);
							layout2.setVisibility(View.INVISIBLE);
							
							
						}
					});
				
					
				}
				
				
			
			}
		}.start();
	}



	/*
	 * 初始化参数
	 * */
	private void init() {
		// TODO Auto-generated method stub
	    layout1 = (RelativeLayout)findViewById(R.id.layout1);
        layout2 = (RelativeLayout)findViewById(R.id.layout2);
		home_button1 = (Button)findViewById(R.id.home_button1);
		home_button2 = (Button)findViewById(R.id.home_button2);
		home_button2.setOnClickListener(this);
		home_button1.setOnClickListener(this);
		
		
		
	}
	
	/*
	 * 有误设备号的验证！
	 * 
	 * */
	public JSONObject netWorking(){
		
		
			String path =Constant.netPath+"validEquipmentId";
			List<NameValuePair> parameter = new ArrayList<NameValuePair>();
			parameter.add(new BasicNameValuePair("username", TabActivityMain.username));
			return ConnectionNet.post(path, parameter);
			
		

     }



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.home_button1:
			Intent intent1 = new Intent(TabActivityHome.this,ScanActivity.class);
			startActivity(intent1);
			break;

		case R.id.home_button2:
			
			Intent intent2 = new Intent(TabActivityHome.this,NumberActivity.class);
			startActivity(intent2);
			
			
			break;
		
			
			
		}
		
	}
	

	
 }
	
	



	
