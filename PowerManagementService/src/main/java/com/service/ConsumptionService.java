package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.Model.Power_Consumption;

@Path("/powerConsumption")
public class ConsumptionService {
	Power_Consumption Obj = new Power_Consumption();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption(){
		
		return Obj.readConsumption();
	}
	
	
	//read by id
	@GET
	@Path("/id/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readConsumptionById(String itemData){
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		String ActNo = itemObject.get("AccountNo").getAsString();
		return Obj.readConsumptionById(ActNo);
	}
	
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		//String ActNo = itemObject.get("AccountNo").getAsString();
		String currentReading = itemObject.get("currentReading").getAsString();
		String date = itemObject.get("date").getAsString();
		String type = itemObject.get("type").getAsString();
		String readerID = itemObject.get("readerID").getAsString();
		String userID = itemObject.get("userID").getAsString();
	
		
		String output = Obj.insertConsumption(currentReading, date, type, readerID, userID); 
		return output;
	}
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumption(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String recID = itemObject.get("recordID").getAsString();
		//String ActNo = itemObject.get("AccountNo").getAsString();
		String currentReading = itemObject.get("currentReading").getAsString();
		String date = itemObject.get("date").getAsString();
		String type = itemObject.get("type").getAsString();
		String readerID = itemObject.get("readerID").getAsString();
		String userID = itemObject.get("userID").getAsString();
		
		

		
		String output = Obj.updateConsumption(recID, currentReading, date, type, readerID, userID); 
		return output;
	}
	
	
	//delete items
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConsumption(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		
		String recID = itemObject.get("recordID").getAsString();
		String output = Obj.deleteConsumption(recID);
		return output;
	}
}
