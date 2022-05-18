package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class Password {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String loginUser(String username, String password) 
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for login"; 
			}

			String query = "select username, password from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next())
			{
				String userName = rs.getString("username");
				String pass = rs.getString("password");

				if(username.equals(userName) && password.equals(pass))
				{

					output = "Welcome "+ username;
					return output;

				}		
			}
		}
		catch (Exception e)
		{

			System.err.println(e.getMessage());
		}
		output = "Incorrect Username or Password";
		return output;

	}

	public String resetPassword(String userCode,String password) 
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for password resetting"; 
			}

			String query = "UPDATE user SET password=? WHERE reset_code=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);



			// binding values
			preparedStmt.setString(1,password);//new password 
			preparedStmt.setString(2,userCode);//usercode sent through email is passed

			if(preparedStmt.execute() == true) {
				output = "Error while Reseting the Password";
			}
			else {
				output = "Password Reset Successfully";
			}

			con.close();

		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return output;

	}
}
