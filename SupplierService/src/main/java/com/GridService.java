package com;

import model.Grid; 

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Grid") 

public class GridService {
	
	  Grid gridObj = new Grid(); 
		
	  //view all grids
	  @GET 
	  @Path("/") 
	  @Produces(MediaType.TEXT_HTML) 
	  public String readGrid() 
	  { 
	    return gridObj.readGrid(); 
	  }
	  
	  
	  //view grids by ID
	  @GET
	  @Path("/gridByID")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.TEXT_PLAIN)
	  public String readGridByID(String gridIDData)
	  {
	  	//Convert the input string to a JSON object 
	  	JsonObject gridObject = new JsonParser().parse(gridIDData).getAsJsonObject();
	  	
	  	//Read the values from the JSON object
	  	String gridId = gridObject.get("gridID").getAsString();
	  	
	  	return gridObj.readGridByID(gridId);				  
	  }
	  
	  
	  //insert grid
	  @POST 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String insertGrid(String gridData) 
	  {
		  //Convert the input string to a JSON object 
		  JsonObject gridObject = new JsonParser().parse(gridData).getAsJsonObject();
		  
		  //Read the values from the JSON object
		  String name = gridObject.get("name").getAsString();
		  String resourceType = gridObject.get("resourceType").getAsString();
		  String resourceCategory = gridObject.get("resourceCategory").getAsString();
		  String totalCapacity = gridObject.get("totalCapacity").getAsString();
		  String address = gridObject.get("address").getAsString();
		  String phone = gridObject.get("phone").getAsString();
		  String powerSupplierID = gridObject.get("powerSupplierID").getAsString();
		  
		  String output = gridObj.insertGrid(name, resourceType, resourceCategory, totalCapacity, address, phone, powerSupplierID);
		  
		  return output;
	  }
	  
	  
	  //update grid
	  @PUT 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String updateGrid(String gridData) 
	  { 
	    //Convert the input string to a JSON object 
	    JsonObject gridObject = new JsonParser().parse(gridData).getAsJsonObject(); 
	   
	    //Read the values from the JSON object 
	    String gridID = gridObject.get("gridID").getAsString(); 
	    String name = gridObject.get("name").getAsString(); 
	    String resourceType = gridObject.get("resourceType").getAsString(); 
	    String resourceCategory = gridObject.get("resourceCategory").getAsString();
	    String totalCapacity = gridObject.get("totalCapacity").getAsString(); 
	    String address = gridObject.get("address").getAsString();
	    String phone = gridObject.get("phone").getAsString(); 
	    String powerSupplierID = gridObject.get("powerSupplierID").getAsString();
	   
	    String output = gridObj.updateGrid(gridID, name, resourceType, resourceCategory, totalCapacity, address, phone, powerSupplierID); 
	   
	    return output; 
	  }  
	  
	  
	  //delete grid
	  @DELETE 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String deleteGrid(String gridData)
	  {
			//Convert the input string to a JSON object 
			JsonObject gridObject = new JsonParser().parse(gridData).getAsJsonObject();
			
			//Read the values from the JSON object
			String gridID = gridObject.get("gridID").getAsString();
			
			String output = gridObj.deleteGrid(gridID);
			
			return output;
	  }

}
