package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class LoginValidation
 */

public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String result="null";
	Connection con;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		OrclConnection connect = new OrclConnection();
		con = connect.OrclConnect();
		/*GetJson g1 = new GetJson();*/
		/*JSONObject j1 = g1.GetJson(request, response);

		System.out.println(j1.get("user_name"));
		System.out.println(j1.get("password"));*/
		
		LoginAuthentication la=new LoginAuthentication();
		result = la.LoginAuthenticate(request, response);
		
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
		System.out.println(result);
		writer.write(result);
		writer.flush();
		writer.close();

	}

}
