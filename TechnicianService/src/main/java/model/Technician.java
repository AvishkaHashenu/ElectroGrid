package model;

import java.sql.*;

public class Technician {
	
	//A common method to connect to the DB
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
	
	
	public String insertTechnician(String TechnicianName, String TechnicianAddress, String TechnicianEmail, String TechnicianPhone, String Type, String ContractTech_Salary, String HourlyTech_HourlyWages)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			// create a prepared statement
			String query = " insert into power_technician(`TechnicianID`,`TechnicianName`,`TechnicianAddress`,`TechnicianEmail`,`TechnicianPhone`,`Type`,`ContractTech_Salary`,`HourlyTech_HourlyWages`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, TechnicianName);
			preparedStmt.setString(3, TechnicianAddress);
			preparedStmt.setString(4, TechnicianEmail);
			preparedStmt.setInt(5, Integer.parseInt(TechnicianPhone));
			preparedStmt.setString(6, Type);
			preparedStmt.setDouble(7, Double.parseDouble(ContractTech_Salary));
			preparedStmt.setDouble(8, Double.parseDouble(HourlyTech_HourlyWages));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the technician details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readTechnicians()
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
			output = "<table border='1'><tr><th>Technician ID</th><th>Technician Name</th>" +
					"<th>Technician Address</th>" +
					"<th>Technician Email</th>" +
					"<th>TechnicianPhone</th>" +
					"<th>Type</th>" +
					"<th>Contract Technician Salary</th>" +
					"<th>Hourly Technician HourlyWages</th>";
			String query = "select * from power_technician";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String TechnicianID = Integer.toString(rs.getInt("TechnicianID"));
				String TechnicianName = rs.getString("TechnicianName");
				String TechnicianAddress = rs.getString("TechnicianAddress");
				String TechnicianEmail = rs.getString("TechnicianEmail");
				String TechnicianPhone = rs.getString("TechnicianPhone");
				String Type = rs.getString("Type");
				String ContractTech_Salary = Double.toString(rs.getDouble("ContractTech_Salary"));
				String HourlyTech_HourlyWages = Double.toString(rs.getDouble("HourlyTech_HourlyWages"));
				// Add into the html table
				output += "<tr><td>" + TechnicianID + "</td>";
				output += "<td>" + TechnicianName + "</td>";
				output += "<td>" + TechnicianAddress + "</td>";
				output += "<td>" + TechnicianEmail + "</td>";
				output += "<td>" + TechnicianPhone + "</td>";
				output += "<td>" + Type + "</td>";
				output += "<td>" + ContractTech_Salary + "</td>";
				output += "<td>" + HourlyTech_HourlyWages + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the details of technicians.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String readTechnicianByID(String TechnicianID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Technician ID</th><th>Technician Name</th>" +
					"<th>Technician Address</th>" +
					"<th>Technician Email</th>" +
					"<th>TechnicianPhone</th>" +
					"<th>Type</th>" +
					"<th>Contract Technician Salary</th>" +
					"<th>Hourly Technician HourlyWages</th>";
			String query = "select * from power_technician where TechnicianID='"+TechnicianID+"'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				TechnicianID = Integer.toString(rs.getInt("TechnicianID"));
				String TechnicianName = rs.getString("TechnicianName");
				String TechnicianAddress = rs.getString("TechnicianAddress");
				String TechnicianEmail = rs.getString("TechnicianEmail");
				String TechnicianPhone = rs.getString("TechnicianPhone");
				String Type = rs.getString("Type");
				String ContractTech_Salary = Double.toString(rs.getDouble("ContractTech_Salary"));
				String HourlyTech_HourlyWages = Double.toString(rs.getDouble("HourlyTech_HourlyWages"));
				
				// Add into the html table
				output += "<tr><td>" + TechnicianID + "</td>";
				output += "<td>" + TechnicianName + "</td>";
				output += "<td>" + TechnicianAddress + "</td>";
				output += "<td>" + TechnicianEmail + "</td>";
				output += "<td>" + TechnicianPhone + "</td>";
				output += "<td>" + Type + "</td>";
				output += "<td>" + ContractTech_Salary + "</td>";
				output += "<td>" + HourlyTech_HourlyWages + "</td>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String updateTechnician(String TechnicianID, String TechnicianName, String TechnicianAddress, String TechnicianEmail, String TechnicianPhone, String Type, String ContractTech_Salary, String HourlyTech_HourlyWages)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			
			String query = "UPDATE power_technician SET TechnicianName=?,TechnicianAddress=?,TechnicianEmail=?,TechnicianPhone=?,Type=?,ContractTech_Salary=?,HourlyTech_HourlyWages=? WHERE TechnicianID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, TechnicianName);
			preparedStmt.setString(2, TechnicianAddress);
			preparedStmt.setString(3, TechnicianEmail);
			preparedStmt.setString(4, TechnicianPhone);
			preparedStmt.setString(5, Type);
			preparedStmt.setDouble(6, Double.parseDouble(ContractTech_Salary));
			preparedStmt.setDouble(7, Double.parseDouble(HourlyTech_HourlyWages));
			preparedStmt.setInt(8, Integer.parseInt(TechnicianID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the details of technician.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteTechnician(String technicianID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from power_technician where technicianID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(technicianID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the technician.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
