package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.testing.GetJson;

public class LoginAuthentication {

String str1;
String str2;
String str3;
Connection con;
String str4;
String flag;

	
	public String LoginAuthenticate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated constructor stub
		
		System.out.println("-------------------:"+request.getHeader("user-agent"));
		
	 
	      /*Enumeration headerNames = request.getHeaderNames();
	      
	      while(headerNames.hasMoreElements()) {
	         String paramName = (String)headerNames.nextElement();
	         System.out.print("******* " + paramName);
	         String paramValue = request.getHeader(paramName);
	         System.out.println("////////" + paramValue + "\n");
	      }*/
	   
		
		GetJson g1 = new GetJson();
		flag="null";
		JSONObject j1 = g1.GetJson(request, response);
		str3 =(String) j1.get("user_name");
		str2 =(String) j1.get("password");
	
		int result=0;
		try {
			System.out.println("str3 "+str3);
			
			System.out.println("str2 "+str2);

			String str = "select user_password, user_type from user_login_ojp where user_name='" + str3 + "'";
			System.out.println(str);
			
			OrclConnection connect = new OrclConnection();
			con = connect.OrclConnect();
			
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(str);
			// ResultSetMetaData rsmd=rs.getMetaData();
			while (rs.next()) {
				str1 = rs.getString(1);
				str4= rs.getString(2);

			}
			System.out.println(str1 + " - this is password");

			if (str2.isEmpty() || str1 == null) {
				result=0;
			} else 	if (str1.equals(str2)) {
					System.out.println("in true loop");
					flag=str4;
					result=1;

				} 

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			return flag;
	}
}
