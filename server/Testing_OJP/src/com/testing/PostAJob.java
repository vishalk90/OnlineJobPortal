package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class PostAJob
 */
public class PostAJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostAJob() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getOutputStream().println("Hurray !! This Servlet Works");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
					
			//2nd Connecting to database
			OrclConnection connection=new OrclConnection();
			
			con = connection.OrclConnect();
				
				//3rd writing the code
				
				
				
				GetJson json=new GetJson();
				JSONObject JobDetails =json.GetJson(request, response);
				

				String str="INSERT INTO JOBS_OJP(JOB_ID, JOB_TITLE, JOB_DETAILS, JOB_POST, JOB_SALARY, JOB_VACANCIES, JOB_CATEGORY, JOB_DEADLINE) VALUES (JOBS_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			

				//4th form the statement obj.
					PreparedStatement p=con.prepareStatement(str);
				

					p.setString(1, (String) JobDetails.get("JOB_TITLE"));
					p.setString(2, (String) JobDetails.get("JOB_DETAILS"));
					p.setString(3, (String) JobDetails.get("JOB_POST"));
					p.setString(4, (String) JobDetails.get("JOB_SALARY"));
					p.setString(5, (String) JobDetails.get("JOB_VACANCIES"));
					p.setString(6, (String) JobDetails.get("JOB_CATOGORY"));
					p.setString(7, (String) JobDetails.get("JOB_DEADLINE"));
				System.out.println(p.toString());
				int result=p.executeUpdate();
				System.out.println("Number of rows updated:"+result);
				
				response.setStatus(HttpServletResponse.SC_OK);
				OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream());
				
				writer.write(String.valueOf(result));
				writer.flush();
				writer.close();
				
				//--------------------------------------------------------------
				
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					try {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						response.getWriter().print(e.getMessage());
						response.getWriter().close();
					} catch (IOException ioe) {
					}
				}
		
		
	}

}
