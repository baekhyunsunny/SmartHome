package com.example.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button main_bt1,main_bt2;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		main_bt1 = (Button)findViewById(R.id.main_bt1);
		main_bt2 = (Button)findViewById(R.id.main_bt2);
		main_bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClassName(MainActivity.this, "com.example.testdemo.RegistActivity");
				startActivity(intent);
				finish();
				
			}
		});
		main_bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClassName(MainActivity.this, "com.example.testdemo.LoginActivity");
				startActivity(intent);
				finish();
			}
		});
	} 


}
