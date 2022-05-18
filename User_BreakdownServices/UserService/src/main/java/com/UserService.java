package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;


import model.User;

@Path("/Users")
public class UserService {

	User userObj = new User();

	//Get Users

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userObj.readUsers();
	}

	//Get User By ID

	@GET
	@Path("/getUserbyID")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UserProfileDetails(String userIdData) {
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userIdData).getAsJsonObject();
		//Read the values from the JSON object
		String userId = userObject.get("userID").getAsString();
		return userObj.readUserByID(userId);
	}

	//Create user account

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(String userData) {
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		String username = userObject.get("username").getAsString();
		String password = userObject.get("password").getAsString();
		String accountNumber = userObject.get("accountNumber").getAsString();
		String address = userObject.get("address").getAsString();
		String NIC = userObject.get("NIC").getAsString();
		String phone = userObject.get("phone").getAsString();
		String reset_code = userObject.get("reset_code").getAsString();
		String userRole = userObject.get("userRole").getAsString();
		String output = userObj.insertUser(username, password, accountNumber, address, NIC, phone, reset_code, userRole);
		return output;
	}

	// Update user account

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData) {
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		String userID = userObject.get("userID").getAsString();
		String username = userObject.get("username").getAsString();
		String password = userObject.get("password").getAsString();
		String accountNumber = userObject.get("accountNumber").getAsString();
		String address = userObject.get("address").getAsString();
		String NIC = userObject.get("NIC").getAsString();
		String phone = userObject.get("phone").getAsString();
		String userRole = userObject.get("userRole").getAsString();
		String output = userObj.updateUser(userID, username, password, accountNumber, address, NIC, phone, userRole);
		return output;
	}

	//Delete user account

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		//Convert the input string to a JSON object 
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		String userID = userObject.get("userID").getAsString();
		String output = userObj.deleteUser(userID);
		return output;
	}
}
