package com.Model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import util.CalcUtility;
import util.DBHandler;
import util.TableHtml;

public class PowerSummary extends DBHandler{
	
	
	final static String Consumption = "power_consumption";
	final static String Capacity = "power_capacity";


	
	//git
	
	
	public static int[] getSumConsumeUnits(String key) {
		int[] output = new int[] {-1,-1};
		
		try {
			Connection con = getConnection();
			if (con == null) {
				return output;
			}
						
			
			/* Example query to get previous sum	
			
			SELECT sum(noOfUnits) FROM electrogrid.power_consumption
			where date like '2022-02%'; 

			*/
			
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = key.split("-");
			String YYMM = dateArray[0]+"-"+dateArray[1]+"%";
			

			
			String query = "SELECT sum(noOfUnits)  FROM " + Consumption
					+ " where date like '"+ YYMM +"'" ;
			
			
			
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()  ) {
				
				output[1] = rs.getInt("sum(noOfUnits)");						
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
	
	
	
	public static int[] getSumCapacity(String key) {
		int[] output = new int[] {-1,-1};
		
		try {
			Connection con = getConnection();
			if (con == null) {
				return output;
			}
						
			
			/* Example query to get sum; 
			
			
			SELECT sum(noOfUnits) FROM electrogrid.power_capacity
			where date like '2022-02%';
			*/
			
			//Split date into array [ YYYY, MM, DD ]
			String[] dateArray = key.split("-");
			String YYMM = dateArray[0]+"-"+dateArray[1]+"%";
			

			
			String query = "SELECT sum(noOfUnits)  FROM " + Capacity
					+ " where date like '"+ YYMM +"'" ;
			
			
			
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			if (rs.next()  ) {
				
				output[1] = rs.getInt("sum(noOfUnits)");						
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

	
	public String[] getSummaryArray(String key) {
		
		String[] valueArr = new String[] {"error","Error while connecting to the database!"};
		
		int[] ConsumeUnits = PowerSummary.getSumConsumeUnits(key);
		int[] SumCapacity = PowerSummary.getSumCapacity(key);
		
		
		//checking whether any errors when retrieving data from database
		if(ConsumeUnits[0]==-1 || SumCapacity[0] == -1) {
			return valueArr;
		}
		else if(ConsumeUnits[0]==-2 || SumCapacity[0] == -2) {
			valueArr[1] = "Error while reading sum of units"; 
			return valueArr;
		}
		
		
		valueArr[0] = "Ok";
		
		int total = SumCapacity[1] - ConsumeUnits[1];
		
		String[] dateArray = key.split("-");
		valueArr[1] = "<h3>Summary for "+CalcUtility.getMonthForInt(Integer.parseInt(dateArray[1]))+" " +dateArray[0]+ "</h3>";
		
		valueArr[1] += "<ul> <li>Total power generation units added to ElectroGrid: " +SumCapacity[1] +" kWh</li> "
					 + "<li>Total number of unit consumed by user : "+ ConsumeUnits[1]+ " kWh</li> ";
		
		if(total < 0) {
       valueArr[1] +=  "<li>Extra units added to the system : " + (total*-1)+ "kWh</li> "
					 + "<li>Remaining units : 0 kWh</li> </ul>";
			return valueArr;
		}
		
	   valueArr[1] +=  "<li>Remaining units :" + total+ " kWh</li> </ul>";
		return valueArr;
		
		
		
	}
	
	
	public String[] getAnnualSummaryTable(String key) {
		
		String[] valueArr = new String[] {"error",""};
		
		String[] dateArray = key.split("-");
		int[] ConsumeUnits;
		int[] SumCapacity;
		
		int totconsumption =0 , totCapacity = 0;
		
		valueArr[1] += "<h3>Summary for "+dateArray[0]+ " </h3>" +TableHtml.getHtmlSummery();
		
		for(int i=1; i<=12; i++) {
			if(i<9) {
				ConsumeUnits = PowerSummary.getSumConsumeUnits(dateArray[0]+"-0"+i);
				SumCapacity = PowerSummary.getSumCapacity(dateArray[0]+"-0"+i);
			}
			else {
				ConsumeUnits = PowerSummary.getSumConsumeUnits(dateArray[0]+"-"+i);
				SumCapacity = PowerSummary.getSumCapacity(dateArray[0]+"-"+i);
			}
			
			
			
			//checking whether any errors when retrieving data from database
			if(ConsumeUnits[0]==-1 || SumCapacity[0] == -1) {
				valueArr[0] = "error";
				valueArr[1] = "Error while connecting to the database!"; 
				return valueArr;
			}
			else if(ConsumeUnits[0]==-2 || SumCapacity[0] == -2) {
				valueArr[0] = "error";
				valueArr[1] = "Error while reading sum of units"; 
				return valueArr;
			}
			
			
			valueArr[0] = "Ok";
			
			int total = SumCapacity[1] - ConsumeUnits[1];
			totconsumption += ConsumeUnits[1];
			totCapacity += SumCapacity[1];
			
			valueArr[1] +="<tr> <td>" + CalcUtility.getMonthForInt(i) + "</td>";
			valueArr[1] += "<td>" +SumCapacity[1] +"</td> "
					+ "<td>"+ ConsumeUnits[1] + "</td> ";
			
			if(total < 0) {
				valueArr[1] += "<td> 0 </td> </td>"
						+"<td>" + (total*-1) + "</td> ";
						 
			}
			else {
				valueArr[1] +=  "<td>" + total+ "</td>  <td> 0 </td> </tr>";
			}
			
			
			
			
			
		}
		
		valueArr[1] += "<ul> <li> Total consumption: " + totconsumption + " kWh</li>";
		valueArr[1] += " <li> Total Capacity: " + totCapacity + " kWh</li>";
		int r = totCapacity - totconsumption;
		if(r>0) {
			valueArr[1] += "<li> Total Remaining Units: " + r + " kWh</li></ul>";
		}
		else {
			valueArr[1] += "<li> Total Extra Units: " + (r*-1) + " kWh</li> </ul>";
		}
		
		return valueArr;
			
		
	}
	

}
