package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class ConnectionDataServlet
 */
public class ConnectionDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	JSONObject j1;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionDataServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		j1=new JSONObject();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getOutputStream().println("Hurray !! This Servlet Works");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
					
			//2nd Connecting to database
			
				con = DriverManager.getConnection("jdbc:oracle:thin:@172.16.10.11:1521:orcl", "itjapan_11", "itjapan_11");
				
				//3rd writing the code
				
				String str = "select * from jobs_ojp";
			
				//4th form the statement obj.
					
				Statement st= con.createStatement();
				ResultSet rs=st.executeQuery(str);
				ResultSetMetaData rsmd=rs.getMetaData();
				
				int count= rsmd.getColumnCount();
				//System.out.println(count);
			while(rs.next())
			{
				for(int i=1;i<=count;i++)
				{
					System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
										
					j1.put(rsmd.getColumnName(i), rs.getString(i));
					System.out.println("after json obj.");
					
				}System.out.println("---------------------------");
				
				
				//----------------------------------------------------------
				
				/*int length = request.getContentLength();
				byte[] input = new byte[length];
				ServletInputStream sin = request.getInputStream();
				
				
				System.out.println(new String(input));
				
				sin.close();*/

				//String recievedString =new String(input) ;
				System.out.println(j1.toJSONString());
				response.setStatus(HttpServletResponse.SC_OK);
				OutputStreamWriter writer = new OutputStreamWriter(
						response.getOutputStream());

			

				writer.write(j1.toJSONString());
				writer.flush();
				writer.close();

				//----------------------------------------------------------
				
				
			}
		 System.out.println(j1.toJSONString());
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
