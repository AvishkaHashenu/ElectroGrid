package com;

import model.Payment;
import model.Payment_Info; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/payment") 

public class PaymentService {
	Payment paymentsObj = new Payment();
	

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayment (String paymentData)
	{
		
		JsonObject paymentsObject = new JsonParser().parse(paymentData).getAsJsonObject();
		String PaymentID = paymentsObject.get("PaymentID").getAsString();
		String UserID = paymentsObject.get("UserID").getAsString();
		String PowerSupplierID = paymentsObject.get("PowerSupplierID").getAsString();
		String Payment_Method = paymentsObject.get("Payment_Method").getAsString();
		String Payment_Date = paymentsObject.get("Payment_Date").getAsString();
		String Amount = paymentsObject.get("Amount").getAsString();
		String Type = paymentsObject.get("Type").getAsString();
		String Consumption_Unit = paymentsObject.get("Consumption_Unit").getAsString();
		String NoOfUnits = paymentsObject.get("NoOfUnits").getAsString();
		
	 
	 String output = paymentsObj.insertPayment(PaymentID,UserID,PowerSupplierID,Payment_Method,Payment_Date,Amount,Type,Consumption_Unit,NoOfUnits); 
	return output;
	
}
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment(){

		return paymentsObj.readPayment();

	
}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String Paymentdata) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentsObject = new JsonParser().parse(Paymentdata).getAsJsonObject(); 
	//Read the values from the JSON object
	 String PaymentID = paymentsObject.get("PaymentID").getAsString(); 
	 String UserID = paymentsObject.get("UserID").getAsString(); 
	 String PowerSupplierID = paymentsObject.get("PowerSupplierID").getAsString(); 
	 String Payment_Method = paymentsObject.get("Payment_Method").getAsString(); 
	 String Payment_Date = paymentsObject.get("Payment_Date").getAsString();
	 String Amount = paymentsObject.get("Amount").getAsString();
	 String Type = paymentsObject.get("Type").getAsString();
	 String Consumption_Unit = paymentsObject.get("Consumption_Unit").getAsString();
	 String NoOfUnits = paymentsObject.get("NoOfUnits").getAsString();
	 
	 String output = paymentsObj.updatePayment(PaymentID,UserID,PowerSupplierID,Payment_Method,Payment_Date,Amount,Type,Consumption_Unit,NoOfUnits); 
	return output;
}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String Paymentdata) 
	{ 
	//Convert the input string to an XML document
		JsonObject paymentsObject = new JsonParser().parse(Paymentdata).getAsJsonObject();
	 
	 
	//Read the value from the element <itemID>
		String PaymentID = paymentsObject.get("PaymentID").getAsString();
		String output = paymentsObj.deletePayment(PaymentID);	
		
	return output; 
}
}
