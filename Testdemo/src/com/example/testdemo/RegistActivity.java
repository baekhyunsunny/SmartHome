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

public class RegistActivity extends Activity implements OnClickListener{
	private EditText regist_edittext1,regist_edittext2,regist_edittext3,regist_edittext4,regist_edittext5;
	private Button regist_bt1,regist_bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);

	  
        init();
	  
       }
	
	/*
	 * 初始化控件，绑定监听器！
	 * */
	public void init(){
		
		  regist_bt1 = (Button)findViewById(R.id.regist_bt1);
		  regist_bt2 = (Button)findViewById(R.id.regist_bt2);
		  regist_edittext1 = (EditText)findViewById(R.id.regist_edittext1);
		  regist_edittext2 = (EditText)findViewById(R.id.regist_edittext2);
		  regist_edittext3 = (EditText)findViewById(R.id.regist_edittext3);
		  regist_edittext4 = (EditText)findViewById(R.id.regist_edittext4);
		  regist_edittext5 = (EditText)findViewById(R.id.regist_edittext5);
		  regist_bt2.setOnClickListener(this);
		
		
	}
	
     /*
      * 设置监听器实现方法
      * 
      * */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  final String username = regist_edittext1.getText().toString().trim();
		  final String password = regist_edittext2.getText().toString().trim();
		  final String repassword = regist_edittext3.getText().toString().trim();
		  final String email = regist_edittext4.getText().toString().trim();
		  final String phone = regist_edittext5.getText().toString().trim();
		  
		  String usernameerror = checkUsername(username);
		  String passworderror = checkPassword(password);
		  String repassworderror = checkRepassword(password,repassword);
		  String emailerror = checkEmail(email);
		  String phoneerror = checkPhone(phone);
		  
		  if(TextUtils.isEmpty(usernameerror)&&TextUtils.isEmpty(passworderror)&&TextUtils
				  .isEmpty(repassworderror)&&TextUtils.isEmpty(emailerror)&&TextUtils.isEmpty(phoneerror)){
			  
			  new Thread(){
				   public void run(){
					   String h = null;					   
					   try {
						JSONObject jsonObj = netWorking(username, password,
								email, phone);
						h = jsonObj.getString("results");
						
					} catch (Exception e) {
						// TODO: handle exception
					}

					if(h.equals("success")){
						   runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								Toast.makeText(RegistActivity.this, "注册成功！", 0).show();
								Intent intent = new Intent(RegistActivity.this,LoginActivity.class);
								startActivity(intent);
								finish();
								
								
							}
						});
						   
						   
					   }
					 if(h.equals("fail")){
						   runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									
									Toast.makeText(RegistActivity.this, "注册失败", 0).show();
									
									
								}
							});
						   
					   }
					
					   
					 
					  
					   
				   }
				 
			  }.start();
			  
			  
			  
		  }else{
			  
			      Toast.makeText(RegistActivity.this,usernameerror+passworderror+repassworderror+emailerror+phoneerror, 0).show();
                     			  
		  }
		  
		  
		  
		
	}
	
	public String checkUsername(String username){
		if(TextUtils.isEmpty(username)){
			return "用户名不能为空！";
		}
		
		
		if(username.length()<6||username.length()>15){
			
			
			return "用户名长度必须在6-15之间!";
			
		}
		
		return "";
		
	}
	
	public String checkPassword(String password){
		if(TextUtils.isEmpty(password)){
			return "密码不能为空！";
		}
		
		
		if(password.length()<6||password.length()>15){
			
			
			return "密码长度必须在6-15之间!";
			
		}
		
		return "";
	}
	
	public String checkRepassword(String password,String repassword){
		if(TextUtils.isEmpty(repassword)){
			return "确认密码不能为空！";
		}
		
		if(!repassword.equals(password)){
			return "密码和确认密码不一致";
			
		}
		

		return "";
	}
	
	public String checkEmail(String email){
		if(TextUtils.isEmpty(email)){
			return "邮箱不能为空！";
		}
		
		return "";
	}
	
	public String checkPhone(String phone){
		if(TextUtils.isEmpty(phone)){
			return "手机号不能为空！";
		}
		return "";
	}
	
	
	public JSONObject netWorking(String username,String password,String email,String phone){

			String path = Constant.netPath+"validUserName";
			List<NameValuePair> parameter = new ArrayList<NameValuePair>();
			parameter.add(new BasicNameValuePair("username", username));
			parameter.add(new BasicNameValuePair("password", password));
			parameter.add(new BasicNameValuePair("email", email));
			parameter.add(new BasicNameValuePair("phone", phone));
			return ConnectionNet.post(path, parameter);
		
		
		
		
	}

}
