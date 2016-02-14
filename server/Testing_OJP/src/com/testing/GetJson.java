package com.testing;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetJson {
JSONObject j1;
	
	
	public JSONObject GetJson(HttpServletRequest request, HttpServletResponse response ) {
		
		try {
		// TODO Auto-generated constructor stub
		
		int length = request.getContentLength();
		byte[] input = new byte[length];
		ServletInputStream sin;
		
			sin = request.getInputStream();
		
		int c, count = 0;
		System.out.println("servlet input stream"+sin);
		//System.out.println(input);
		while ((c = sin.read(input, count, input.length - count)) != -1) {
			count += c;
			System.out.println(count);
		}
		System.out.println("direct output"+input);
		
		String recievedString = new String(input);
		
		System.out.println("This is json after converting to string"+recievedString);
		
		j1=new JSONObject();
		JSONParser jsonpars=new JSONParser();
		Object obj=jsonpars.parse(recievedString);
		j1=(JSONObject) obj;
		sin.close();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return j1;
		
	}
}
