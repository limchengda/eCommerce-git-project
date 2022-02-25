import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/listingdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO listingdetails"
			+ " (product, price, description, status) VALUES " + " (?, ?, ?);";
	private static final String SELECT_PRODUCTS_BY_ID = "select product,price,description,status from listingdetails where product =?";
	private static final String SELECT_ALL_PRODUCTS = "select * from listingdetails";
	private static final String DELETE_PRODUCTS_SQL = "delete from listingdetails where product = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update listingdetails set product = ?,price= ?, description =?,status =? where product = ?;";

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
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
	public ProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/ProductsServlet/delete":
				deleteProduct(request, response);
				break;
			case "/ProductsServlet/edit":
				showEditForm(request, response);
				break;
			case "/ProductsServlet/update":
				updateUser(request, response);
				break;
			case "/ProductsServlet/dashboard":
				listProducts(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Step 5: listUsers function to connect to the database and retrieve all users
	// records
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Products> products = new ArrayList<>();

		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {

			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String product = rs.getString("product");
				String price = rs.getString("price");
				String description = rs.getString("description");
				String status = rs.getString("status");
				products.add(new Products(product, price, description, status));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the
		// userManagement.jsp
		request.setAttribute("listProducts", products);
		request.getRequestDispatcher("/productManagement.jsp").forward(request, response);
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String product = request.getParameter("product");
		Products existingProduct = new Products("", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID);) {
			preparedStatement.setString(1, product);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				product = rs.getString("product");
				String price = rs.getString("price");
				String description = rs.getString("description");
				String status = rs.getString("status");
				existingProduct = new Products(product, price, description, status);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("Products", existingProduct);
		request.getRequestDispatcher("/productEdit.jsp").forward(request, response);
	}

	// method to update the user table base on the form data
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String oriName = request.getParameter("oriName");
		String product = request.getParameter("product");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String status = request.getParameter("status");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
			statement.setString(1, product);
			statement.setString(2, price);
			statement.setString(3, description);
			statement.setString(4, status);
			statement.setString(5, oriName);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8090/eCommerceJavaEE/ProductsServlet/dashboard");
	}

	// method to delete user
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	 String product = request.getParameter("product");
	 //Step 2: Attempt connection with database and execute delete user SQL query
	 try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
	 statement.setString(1, product);
	 int i = statement.executeUpdate();
	 }
	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	 response.sendRedirect("http://localhost:8090/eCommerceJavaEE/ProductsServlet/dashboard");
	}
}
