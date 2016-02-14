package com.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrclConnection {
Connection con;
	
	public Connection OrclConnect() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		//2nd Connecting to database
		
			con = DriverManager.getConnection("jdbc:oracle:thin:@172.16.10.11:1521:orcl", "itjapan_1", "itjapan_1");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return con;
	}
	
		
	
	
}
