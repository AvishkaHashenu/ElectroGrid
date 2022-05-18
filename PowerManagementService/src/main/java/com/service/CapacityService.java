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
import com.Model.Power_Capacity;


@Path("/powerCapacity")
public class CapacityService {
	Power_Capacity Obj = new Power_Capacity();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCapacity(){
		
		return Obj.readCapacity();
	}
	
	
	//read
	@GET
	@Path("/id/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readCapacityById(String itemData){
		
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		String SupNo = itemObject.get("supplierID").getAsString();

	
		
		String output = Obj.readCapacityById(SupNo); 
		return output;
	}
	
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCapacity(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		String SupNo = itemObject.get("supplierID").getAsString();
		String date = itemObject.get("date").getAsString();
		String Type = itemObject.get("Type").getAsString();
		String units = itemObject.get("noOfUnits").getAsString();

	
		
		String output = Obj.insertCapacity(SupNo, date, Type, units); 
		return output;
	}
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCapacity(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String capID = itemObject.get("capacityID").getAsString();
		String SupNo = itemObject.get("supplierID").getAsString();
		String date = itemObject.get("date").getAsString();
		String Type = itemObject.get("Type").getAsString();
		String units = itemObject.get("noOfUnits").getAsString();
		

		
		String output = Obj.updateCapacity(capID, SupNo, date, Type, units); 
		return output;
	}
	
	
	//delete items
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCapacity(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		
		String recID = itemObject.get("capacityID").getAsString();
		String output = Obj.deleteCapacity(recID);
		return output;
	}
}
