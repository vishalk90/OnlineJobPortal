package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class GettingJobs
 */
public class GettingJobs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GettingJobs() {
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
		System.out.println("hello");
		try {
		OrclConnection connect = new OrclConnection();
		Connection con = connect.OrclConnect();
		
		JSONObject jobs=new JSONObject();
		
		String str = "select job_category,count(*) as COUNT from jobs_ojp group by job_category";
		
		//4th form the statement obj.
			
		Statement st= con.createStatement();
		ResultSet rs=st.executeQuery(str);
		ResultSetMetaData rsmd=rs.getMetaData();
		
		int count= rsmd.getColumnCount();
		//System.out.println(count);
		int counter=1;
		while(rs.next())
		{
			
			for(int i=1;i<=count;i++)
			{
				System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
									
				jobs.put(rsmd.getColumnName(i)+String.valueOf(counter), rs.getString(i));
				
				
			}System.out.println("---------------------------");
			counter++;
			
		}
		jobs.put("COUNT", String.valueOf(counter));
		System.out.println(jobs.toJSONString());
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(
		response.getOutputStream());
		
		
		
		writer.write(jobs.toJSONString());
		writer.flush();
		writer.close();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
		
		
	}

}
