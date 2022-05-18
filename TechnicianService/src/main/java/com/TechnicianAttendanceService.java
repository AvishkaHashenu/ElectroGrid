package com;

import model.TechniciansAttendance;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/TechniciansAttendance")

public class TechnicianAttendanceService {
	
	TechniciansAttendance attObj = new TechniciansAttendance();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertTechnicianAttendance(String techData)
	{
		JsonObject attObject = new JsonParser().parse(techData).getAsJsonObject();
		String Technician_AttendanceID = attObject.get("Technician_AttendanceID").getAsString();
		String TechnicianID = attObject.get("TechnicianID").getAsString();
		//String Date = attObject.get("Date").getAsString();
		String AttendanceStatus = attObject.get("AttendanceStatus").getAsString();
		String output = attObj.insertTechnicianAttendance(Technician_AttendanceID, TechnicianID, AttendanceStatus);
		return output;
	}
	
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readTechnicianAttendance()
	{
		return attObj.readTechnicianAttendance();
	}
	
	
	//Get User By ID
		@GET
		@Path("/getTechnicianAttendancebyID")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String TechnicianAttendanceDetails(String TechnicianIdData) {
			//Convert the input string to a JSON object 
			JsonObject techniciabObject = new JsonParser().parse(TechnicianIdData).getAsJsonObject();
			//Read the values from the JSON object
			String TechnicianID = techniciabObject.get("TechnicianID").getAsString();
			return attObj.readTechnicianAttendanceByID(TechnicianID);
		}
		
		
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTechnicianAttendance(String techData)
	{
		//Convert the input string to a JSON object
		JsonObject attObject = new JsonParser().parse(techData).getAsJsonObject();
		//Read the values from the JSON object
		String Technician_AttendanceID = attObject.get("Technician_AttendanceID").getAsString();
		String TechnicianID = attObject.get("TechnicianID").getAsString();
		String Date = attObject.get("Date").getAsString();
		String AttendanceStatus = attObject.get("AttendanceStatus").getAsString();
		String output = attObj.updateTechnicianAttendance(Technician_AttendanceID, TechnicianID, Date, AttendanceStatus);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTechnicianAttendance(String techData)
	{
		//Convert the input string to a JSON object
		JsonObject attObject = new JsonParser().parse(techData).getAsJsonObject();
		//Read the value from the element <itemID>
		String Technician_AttendanceID = attObject.get("Technician_AttendanceID").getAsString();
		String output = attObj.deleteTechnicianAttendance(Technician_AttendanceID);
		return output;
	}

}
