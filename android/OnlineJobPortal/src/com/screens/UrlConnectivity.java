package com.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class UrlConnectivity {
	
	
	public URLConnection connection(String str){
		// TODO Auto-generated method stub

		
	 URLConnection connection=null;
                    
                    URL url;
					try {
						url = new URL("http://10.0.2.2:8080/Testing_OJP/"+str);
					
                    connection = url.openConnection();
                    
                    
                    } catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return connection;
				}
	
	
               
		
	

}
