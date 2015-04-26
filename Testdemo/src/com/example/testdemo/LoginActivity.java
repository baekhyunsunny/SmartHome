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
	//�����ؼ�
	private Button login_bt1,login_bt2;
	private EditText login_edittext1,login_edittext2;
	String info ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		init(); //��ʼ���ؼ�
		
		
		
	}
    
	/*
	 * ��½���뱾��У��
	 * 
	 * */
    public void init(){
    	
    	login_bt1 = (Button)findViewById(R.id.login_bt1);
		login_bt2 = (Button)findViewById(R.id.login_bt2);
		login_edittext1 = (EditText)findViewById(R.id.login_edittext1);
		login_edittext2 = (EditText)findViewById(R.id.login_edittext2);
		login_bt2.setOnClickListener(this);//���õ�½����
    	
    }
    
    /*
     * ��½�û�������У��
     * 
     * */
	
	public String checkUsername(String username){
		
		
		if(TextUtils.isEmpty(username)){
			return "�û�������Ϊ�գ�";
		}
		
		
		if(username.length()<6||username.length()>15){
			
			
			return "�û������ȱ�����6-15֮��!";
			
		}
		
		return "";
		
	}
	/*
	 * ��½���뱾��У��
	 * 
	 * */
	public String checkPassword(String password){
		
		if(TextUtils.isEmpty(password)){
			return "���벻��Ϊ�գ�";
			
		}

		
		if(password.length()<6||password.length()>15){
			return "���볤�ȱ�����6-15֮��!";
			
		}
		return "";
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final String username = login_edittext1.getText().toString().trim();
		final String password = login_edittext2.getText().toString().trim();
		
		String usernameerror = checkUsername(username);//��½�˺ű�����֤
		
		String userpassworderror = checkPassword(password);//��½���뱾����֤
		
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
								Toast.makeText(LoginActivity.this, "��½�ɹ�", 0).show();
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
								Toast.makeText(LoginActivity.this, "�˺Ż��������", 0).show();
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