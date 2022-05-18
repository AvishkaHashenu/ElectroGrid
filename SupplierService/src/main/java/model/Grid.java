package model;

import java.sql.*;

public class Grid {
	
	  //A common method to connect to the DB 
	  private Connection connect() 
	  { 
	    Connection con = null; 
	 
	    try 
	    { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	       
	      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "database"); 
	    } 
	    catch (Exception e) 
	    {e.printStackTrace();} 
	 
	    return con; 
	  } 
	  
	 
	  //insert grid
	  public String insertGrid(String name, String resourceType, String resourceCategory, String totalCapacity, String address, String phone, String powerSupplierID) 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for inserting."; } 
	 
	      // create a prepared statement 
	      String query = " insert into power_grid(`gridID`,`name`,`resourceType`,`resourceCategory`,`totalCapacity`,`address`,`phone`,`powerSupplierID`)" 
	          + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setInt(1, 0); 
	      preparedStmt.setString(2, name); 
	      preparedStmt.setString(3, resourceType);
	      preparedStmt.setString(4, resourceCategory); 
	      preparedStmt.setInt(5, Integer.parseInt(totalCapacity)); 
	      preparedStmt.setString(6, address);
	      preparedStmt.setString(7, phone);
	      preparedStmt.setInt(8, Integer.parseInt(powerSupplierID)); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      output = "Grid inserted successfully"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while inserting the grid."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  } 
	  
	 
	  //view all grids
	  public String readGrid() 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for reading."; } 
	 
	      // Prepare the html table to be displayed 
	      output = "<table border='1'><tr><th>Grid ID</th><th>Grid name</th><th>Resource Type</th>" +
	    	"<th>Resource Category</th>" +
	        "<th>Total capacity(MW)</th>" +  
	        "<th>Address</th>" + 
	        "<th>Phone</th></tr>";   
	     
	      String query = "select * from power_grid"; 
	      Statement stmt = con.createStatement(); 
	      ResultSet rs = stmt.executeQuery(query); 
	 
	      // iterate through the rows in the result set 
	      while (rs.next()) 
	      { 
	        String gridID = Integer.toString(rs.getInt("gridID")); 
	        String name = rs.getString("name");
	        String resourceType = rs.getString("resourceType");
	        String resourceCategory = rs.getString("resourceCategory"); 
	        String totalCapacity = Integer.toString(rs.getInt("totalCapacity")); 
	        String address = rs.getString("address"); 
	        String phone = rs.getString("phone"); 
	 
	        // Add into the html table 
	        output += "<tr><td>" + gridID + "</td>"; 
	        output += "<td>" + name + "</td>";
	        output += "<td>" + resourceType + "</td>";
	        output += "<td>" + resourceCategory + "</td>";
	        output += "<td>" + totalCapacity + "</td>"; 
	        output += "<td>" + address + "</td>"; 
	        output += "<td>" + phone + "</td>"; 
	 
	      } 
	 
	      con.close(); 
	 
	      // Complete the html table 
	      output += "</table>"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while reading the grids."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  } 
	 
	  
	  //view grids by ID
	  public String readGridByID(String gridId) 
	  { 
	    String output = ""; 

	    try 
	    { 
	      Connection con = connect(); 

	      if (con == null) 
	      {return "Error while connecting to the database for reading."; } 

	      // Prepare the html table to be displayed 
	      output = "<table border='1'><tr><th>Grid ID</th><th>Grid name</th><th>Resource Type</th>" + 
	    	"<th>Resource Category</th>" +
	        "<th>Total capacity(MW)</th>" +  
	        "<th>Address</th>" + 
	        "<th>Phone</th></tr>";   
	     
	      String query = "select * from power_grid where gridID='"+gridId+"'";
	      Statement stmt = con.createStatement(); 
	      ResultSet rs = stmt.executeQuery(query); 

	      // iterate through the rows in the result set 
	      while (rs.next()) 
	      { 
	        String gridID = Integer.toString(rs.getInt("gridID")); 
	        String name = rs.getString("name"); 
	        String resourceType = rs.getString("resourceType");
	        String resourceCategory = rs.getString("resourceCategory");
	        String totalCapacity = Integer.toString(rs.getInt("totalCapacity")); 
	        String address = rs.getString("address"); 
	        String phone = rs.getString("phone"); 

	        // Add into the html table 
	        output += "<tr><td>" + gridID + "</td>"; 
	        output += "<td>" + name + "</td>";
	        output += "<td>" + resourceType + "</td>";
	        output += "<td>" + resourceCategory + "</td>"; 
	        output += "<td>" + totalCapacity + "</td>"; 
	        output += "<td>" + address + "</td>"; 
	        output += "<td>" + phone + "</td>"; 

	      } 

	      con.close(); 

	      // Complete the html table 
	      output += "</table>"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while reading the grids."; 
	      System.err.println(e.getMessage()); 
	    } 

	    return output; 
	  }
	  
	  
	  //update grids
	  public String updateGrid(String gridID, String name, String resourceType, String resourceCategory, String totalCapacity, String address, String phone, String powerSupplierID)
	   
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for updating."; } 
	 
	      // create a prepared statement 
	      String query = "UPDATE power_grid SET name=?,resourceType=?,resourceCategory=?,totalCapacity=?,address=?,phone=?,powerSupplierID=? WHERE gridID=?"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setString(1, name); 
	      preparedStmt.setString(2, resourceType);
	      preparedStmt.setString(3, resourceCategory); 
	      preparedStmt.setDouble(4, Integer.parseInt(totalCapacity)); 
	      preparedStmt.setString(5, address);
	      preparedStmt.setString(6, phone); 
	      preparedStmt.setDouble(7, Integer.parseInt(powerSupplierID));  
	      preparedStmt.setInt(8, Integer.parseInt(gridID)); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      output = "Grid updated successfully"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while updating the grid."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  }
	  
	 
	  //delete grids
	  public String deleteGrid(String gridID) 
	  { 
	    String output = ""; 
	 
	    try 
	    { 
	      Connection con = connect(); 
	 
	      if (con == null) 
	      {return "Error while connecting to the database for deleting."; } 
	 
	      // create a prepared statement 
	      String query = "delete from power_grid where gridID=?"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      // binding values 
	      preparedStmt.setInt(1, Integer.parseInt(gridID)); 
	 
	      // execute the statement 
	      preparedStmt.execute(); 
	      con.close(); 
	 
	      output = "Grid deleted successfully"; 
	    } 
	    catch (Exception e) 
	    { 
	      output = "Error while deleting the grid."; 
	      System.err.println(e.getMessage()); 
	    } 
	 
	    return output; 
	  }

}
