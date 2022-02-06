

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class ProfilesServlet
 */
@WebServlet("/ProfilesServlet")
public class ProfilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/profile";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_USERS_SQL = "INSERT INTO profile" + " (username,firstname, lastname, email, address, gender, dob) VALUES " + " (?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select username, firstname, lastname, email, address, gender, dob from profile where username =?";
	private static final String SELECT_ALL_USERS = "select * from profile ";
	private static final String DELETE_USERS_SQL = "delete from profile where username = ?;";
	private static final String UPDATE_USERS_SQL = "update profile set firstname = ?,lastname= ?, email =?,address =?,gender =?,dob =? where username = ?;";
	//Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
	 protected Connection getConnection() {
	 Connection connection = null;
	 try {
	 Class.forName("com.mysql.jdbc.Driver");
	 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } catch (ClassNotFoundException e) {
	 e.printStackTrace();
	 }
	 return connection;
	 }
			/**
			 * @see HttpServlet#HttpServlet()
			 */
			public ProfilesServlet() {
				super();
				// TODO Auto-generated constructor stub
			}

			/**
			 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				//Step 4: Depending on the request servlet path, determine the function to invoke using the follow switch statement.
				String action = request.getServletPath();
				 try {
				 switch (action) {
				 case "/ProfilesServlet/delete":
				 deleteProfile(request, response);
				 break;
				 case "/ProfilesServlet/edit":
				 showEditForm(request, response);
				 break;
				 case "/ProfilesServlet/update":
				 updateProfile(request, response);
				 break;
				 case "/ProfilesServlet/dashboard":
				 listProfiles(request, response);
				 break;
				 }
				 } catch (SQLException ex) {
				 throw new ServletException(ex);
				 } 
				response.getWriter().append("Served at: ").append(request.getContextPath());
			}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request, response);
			}
			//Step 5: listProfile function to connect to the database and retrieve all users records
			private void listProfiles(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
				List <Profiles> profiles = new ArrayList <>();
				try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						String un = rs.getString("username");
						String fn = rs.getString("firstname");
						String ln = rs.getString("lastname");
						String email = rs.getString("email");
						String address = rs.getString("address");
						String gender = rs.getString("gender");
						String date = rs.getString("dob");
						profiles.add(new Profiles(un, fn, ln, email, address, gender, date));
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				request.setAttribute("listProfiles", profiles);
				request.getRequestDispatcher("/profileManagement.jsp").forward(request, response);
			}
			//method to get parameter, query database for existing user data and redirect to user edit page
			private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
				String un = request.getParameter("username");
				Profiles existingProfile = new Profiles("", "", "", "", "", "", "");
				try (Connection connection = getConnection();
						PreparedStatement preparedStatement =
								connection.prepareStatement(SELECT_USER_BY_ID);) {
					preparedStatement.setString(1, un);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						un = rs.getString("username");
						String fn = rs.getString("firstname");
						String ln = rs.getString("lastname");
						String email = rs.getString("email");
						String address = rs.getString("address");
						String gender = rs.getString("gender");
						String date = rs.getString("dob");
						existingProfile = new Profiles(un, fn, ln, email, address, gender, date);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				request.setAttribute("Profiles", existingProfile);
				request.getRequestDispatcher("/profileEdit.jsp").forward(request, response);
			}
			//method to update the profile table base on the form data
			private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
				String oriName = request.getParameter("oriName");
				String fn = request.getParameter("firstname");
				String ln = request.getParameter("lastname");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String date = request.getParameter("dob");
				try (Connection connection = getConnection(); PreparedStatement statement =
						connection.prepareStatement(UPDATE_USERS_SQL);) {
					statement.setString(1, fn);
					statement.setString(2, ln);
					statement.setString(3, email);
					statement.setString(4, address);
					statement.setString(5, gender);
					statement.setString(6, date);
					statement.setString(7, oriName);
					int i = statement.executeUpdate();
				}
				response.sendRedirect("http://localhost:8090/eCommerceJavaEE/ProfilesServlet/dashboard");
			}
			//method to delete user
			private void deleteProfile(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
				String un = request.getParameter("username");
				try (Connection connection = getConnection(); PreparedStatement statement =
						connection.prepareStatement(DELETE_USERS_SQL);) {
					statement.setString(1, un);
					int i = statement.executeUpdate();
				}
				response.sendRedirect("http://localhost:8090/eCommerceJavaEE/ProfilesServlet/dashboard");
			}
}