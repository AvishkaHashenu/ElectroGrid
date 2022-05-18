package com.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.CalcUtility;
import util.TableHtml;
import util.DBHandler;

public class Power_Consumption extends DBHandler{
	
	
	final static String tableName = "power_consumption";

	//insert consumption
	public String insertConsumption(String currentReading, String date, String type, String readerID, String userID) {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database";
			}
			
			//retrieve the account number of the user
			String[] aa = Power_Consumption.getActNumberById(userID);
			 
			if(aa[0].equals("error")) {
				return "Error while retrieve the account number of the user";
			}
			
			String ActNo = aa[1]; 
			
			//calculation { current reading - previous reading of the same user to get consume units of the month}
			
			int[] noUnit = CalcUtility.calculateUsedUnits(Integer.parseInt(currentReading), ActNo, date);
			
			//database status validation
			if(noUnit[0]==-1) {
				return "Error while connecting to the database!";
			}
			else if(noUnit[0]==-2) {
				return "Error while reading previous reading";
			}
			else if(noUnit[1]<0){
				return "Incorrect input: Number of units in Current reading cannot be less than previous reading ";
			}
			
			
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = date.split("-");
			
			//monthYear
			String monthYear = dateArray[0] + dateArray[1]; //parse the date into month and year
			
			
			
			//type selection query add constraints
			
			
			
			
			//creating record id from date and actNo : { PC00000YYYYDD }
			String recordID = "PC"+ActNo+dateArray[0]+dateArray[1];
			
			
			
