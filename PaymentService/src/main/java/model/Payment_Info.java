package model;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Payment_Info {
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 

			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	}

	public String insertPayment(String CreditID, String UserID, String SecurityNum, String ExpireDate) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into Payment_Info(`CreditID`,`UserID`,`SecurityNum`,`ExpireDate`)"
					+ " values (?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(CreditID)); 
			preparedStmt.setInt(2, Integer.parseInt(UserID)); 
			preparedStmt.setString(3, SecurityNum); 
			preparedStmt.setString(4, ExpireDate);	

			// execute the statement

			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		return output;
	}
	
	
	
	public String readPayment() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";

			} 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>CreditID</th>" 
					+"<th>UserID</th><th>Security Num</th>"
					+ "<th>Expire Date</th>" 
					+ "<th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from Payment_Info"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String CreditID = Integer.toString(rs.getInt("CreditID")); 
				String UserID = Integer.toString(rs.getInt("UserID")); 
				String SecurityNum = rs.getString("SecurityNum"); 
				String ExpireDate = rs.getString("ExpireDate"); 

				// Add into the html table
				output += "<tr><td>" + CreditID + "</td>"; 
				output += "<td>" + UserID + "</td>"; 
				output += "<td>" + SecurityNum + "</td>"; 
				output += "<td>" + ExpireDate + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='CreditID' type='hidden' value='" + CreditID 
						+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Payment_Info."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 

	}
	
	

	public String updatePayment(String CreditID, String UserID, String SecurityNum, String ExpireDate) 

	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE Payment_Info SET CreditID=?,SecurityNum=?,ExpireDate=? WHERE UserID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(CreditID));  
			preparedStmt.setString(2, SecurityNum); 
			preparedStmt.setString(3, ExpireDate);	
			preparedStmt.setInt(4, Integer.parseInt(UserID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the Payment_Info."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	public String deletePayment(String UserID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from Payment_Info where UserID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(UserID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the Payment_Info."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
}


