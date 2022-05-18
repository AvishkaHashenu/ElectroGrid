package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

public class Breakdown {

	// A common method to connect to the DB
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

	public String insertBreakdown(String region, String description, String phone, String user) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into breakdown (`breakdownID`,`region`,`description`,`phone`,`user`,`date`,`status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			String strDate = dateFormat.format(date);
			Integer status = 0;
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, region);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, phone);
			preparedStmt.setInt(5, Integer.parseInt(user));
			preparedStmt.setString(6, strDate);
			preparedStmt.setInt(7, status);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Breakdown Reported Successfully!!!";
		} catch (Exception e) {
			output = "Error while reporting the breakdown";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBreakdowns() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Region</th><th>Description</th>" + "<th>Mobile Number</th>" + "<th>Status</th>" + "<th>User</th>" + "<th>Date</th>";

			String query = "select b.*, u.username, bs.description from breakdown b INNER JOIN user u ON b.user = u.userID INNER JOIN breakdown_status bs ON b.status = bs.value ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String breakdownID = Integer.toString(rs.getInt("b.breakdownID"));
				String region = rs.getString("b.region");
				String description = rs.getString("b.description");
				String phone = rs.getString("b.phone");
				String user = rs.getString("u.username");
				String date = rs.getString("b.date");
				String status = rs.getString("bs.description");
				// Add into the html table
				output += "<tr><td>" + region + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + user + "</td>";
				output += "<td>" + date + "</td>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the breakdowns.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBreakdownByID(String breakdownId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Breakdown ID</th><th>Region</th><th>Description</th>" + "<th>Mobile Number</th>" + "<th>Status</th>" + "<th>User</th>" + "<th>Date</th>";

			String query = "select b.*, u.username, bs.description from breakdown b INNER JOIN user u ON b.user = u.userID INNER JOIN breakdown_status bs ON b.status = bs.value where b.breakdownID='"+breakdownId+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String breakdownID = Integer.toString(rs.getInt("b.breakdownID"));
				String region = rs.getString("b.region");
				String description = rs.getString("b.description");
				String phone = rs.getString("b.phone");
				String user = rs.getString("u.username");
				String date = rs.getString("b.date");
				String status = rs.getString("bs.description");
				// Add into the html table
				output += "<tr><td>" + breakdownID + "</td>";
				output += "<td>" + region + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + user + "</td>";
				output += "<td>" + date + "</td>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the breakdown.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBreakdown(String breakdownID, String region, String description, String phone, String user) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE breakdown SET region=?,description=?,phone=?,user=? WHERE breakdownID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, region);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, phone);
			preparedStmt.setInt(4, Integer.parseInt(user));
			preparedStmt.setInt(5, Integer.parseInt(breakdownID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Breakdown Info Updated Successfully!";
		} catch (Exception e) {
			output = "Error while updating Breakdown Info.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBreakdown(String breakdownID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from breakdown where breakdownID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(breakdownID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Breakdown Complain Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the user account.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateStatus(String breakdownID, String status) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating status.";
			}
			// create a prepared statement
			String query = "UPDATE breakdown SET status=? WHERE breakdownID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(status));
			preparedStmt.setInt(2, Integer.parseInt(breakdownID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Breakdown Status Updated Successfully!";
		} catch (Exception e) {
			output = "Error while updating Breakdown Status.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
