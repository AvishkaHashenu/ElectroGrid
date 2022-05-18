package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
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
	
	
	public String insertPayment(String PaymentID, String UserID, String PowerSupplierID, String Payment_Method, String Payment_Date, String Amount,String Type, String Consumption_Unit,String NoOfUnits) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into Payment(`PaymentID`,`UserID`,`PowerSupplierID`,`Payment_Method`,`Payment_Date`,`Amount`,`Type`,`Consumption_Unit`,`NoOfUnits`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID)); 
			preparedStmt.setInt(2, Integer.parseInt(UserID)); 
			preparedStmt.setString(3,PowerSupplierID);
			preparedStmt.setString(4, Payment_Method); 
			preparedStmt.setString(5, Payment_Date);	
			preparedStmt.setDouble(6, Double.parseDouble(Amount));
			preparedStmt.setString(7, Type);
			preparedStmt.setInt(8, Integer.parseInt(Consumption_Unit));	
			preparedStmt.setInt(9, Integer.parseInt(NoOfUnits));	
				

			// execute the statement

			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Payment."; 
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
			output = "<table border='1'><tr><th>PaymentID</th>" 
					+"<th>UserID</th><th>PowerSupplierID</th>"
					+ "<th>Payment_Method</th>" 
					+ "<th>Payment_Date</th>" 
					+ "<th>Amount</th>"
					+ "<th>Type</th>"
					+ "<th>Consumption_Unit</th>"
					+ "<th>NoOfUnits</th>"
					+ "<th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from Payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String PaymentID = Integer.toString(rs.getInt("PaymentID")); 
				String UserID = Integer.toString(rs.getInt("UserID")); 
				String PowerSupplierID = Integer.toString(rs.getInt("PowerSupplierID"));  
				String Payment_Method = rs.getString("Payment_Method"); 
				String Payment_Date = rs.getString("Payment_Date"); 
				String Amount = Double.toString(rs.getDouble("Amount"));  
				String Type = rs.getString("Type"); 
				String Consumption_Unit = rs.getString("Consumption_Unit"); 
				String NoOfUnits = rs.getString("NoOfUnits"); 
				// Add into the html table
				output += "<tr><td>" + PaymentID + "</td>"; 
				output += "<td>" + UserID + "</td>"; 
				output += "<td>" + PowerSupplierID + "</td>"; 
				output += "<td>" + Payment_Method + "</td>"; 
				output += "<td>" + Payment_Date + "</td>"; 
				output += "<td>" + Amount + "</td>"; 
				output += "<td>" + Type + "</td>"; 
				output += "<td>" + Consumption_Unit + "</td>"; 
				output += "<td>" + NoOfUnits + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='Payment.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='CreditID','PowerSupplierID' type='hidden' value='" + PaymentID 
						+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the Payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 

	}
	
	public String updatePayment(String PaymentID, String UserID,String PowerSupplierID, String Payment_Method, String Payment_Date, String Amount,String Type, String Consumption_Unit,String NoOfUnits) 

	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE Payment SET UserID=?,PowerSupplierID=?,Payment_Method=?,Payment_Date=?,Amount=?,Type=?,Consumption_Unit=?,NoOfUnits=? WHERE PaymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(UserID));
			preparedStmt.setInt(2, Integer.parseInt(PowerSupplierID));
			preparedStmt.setString(3, Payment_Method); 
			preparedStmt.setString(4, Payment_Date);	
			preparedStmt.setDouble(5, Double.parseDouble(Amount));
			preparedStmt.setString(6, Type);
			preparedStmt.setInt(7, Integer.parseInt(Consumption_Unit));	
			preparedStmt.setInt(8, Integer.parseInt(NoOfUnits));
			preparedStmt.setInt(9, Integer.parseInt(PaymentID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the Payment"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	public String deletePayment(String PaymentID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from Payment where PaymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the Payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	
	
}
