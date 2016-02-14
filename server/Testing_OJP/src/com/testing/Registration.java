package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("In registration now");
		
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
					
			//2nd Connecting to database
			OrclConnection connection=new OrclConnection();
			
			con = connection.OrclConnect();
				
				//3rd writing the code
				
				
				
				GetJson json=new GetJson();
				JSONObject UserDetails =json.GetJson(request, response);
				

				if(UserDetails.get("User_Type").toString().equals("provider")){
				String ProviderQuery="INSERT INTO JOB_PROVIDER_OJP (USER_ID, USER_NAME, USER_SURNAME, USER_ADDRESS, USER_EMAIL, USER_MOBILE_NUMBER, USER_POSTAL_CODE, CITY, COMPANY_NAME, COMPANY_LOCATION, COMPANY_REGISTRATION_NUMBER, COMPANY_DETAILS) VALUES (JOB_PROVIDER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				//4th form the statement obj.
					PreparedStatement p=con.prepareStatement(ProviderQuery);
					
					p.setString(1, (String) UserDetails.get("UserName"));
					p.setString(2, (String) UserDetails.get("LastName"));
					p.setString(3, (String) UserDetails.get("Address"));
					p.setString(4, (String) UserDetails.get("Email"));
					p.setString(5, (String) UserDetails.get("Phone"));
					p.setString(6, (String) UserDetails.get("PostalCode"));
					p.setString(7, (String) UserDetails.get("City"));
					p.setString(8, (String) UserDetails.get("CompanyName"));
					p.setString(9, (String) UserDetails.get("CompanyLocation"));
					p.setString(10, (String) UserDetails.get("CompanyRegistrationNumber"));
					p.setString(11, (String) UserDetails.get("CompanyDetails"));
					
				System.out.println(p.toString());
				int result=p.executeUpdate();
				System.out.println("Number of rows updated:"+result);
				
				//-----------UserLoginOJP database uploading--------------------------
				
				String LoginQuery="INSERT INTO USER_LOGIN_OJP (USER_ID, USER_NAME, USER_PASSWORD, USER_TYPE) VALUES ((select user_id from job_provider_ojp where user_name='"+UserDetails.get("UserName").toString()+"'), ?, ?, ?)";
				PreparedStatement P1=con.prepareStatement(LoginQuery);
				P1.setString(1, UserDetails.get("UserName").toString());
				P1.setString(2, UserDetails.get("Password").toString());
				P1.setString(3, UserDetails.get("User_Type").toString());
				System.out.println(P1.toString());
				int LoginUserResult=P1.executeUpdate();
				System.out.println("Number of rows updated in login table:"+LoginUserResult);

				
				//--------------------------------------------------------------------
				response.setStatus(HttpServletResponse.SC_OK);
				OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream());
				
				writer.write(String.valueOf(result));
				writer.flush();
				writer.close();
				
				}
				
				
				
				
				
				/*
				else if(UserDetails.get("User_Type").toString().equals("seeker"))
				{
					String str="INSERT INTO JOB_SEEKER_OJP (USER_NAME, USER_SURNAME, USER_ID, USER_ADDRESS, USER_EMAIL, USER_MOBILE_NUMBER, USER_POSTAL_CODE, COMPANY_ID, COMPANY_NAME, COMPANY_LOCATION, COMPANY_REGISTRATION_NUMBER, COMPANY_DETAILS) VALUES (?, ?, '1', ?, ?, ?, ?, '1', ?, ?,?, ?)";
						
					//4th form the statement obj.
						PreparedStatement p=con.prepareStatement(str);
						
						p.setString(1, (String) UserDetails.get("JOB_TITLE"));
						p.setString(2, (String) UserDetails.get("JOB_DETAILS"));
						p.setString(3, (String) UserDetails.get("JOB_POST"));
						p.setString(4, (String) UserDetails.get("JOB_SALARY"));
						p.setString(5, (String) UserDetails.get("JOB_VACANCIES"));
						p.setString(6, (String) UserDetails.get("JOB_CATOGORY"));
						p.setString(7, (String) UserDetails.get("JOB_DEADLINE"));
					System.out.println(p.toString());
					int result=p.executeUpdate();
					System.out.println("Number of rows updated:"+result);
					
					response.setStatus(HttpServletResponse.SC_OK);
					OutputStreamWriter writer = new OutputStreamWriter(
					response.getOutputStream());
					
					writer.write(String.valueOf(result));
					writer.flush();
					writer.close();
				}
				*/
				
				
				
				
				
				//----------------------------------------------------------
				
				
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
