package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Breakdown;

@Path("/Breakdowns")
public class BreakdownService {

	Breakdown breakdownObj = new Breakdown();

	//Retrieve Breakdowns

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBreakdowns() {
		return breakdownObj.readBreakdowns();
	}

	//Get Breakdown By ID

	@GET
	@Path("/getBreakdownbyID")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String BreakdownDetails(String breakdownIdData) {
		//Convert the input string to a JSON object 
		JsonObject breakdownIDObject = new JsonParser().parse(breakdownIdData).getAsJsonObject();
		//Read the values from the JSON object
		String breakdownID = breakdownIDObject.get("breakdownID").getAsString();
		return breakdownObj.readBreakdownByID(breakdownID);
	}

	//Create Breakdown Complain

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBreakdown(String breakdownData) {
		//Convert the input string to a JSON object 
		JsonObject breakdownObject = new JsonParser().parse(breakdownData).getAsJsonObject();
		//Read the values from the JSON object
		String region = breakdownObject.get("region").getAsString();
		String description = breakdownObject.get("description").getAsString();
		String phone = breakdownObject.get("phone").getAsString();
		String user = breakdownObject.get("user").getAsString();
		String output = breakdownObj.insertBreakdown(region, description, phone, user);
		return output;
	}

	// Update Breakdown Complain

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBreakdown(String breakdownData) {
		//Convert the input string to a JSON object 
		JsonObject breakdownObject = new JsonParser().parse(breakdownData).getAsJsonObject();
		//Read the values from the JSON object
		String breakdownID = breakdownObject.get("breakdownID").getAsString();
		String region = breakdownObject.get("region").getAsString();
		String description = breakdownObject.get("description").getAsString();
		String phone = breakdownObject.get("phone").getAsString();
		String user = breakdownObject.get("user").getAsString();
		String output = breakdownObj.updateBreakdown(breakdownID, region, description, phone, user);
		return output;
	}

	//Delete Breakdown Complain

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBreakdown(String breakdownData) {
		//Convert the input string to a JSON object 
		JsonObject breakdownObject = new JsonParser().parse(breakdownData).getAsJsonObject();
		//Read the values from the JSON object
		String breakdownID = breakdownObject.get("breakdownID").getAsString();
		String output = breakdownObj.deleteBreakdown(breakdownID);
		return output;
	}

	// Update Breakdown Status

	@PUT
	@Path("/updateStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStatus(String breakdownStatusData) {
		//Convert the input string to a JSON object 
		JsonObject breakdownStatusObject = new JsonParser().parse(breakdownStatusData).getAsJsonObject();
		//Read the values from the JSON object
		String breakdownID = breakdownStatusObject.get("breakdownID").getAsString();
		String status = breakdownStatusObject.get("status").getAsString();
		String output = breakdownObj.updateStatus(breakdownID, status);
		return output;
	}
}
