package com.testing;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AndroidToServlet
 */
public class AndroidToServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidToServlet() {
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
		response.getOutputStream().println("Hurray !! This Servlet Works");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			int length = request.getContentLength();
			byte[] input = new byte[length];
			
			
			ServletInputStream sin = request.getInputStream();
			int c, count = 0;
			System.out.println(sin);
			System.out.println(input);
			
			while ((c = sin.read(input, count, input.length - count)) != -1) {
				count += c;
				System.out.println(count);
			}
						
			System.out.println(input);
			sin.close();

			String recievedString = new String(input);
			
			
			System.out.println(recievedString);
response.setStatus(HttpServletResponse.SC_OK);

			OutputStreamWriter writer = new OutputStreamWriter(
					response.getOutputStream());

			Integer doubledValue = Integer.parseInt(recievedString) * 2;

			writer.write(doubledValue.toString());
			writer.flush();
			writer.close();

		} catch (IOException e) {

			try {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().print(e.getMessage());
				response.getWriter().close();
			} catch (IOException ioe) {
			}
		}
	}

}
