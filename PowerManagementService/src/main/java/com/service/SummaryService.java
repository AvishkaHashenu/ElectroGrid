package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Model.PowerSummary;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/getSummary")
public class SummaryService {
	PowerSummary Obj = new PowerSummary();
	
	//reads
	@GET
	@Path("/monthly/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getSummary(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String capID = itemObject.get("key").getAsString();

		

		
		String[] output = Obj.getSummaryArray(capID); 
		return output[1];
	}
	
	
	//reads
	@GET
	@Path("/Annual/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getYearSummary(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String capID = itemObject.get("key").getAsString();

		

		
		String[] output = Obj.getAnnualSummaryTable(capID); 
		return output[1];
	}
}
