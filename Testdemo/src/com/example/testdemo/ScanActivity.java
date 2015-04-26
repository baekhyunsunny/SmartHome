package com.example.testdemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testdemo.utils.Constant;
import com.example.testdemo.utils.StreamTools;

public class ScanActivity extends Activity {
    private TextView text1;
    private TextView text2; 
    String humidity;
    String temperature;
    private boolean tag = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		init();	
		new GetMessage().start();
		
		
	}
		
	private void init() {
		// TODO Auto-generated method stub
		text1 = (TextView)findViewById(R.id.text1);
		text2 = (TextView)findViewById(R.id.text2);

	}
	

    
	private class GetMessage extends Thread {
		public void run(){
			
          while(tag){ 
			    JSONObject jsonObj = netWorking();
			    try {
					humidity = jsonObj.getString("humidity");
					temperature = jsonObj.getString("temperature");
				} catch (Exception e) {
					// TODO: handle exception
				} 
			    runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						text2.setText(humidity);
						text1.setText(temperature);
					}
				});
			
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	
	}

	private JSONObject netWorking(){
			try {
				String path = Constant.netPath
						+ "getEquipmentMessage?username="
						+ TabActivityMain.username + "&password="
						+ TabActivityMain.equipmentId;
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				if (code == 200) {
					InputStream is = conn.getInputStream();
					String result = StreamTools.readFromStream(is);
					return new JSONObject(result);
				} else {
					return null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}    return null;
		    
	   }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tag = false;
	}

     
}
	
