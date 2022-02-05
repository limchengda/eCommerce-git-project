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
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	
	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/listingdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	private static final long serialVersionUID = 1L;

	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO ListingDetails"
			+ " (product, price, description, status) VALUES " + " (?, ?, ?);";
	private static final String SELECT_PRODUCTS_BY_ID = "select product,price,description,status from ListingDetails where product =?";
	private static final String SELECT_ALL_PRODUCTS = "select * from ListingDetails ";
	private static final String DELETE_PRODUCTS_SQL = "delete from ListingDetails where product = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update ListingDetails set product = ?,price= ?, description =?,status =? where product = ?;";

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
	public ProductListServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Step 4: Depending on the request servlet path, determine the function to
		// invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/insert":
				break;
			case "/delete":
				break;
			case "/edit":
				break;
			case "/update":
				break;
			default:
				listProducts(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	//Step 5: listUsers function to connect to the database and retrieve all users records
			private void listProducts(HttpServletRequest request, HttpServletResponse response) 
					throws SQLException, IOException, ServletException
			{
			List <ProductList> products = new ArrayList <>();
			 try (Connection connection = getConnection();
			 // Step 5.1: Create a statement using connection object
			 PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			 // Step 5.2: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 5.3: Process the ResultSet object.
			 while (rs.next()) {
			 String product = rs.getString("product");
			 String price = rs.getString("price");
			 String description = rs.getString("description");
			 String status = rs.getString("status");
			 products.add(new ProductList(product, price, description, status));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listProducts", products);
			request.getRequestDispatcher("/listingManagement.jsp").forward(request, response);
			}

}
