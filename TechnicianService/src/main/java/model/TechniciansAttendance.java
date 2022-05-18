package model;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class TechniciansAttendance {
	
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
	
	
	public String insertTechnicianAttendance(String Technician_AttendanceID, String TechnicianID, String AttendanceStatus)
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
			String query = " insert into technicians_attendance_details(`Technician_AttendanceID`,`TechnicianID`,`Date`,`AttendanceStatus`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String strDate = dateFormat.format(date);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Technician_AttendanceID));
			preparedStmt.setInt(2, Integer.parseInt(TechnicianID));
			preparedStmt.setString(3, strDate);
			preparedStmt.setInt(4, Integer.parseInt(AttendanceStatus));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the attendance.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String readTechnicianAttendance()
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
			output = "<table border='1'><tr><th>Technician Attendance ID</th><th>Technician ID</th>" +
					"<th>Date</th>" +
					"<th>Attendance Status</th>";
			String query = "select * from technicians_attendance_details";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String Technician_AttendanceID = Integer.toString(rs.getInt("Technician_AttendanceID"));
				String TechnicianID = Integer.toString(rs.getInt("TechnicianID"));
				String Date = rs.getString("Date");
				String AttendanceStatus = Integer.toString(rs.getInt("AttendanceStatus"));
				
				// Add into the html table
				output += "<tr><td>" + Technician_AttendanceID + "</td>";
				output += "<td>" + TechnicianID + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + AttendanceStatus + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readTechnicianAttendanceByID(String TechnicianID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Technician Attendance ID</th><th>Technician ID</th>" +
					"<th>Date</th>" +
					"<th>Attendance Status</th>";
			String query = "select * from technicians_attendance_details where TechnicianID='"+TechnicianID+"'";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Technician_AttendanceID = Integer.toString(rs.getInt("Technician_AttendanceID"));
				TechnicianID = Integer.toString(rs.getInt("TechnicianID"));
				String Date = rs.getString("Date");
				String AttendanceStatus = Integer.toString(rs.getInt("AttendanceStatus"));
				
				// Add into the html table
				output += "<tr><td>" + Technician_AttendanceID + "</td>";
				output += "<td>" + TechnicianID + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + AttendanceStatus + "</td>";
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
	
	
	public String updateTechnicianAttendance(String Technician_AttendanceID, String TechnicianID, String Date, String AttendanceStatus)
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
			String query = "UPDATE technicians_attendance_details SET TechnicianID=?,Date=?,AttendanceStatus=? WHERE Technician_AttendanceID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(TechnicianID));
			preparedStmt.setString(2, Date);
			preparedStmt.setInt(3, Integer.parseInt(AttendanceStatus));
			preparedStmt.setInt(4, Integer.parseInt(Technician_AttendanceID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteTechnicianAttendance(String Technician_AttendanceID)
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
			String query = "delete from technicians_attendance_details where Technician_AttendanceID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Technician_AttendanceID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the attendance details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
