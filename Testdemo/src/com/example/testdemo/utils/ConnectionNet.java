package com.example.testdemo.utils;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


public class ConnectionNet {
	
	public static JSONObject post(String path, List<NameValuePair> parameter ){
		
		String info = "Õ¯¬Á≤ªø…”√" ;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(path);
			httpPost.setEntity(new UrlEncodedFormEntity(parameter,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
                InputStream is = response.getEntity().getContent();
				String result = StreamTools.readFromStream(is);
				return new JSONObject(result);
			} else {
				return new JSONObject().put("info", info);
			}
		} catch (Exception e) {
			// TODO: handle exception
			   return null;
		}
		
	} 
		
		
		
	}


