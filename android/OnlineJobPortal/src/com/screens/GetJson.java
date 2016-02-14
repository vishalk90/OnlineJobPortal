package com.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GetJson {

	public JSONObject getJsonObject(String str) {
		// TODO Auto-generated constructor stub
		JSONObject json=new JSONObject();
		try {
		
		UrlConnectivity u=new UrlConnectivity();
		
		
		URLConnection connection = u.connection(str);



		Log.d("inputString", "hi");
		Log.d("inputString1", connection.toString());
		connection.setDoOutput(true);
		
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					Log.d("inputString2", in.toString());
					String returnString = "";
					//doubledValue = 0;
					StringBuffer s11=new StringBuffer();
				
					while ((returnString = in.readLine()) != null) {
					
						s11.append(returnString);
				
						//doubledValue = Integer.parseInt(returnString);
					}
					in.close();
					Log.d("json object to string", s11.toString());
				
					
					
			json= new JSONObject(s11.toString());
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		
		return json;
	}
	
	
}
