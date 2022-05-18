package com;
import model.Technician;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Technicians")

public class TechnicianService {
	
	Technician technicianObj = new Technician();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertTechnician(String techData)
	{ 
		JsonObject attObject = new JsonParser().parse(techData).getAsJsonObject();
		//String output = technicianObj.insertTechnician(TechnicianName, TechnicianAddress, TechnicianEmail, TechnicianPhone, Type, ContractTech_Salary, HourlyTech_HourlyWages );
		String TechnicianName = attObject.get("TechnicianName").getAsString();
		String TechnicianAddress = attObject.get("TechnicianAddress").getAsString();
		String TechnicianEmail = attObject.get("TechnicianEmail").getAsString();
		String TechnicianPhone = attObject.get("TechnicianPhone").getAsString();
		String Type = attObject.get("Type").getAsString();
		String ContractTech_Salary = attObject.get("ContractTech_Salary").getAsString();
		String HourlyTech_HourlyWages = attObject.get("HourlyTech_HourlyWages").getAsString();
		String output = technicianObj.insertTechnician(TechnicianName, TechnicianAddress, TechnicianEmail, TechnicianPhone, Type, ContractTech_Salary, HourlyTech_HourlyWages);
		return output; 
	}
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readTechnicians()
	{
	return technicianObj.readTechnicians();
	}
	
	
	//Get User By ID
	@GET
	@Path("/getTechnicianbyID")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String TechnicianProfileDetails(String TechnicianIdData) {
		//Convert the input string to a JSON object 
		JsonObject techniciabObject = new JsonParser().parse(TechnicianIdData).getAsJsonObject();
		//Read the values from the JSON object
		String TechnicianID = techniciabObject.get("TechnicianID").getAsString();
		return technicianObj.readTechnicianByID(TechnicianID);
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTechnician(String techData)
	{
	//Convert the input string to a JSON object
	JsonObject techObject = new JsonParser().parse(techData).getAsJsonObject();
	//Read the values from the JSON object
	String TechnicianID = techObject.get("TechnicianID").getAsString();
	String TechnicianName = techObject.get("TechnicianName").getAsString();
	String TechnicianAddress = techObject.get("TechnicianAddress").getAsString();
	String TechnicianEmail = techObject.get("TechnicianEmail").getAsString();
	String TechnicianPhone = techObject.get("TechnicianPhone").getAsString();
	String Type = techObject.get("Type").getAsString();
	String ContractTech_Salary = techObject.get("ContractTech_Salary").getAsString();
	String HourlyTech_HourlyWages = techObject.get("HourlyTech_HourlyWages").getAsString();
	String output = technicianObj.updateTechnician(TechnicianID, TechnicianName, TechnicianAddress, TechnicianEmail, TechnicianPhone, Type, ContractTech_Salary, HourlyTech_HourlyWages);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTechnician(String techData)
	{
		//Convert the input string to an XML document
		JsonObject techObject = new JsonParser().parse(techData).getAsJsonObject();
		//Read the value from the element <itemID>
		String TechnicianID = techObject.get("TechnicianID").getAsString();
		String output = technicianObj.deleteTechnician(TechnicianID);
		return output;
	}

}
