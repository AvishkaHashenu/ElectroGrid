package com;

import model.Supplier; 

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Supplier") 

public class SupplierService {
	
	  Supplier supplierObj = new Supplier(); 
	  
	  //view all suppliers
	  @GET 
	  @Path("/") 
	  @Produces(MediaType.TEXT_HTML) 
	  public String readSuppliers() 
	  { 
	    return supplierObj.readSuppliers(); 
	  }
	  
	  
	  //view suppliers by ID
	  @GET
	  @Path("/supplierByID")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.TEXT_PLAIN)
	  public String readSupplierByID(String powerSupplierIDData)
	  {
		//Convert the input string to a JSON object 
		JsonObject supplierObject = new JsonParser().parse(powerSupplierIDData).getAsJsonObject();
		
		//Read the values from the JSON object
		String powerSupplierId = supplierObject.get("powerSupplierID").getAsString();
		
		return supplierObj.readSupplierByID(powerSupplierId);				  
	  }
	  
	  
	  //insert supplier
	  @POST 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String insertSupplier(String supplierData) 
	  {
		  //Convert the input string to a JSON object 
		  JsonObject supplierObject = new JsonParser().parse(supplierData).getAsJsonObject();
		  
		  //Read the values from the JSON object
		  String name = supplierObject.get("name").getAsString();
		  String address = supplierObject.get("address").getAsString();
		  String NIC = supplierObject.get("NIC").getAsString();
		  String phone = supplierObject.get("phone").getAsString();
		  
		  String output = supplierObj.insertSupplier(name, address, NIC, phone);
		  
		  return output;
	  }
	  
	  
	  //update supplier
	  @PUT 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String updateSupplier(String supplierData) 
	  { 
	    //Convert the input string to a JSON object 
	    JsonObject supplierObject = new JsonParser().parse(supplierData).getAsJsonObject(); 
	   
	    //Read the values from the JSON object 
	    String powerSupplierID = supplierObject.get("powerSupplierID").getAsString(); 
	    String name = supplierObject.get("name").getAsString(); 
	    String address = supplierObject.get("address").getAsString(); 
	    String NIC = supplierObject.get("NIC").getAsString(); 
	    String phone = supplierObject.get("phone").getAsString(); 
	   
	    String output = supplierObj.updateSupplier(powerSupplierID, name, address, NIC, phone); 
	   
	    return output; 
	  } 
	  
	  
	  //delete supplier
	  @DELETE 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String deleteSupplier(String supplierData)
	  {
			//Convert the input string to a JSON object 
			JsonObject supplierObject = new JsonParser().parse(supplierData).getAsJsonObject();
			
			//Read the values from the JSON object
			String powerSupplierID = supplierObject.get("powerSupplierID").getAsString();
			
			String output = supplierObj.deleteSupplier(powerSupplierID);
			
			return output;
	  }

}