			//9
			// create a prepared statement
			String query = "insert into " + tableName + " (`recordID`,`Electricity_AccountNo`,`CurrentReading`,`NoOfUnits`,`date`,`monthYear`,`type`,`ReaderID`,`userID`)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, recordID);
			preparedStmt.setString(2, ActNo);
			preparedStmt.setInt(3, Integer.parseInt(currentReading));
			preparedStmt.setInt(4, noUnit[1]);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, monthYear);
			preparedStmt.setString(7, type);
			preparedStmt.setString(8, readerID);
			preparedStmt.setString(9, userID);

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



	//read table
	public String readConsumption() {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = TableHtml.getHtml();
			
			String query = "select * from "+ tableName ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String recordID = rs.getString("recordID");
				String actno = rs.getString("Electricity_AccountNo");
				String cread = Integer.toString(rs.getInt("CurrentReading"));
				String nounit = Integer.toString(rs.getInt("NoOfUnits"));
				String monthYear = rs.getString("monthYear");
				String date = rs.getString("date");
				String type = rs.getString("type");
				String ReaderID = rs.getString("ReaderID");
				String userID = rs.getString("userID");
				
					
				// Add a row into the html table
				output += "<tr><td>" + recordID + "</td>" + "<td>" + actno + "</td>";
				output += "<td>" + cread + "</td>" + "<td>" + nounit + "</td>";
				output += "<td>" + date + "</td>" + "<td>" + monthYear + "</td>";
				output += "<td>" + type + "</td>" + "<td>" + ReaderID + "</td>";
				output += "<td>" + userID + "</td>";

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
	public String updateConsumption(String record, String currentReading, String date, String type, String readerID, String userID){
		String output = "";
		try
		{
			Connection con = getConnection();
			if (con == null)
			{return "Error while connecting to the database for updating.";
			}
			
			
			//retrieve the account number of the user
			String[] aa = Power_Consumption.getActNumberById(userID);
			 
			if(aa[0].equals("error")) {
				return "Error while retrieve the account number of the user";
			}
			
			String ActNo = aa[1]; 
			
			
			//do calculation { current reading - previous reading} noUnit = getResult(currentReading);
			int[] noUnit = CalcUtility.calculateUsedUnits(Integer.parseInt(currentReading), ActNo, date);
			
			if(noUnit[0]==-1) {
				return "Error while connecting to the database";
			}
			else if(noUnit[0]==-2) {
				return "Error while reading previous reading";
			}
			else if(noUnit[1]<0){
				return "Incorrect input: Number of units in Current reading cannot be less than previous reading ";
			}
			
				
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = date.split("-");
			
			//monthYear
			String monthYear = dateArray[0] + dateArray[1]; //parse the date into month and year
			
						
			//type selection query add constraints
			
			
			//creating record id from date and actNo : { PC00000YYYYDD }
			String recordID = "PC"+ActNo+dateArray[0]+dateArray[1];
			
			
			// create a prepared statement
			String query = "UPDATE "+ tableName + " SET recordID=?, Electricity_AccountNo=?, CurrentReading=?, NoOfUnits=? ,"
					+ " date=? , monthYear=?, type=?, ReaderID=?, userID=? "
					+ " WHERE recordID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, recordID);
			preparedStmt.setString(2, ActNo);
			preparedStmt.setInt(3, Integer.parseInt(currentReading));
			preparedStmt.setInt(4, noUnit[1]);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, monthYear);
			preparedStmt.setString(7, type);
			preparedStmt.setString(8, readerID);
			preparedStmt.setString(9, userID);
			preparedStmt.setString(10, record);

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
	public String deleteConsumption(String recordID)
	{
		String output = "";
		try
		{
			Connection con = getConnection();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from "+ tableName + " where recordID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, recordID);
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

	
	public String readConsumptionById(String key) {
		String output = "";
		try {
			Connection con = getConnection();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = TableHtml.getHtml();
			
			String query = "select * from "+ tableName + " where Electricity_AccountNo ='" + key + "'"  ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String recordID = rs.getString("recordID");
				String actno = rs.getString("Electricity_AccountNo");
				String cread = Integer.toString(rs.getInt("CurrentReading"));
				String nounit = Integer.toString(rs.getInt("NoOfUnits"));
				String monthYear = rs.getString("monthYear");
				String date = rs.getString("date");
				String type = rs.getString("type");
				String ReaderID = rs.getString("ReaderID");
				String userID = rs.getString("userID");
				
					
				// Add a row into the html table
				output += "<tr><td>" + recordID + "</td>" + "<td>" + actno + "</td>";
				output += "<td>" + cread + "</td>" + "<td>" + nounit + "</td>";
				output += "<td>" + date + "</td>" + "<td>" + monthYear + "</td>";
				output += "<td>" + type + "</td>" + "<td>" + ReaderID + "</td>";
				output += "<td>" + userID + "</td>";

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
	
	
	
	public static int[] readPreviousReading(String key, String actnum) {
		int[] output = new int[] {-1,-1};
		
		try {
			Connection con = getConnection();
			if (con == null) {
				return output;
			}
						
			
			/* Example query to get previous reading	
			
			 SELECT currentReading
			 FROM electrogrid.power_consumption
			 where Electricity_AccountNo LIKE '111111' AND date IN (SELECT max(date)
																	FROM electrogrid.power_consumption
																	where Electricity_AccountNo LIKE '111111' AND date < '2022-03-04'  );  */
			
			
			String subquery = "( SELECT max(date) FROM " + tableName
					+ " where Electricity_AccountNo LIKE '"+ actnum +"' AND date < '"+ key +"'  )";
			
			String query = "SELECT currentReading FROM "+ tableName 
					+" where Electricity_AccountNo LIKE '"+ actnum +"' AND date IN " + subquery;
			
			
			
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()  ) {
				
				output[1] = rs.getInt("CurrentReading");						
			}
			else{
				output[1] = 0;
			}
			con.close();

		} catch (Exception e) {
			output[0] = -2; //"Error while reading the database." + e.getMessage();
			System.err.println(e.getMessage());
		}
		
		output[0] = 0;
		return output;
	}

	private static String [] getActNumberById(String userID) {
		String[] valueArr = new String[] {"error","Error while connecting to the database!"};
		try {
			Connection con = getConnection();
			if (con == null) {
				//return "Error while connecting to the database for reading.";
				return valueArr;
			}
		
			
			String query = "SELECT accountNumber FROM electrogrid.user where userID ='" + userID + "'"  ;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()) {
				String actno = rs.getString("accountNumber");
				valueArr[1] = actno;
				valueArr[0] = "Ok";
			}
			con.close();
			// Complete the html table
		} catch (Exception e) {
			valueArr[1] = "Error while reading the database." + e.getMessage();
			System.err.println(e.getMessage());
			return null;
		}
		return valueArr;

	}
	

}
