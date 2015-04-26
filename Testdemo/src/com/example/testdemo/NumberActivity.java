package com.example.testdemo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testdemo.utils.Constant;
import com.example.testdemo.utils.StreamTools;

public class NumberActivity extends Activity implements OnClickListener {
	private EditText number_edittext2;
	private Button number_button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number);
		init();
		
		
		
		
	}
	
	/*
	 * 
	 * 初始化
	 * */
	private void init() {
		// TODO Auto-generated method stub
		number_edittext2 = (EditText)findViewById(R.id.number_edittext2);
		number_button1 = (Button)findViewById(R.id.number_bt1);
		number_button1.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String equipmentId = number_edittext2.getText().toString().trim();
		new Thread(){
			public void run(){
				
				JSONObject jsonObj = netWorking(TabActivityMain.username,equipmentId);
			    try {
					String equipmentId = jsonObj.getString("equipmentId");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    if(equipmentId  != null){
			    	runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(NumberActivity.this, "绑定成功！", 0).show();
							
							Intent intent = new Intent(NumberActivity.this,TabActivityHome.class);
							intent.putExtra("username", TabActivityMain.username);
					    	startActivity(intent);
					    	finish();
							
						}
					});
			    
			    	
			    	
			    	
			    }
			    else{
			    	
			    	
                          runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(NumberActivity.this, "绑定失败！", 0).show();
							
							
						}
					});
			    	
			    	
			    }
				
				
				
			}

			
			
		}.start();
		
		
	}
	
	private JSONObject netWorking(String username,String equipmentId ) {

	    
	try {
		
		String path = Constant.netPath+"addEquipmentid";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> parameters1 = new ArrayList<NameValuePair>();
		parameters1.add(new BasicNameValuePair("username", username));
		parameters1.add(new BasicNameValuePair("equipmentId", equipmentId));
		httpPost.setEntity(new UrlEncodedFormEntity(parameters1));
		HttpResponse response = httpClient.execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		if(code == 200){
			InputStream is = response.getEntity().getContent();
			String result = StreamTools.readFromStream(is);
		
			return new JSONObject(result);
		
			
		}else{
			
			return null;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		return null;
		
	}
	     
	
       
	}	
}
