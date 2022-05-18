package com;
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
@Path("/payment_info") 

public class Payment_InfoService {
	
	Payment_Info paymentObj = new Payment_Info();


	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayment (String paymentData)
	{
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		String CreditID = paymentObject.get("CreditID").getAsString();
		String UserID = paymentObject.get("UserID").getAsString();
		String SecurityNum = paymentObject.get("SecurityNum").getAsString();
		String ExpireDate = paymentObject.get("ExpireDate").getAsString();
		 
	
	 String output = paymentObj.insertPayment(CreditID, UserID, SecurityNum, ExpireDate); 
	return output;
}
	//read
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readPayment(){

				return paymentObj.readPayment();
	
}
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updatePayment(String Paymentdata) 
			{ 
			//Convert the input string to a JSON object 
			 JsonObject paymentObject = new JsonParser().parse(Paymentdata).getAsJsonObject(); 
			//Read the values from the JSON object
			 String CreditID = paymentObject.get("CreditID").getAsString(); 
			 String UserID = paymentObject.get("UserID").getAsString(); 
			 String SecurityNum = paymentObject.get("SecurityNum").getAsString(); 
			 String ExpireDate = paymentObject.get("ExpireDate").getAsString(); 
			 
			 String output = paymentObj.updatePayment(CreditID,UserID,SecurityNum,ExpireDate); 
			return output;
		}
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deletePayment(String Paymentdata) 
			{ 
			//Convert the input string to an XML document
				JsonObject paymentObject = new JsonParser().parse(Paymentdata).getAsJsonObject();
				 
			//Read the value from the element <itemID>
			 String UserID = paymentObject.get("UserID").getAsString();
			
			 String output = paymentObj.deletePayment(UserID); 
			return output; 
			}

}