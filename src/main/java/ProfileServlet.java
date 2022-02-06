

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Import these libraries from java.io and java.sql
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		String un = request.getParameter("username");
		String fn = request.getParameter("firstname");
		String ln = request.getParameter("lastname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String date = request.getParameter("dob");
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(
		 "jdbc:mysql://localhost:3306/profile", "root", "password");
		//Step 4: implement the sql query using prepared statement
		 PreparedStatement ps = con.prepareStatement("insert into PROFILE values(?,?,?,?,?,?,?)");
		//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		  ps.setString(1, un);
		  ps.setString(2, fn);
		  ps.setString(3, ln);
		  ps.setString(4, email);
		  ps.setString(5, address);
		  ps.setString(6, gender);
		  ps.setString(7, date);
		//Step 6: perform the query on the database using the prepared statement
		  int i = ps.executeUpdate();
		//Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
		   if (i > 0){
		  PrintWriter writer = response.getWriter();
		  writer.println("<h1>" + "You have successfully create profile information!" + "</h1>" +
		  "<a href=\"http://localhost:8090/eCommerceJavaEE/ProfilesServlet/dashboard\" class=\"nav-link\">Go to Dashboard</a>" );
		  writer.close();
		  }
		  }
		//Step 8: catch and print out any exception
		catch (Exception exception) {
		 System.out.println(exception);
		 out.close();
		}
		doGet(request, response);
	}
}
