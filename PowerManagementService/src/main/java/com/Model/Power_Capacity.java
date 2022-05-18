package com.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.TableHtml;
import util.DBHandler;

public class Power_Capacity extends DBHandler{
	
	
	final String tableName = "power_capacity";


	//insert items
	public String insertCapacity(String SupNo, String date, String Type, String units) {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database";
			}
			
			//int noUnit = 0;  //do calculation { current reading - previous reading} noUnit = getResult(currentReading);
			
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = date.split("-");
			
			
			//type selection query add constraints
			
			
			
			
			//creating record id from date
			String capacityID = "CID"+SupNo+dateArray[0]+dateArray[1];
			
			
			
			//5
			// create a prepared statement
			String query = "insert into " + tableName + " (`capacityID`,`date`,`Type`,`noOfUnits`,`supplierID`)"
			+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, capacityID);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, Type);
			preparedStmt.setInt(4, Integer.parseInt(units));
			preparedStmt.setString(5, SupNo);


			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting: " +  e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCapacity() {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = TableHtml.getHtmlCapacity();
			
			String query = "select * from "+ tableName ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String capacityID = rs.getString("capacityID");
				String date = rs.getString("date");
				String Type = rs.getString("Type");
				String nounit = Integer.toString(rs.getInt("noOfUnits"));
				String SupNo = rs.getString("supplierID");
	;
				
					
				// Add a row into the html table
				output += "<tr><td>" + capacityID + "</td>" + "<td>" + date + "</td>";
				output += "<td>" + Type + "</td>" + "<td>" + nounit + "</td>";
				output += "<td>" + SupNo + "</td>" ;
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the database." + e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//Update table
	public String updateCapacity(String capID, String SupNo, String date, String Type, String units){
		String output = "";
		try
		{
			Connection con = getConnection();
			if (con == null)
			{return "Error while connecting to the database for updating.";
			}
										
			//type selection query add constraints
			
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = date.split("-");
			
			
			//type selection query add constraints
			
			
			
			
			//creating record id from date
			String capacityID = "CID"+SupNo+dateArray[0]+dateArray[1];
			
			
			
			// create a prepared statement
			String query = "UPDATE "+ tableName + " SET capacityID=?, date=?, Type=?, noOfUnits=?, supplierID=? "
					+ " WHERE capacityID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, capacityID);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, Type);
			preparedStmt.setInt(4, Integer.parseInt(units));
			preparedStmt.setString(5, SupNo);
			preparedStmt.setString(6, capID);


			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the database.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String deleteCapacity(String capacityID)
	{
		String output = "";
		try
		{
			Connection con = getConnection();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from "+ tableName + " where capacityID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, capacityID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the value.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	public String readCapacityById(String key) {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = TableHtml.getHtmlCapacity();
			
			String query = "select * from "+ tableName + " where supplierID = '"+ key +"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String capacityID = rs.getString("capacityID");
				String date = rs.getString("date");
				String Type = rs.getString("Type");
				String nounit = Integer.toString(rs.getInt("noOfUnits"));
				String SupNo = rs.getString("supplierID");
	;
				
					
				// Add a row into the html table
				output += "<tr><td>" + capacityID + "</td>" + "<td>" + date + "</td>";
				output += "<td>" + Type + "</td>" + "<td>" + nounit + "</td>";
				output += "<td>" + SupNo + "</td>" ;
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the database." + e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}



}
