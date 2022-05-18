package util;
import java.sql.*;
import java.sql.Connection;

public class DBHandler {
	private static final String host = "127.0.0.1";
	private static final String port = "3306";
	private static final String database = "electrogrid";
	private static final String username = "root";
	private static final String password = "root";


	/**
	 * This method returns a usable JDBC database connection to be used by sub classes in the service/project.
	 * 
	 * @return returns a valid SQL connection based on the given connection string
	 */

	private static Connection con = null;


	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, username, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}

