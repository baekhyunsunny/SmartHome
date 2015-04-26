package com.example.testdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testdemo.utils.ConnectionNet;
import com.example.testdemo.utils.Constant;

public class LoginActivity extends Activity implements OnClickListener {
	//申明控件
	private Button login_bt1,login_bt2;
	private EditText login_edittext1,login_edittext2;
	String info ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		init(); //初始化控件
		
		
		
	}
    
	/*
	 * 登陆密码本地校验
	 * 
	 * */
    public void init(){
    	
    	login_bt1 = (Button)findViewById(R.id.login_bt1);
		login_bt2 = (Button)findViewById(R.id.login_bt2);
		login_edittext1 = (EditText)findViewById(R.id.login_edittext1);
		login_edittext2 = (EditText)findViewById(R.id.login_edittext2);
		login_bt2.setOnClickListener(this);//设置登陆功能
    	
    }
    
    /*
     * 登陆用户名本地校验
     * 
     * */
	
	public String checkUsername(String username){
		
		
		if(TextUtils.isEmpty(username)){
			return "用户名不能为空！";
		}
		
		
		if(username.length()<6||username.length()>15){
			
			
			return "用户名长度必须在6-15之间!";
			
		}
		
		return "";
		
	}
	/*
	 * 登陆密码本地校验
	 * 
	 * */
	public String checkPassword(String password){
		
		if(TextUtils.isEmpty(password)){
			return "密码不能为空！";
			
		}

		
		if(password.length()<6||password.length()>15){
			return "密码长度必须在6-15之间!";
			
		}
		return "";
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String username = login_edittext1.getText().toString().trim();
		final String password = login_edittext2.getText().toString().trim();
		
		String usernameerror = checkUsername(username);//登陆账号本地验证
		
		String userpassworderror = checkPassword(password);//登陆密码本地验证
		
		if(TextUtils.isEmpty(usernameerror)||TextUtils.isEmpty(userpassworderror)){
			
			new Thread(){
				public void run() {
					String result = null;
					//final String result = netWorkin(username, password);
					JSONObject jsonObj;
					try {
						jsonObj = netWorking(username, password);
						result = jsonObj.getString("results");
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				
					if(result.equals("success")){
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(LoginActivity.this, "登陆成功", 0).show();
								Intent intent = new Intent(LoginActivity.this,TabActivityMain.class);
								intent.putExtra("username", username);
								startActivity(intent);
								finish();
							}
						});

					}
					if(result.equals("fail")){
						
	                        runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(LoginActivity.this, "账号或密码错误！", 0).show();
							}
						});
						
					}
					
					
					
				}
			
				
				
			}.start();
			
                 
		}else{
			
			Toast.makeText(LoginActivity.this, usernameerror + userpassworderror , 0).show();
			
		}
	}
	
	public JSONObject netWorking(String username,String password){
		

	
			
			String path = Constant.netPath+"login";
			List<NameValuePair> parameter = new ArrayList<NameValuePair>();
			parameter.add(new BasicNameValuePair("username", username));
			parameter.add(new BasicNameValuePair("password", password));
			return ConnectionNet.post(path, parameter);
		

	
	
}
}