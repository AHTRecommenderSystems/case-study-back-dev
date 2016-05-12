package com.aht.bonappettit.webservices.node;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import java.util.LinkedList;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aht.bonappettit.domain.node.User;
import com.aht.bonappettit.serviceimpl.node.UserServiceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.ogm.session.result.ResultProcessingException;

@Component
@Path("/userws")
public class UserWS {
	@Autowired UserServiceImpl userService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@FormParam("name") String name, 
			@FormParam("email") String email, 
			@FormParam("since") String since, 
			@FormParam("gender") String gender, 
			@FormParam("lastname") String lastname, 
			@FormParam("password") String password, 
			@FormParam("nationality") String nationality, 
			@FormParam("birthdate") String birthdate) {
		User user = new User();
		JSONObject response = new JSONObject();
		
		try {
			if(name != null)
				user.setName(name);
			if(email != null)
				user.setEmail(email);
			if(since != null)
				user.setSince(since);
			if(gender != null)
				user.setGender(gender);
			if(lastname != null)
				user.setLastname(lastname);
			if(password != null)
				user.setPassword(password);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			user = userService.create(user);
			if(user.getId() != null) {
				response.put("success", true);
				response.put("id", user.getId());
			}
		} catch (ResultProcessingException exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieve(@FormParam("id") String id) {
		User user = new User();
		JSONObject response = new JSONObject();
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(user.getName() != null)
				response.put("name", user.getName());
			if(user.getLastname() != null)
				response.put("lastname", user.getLastname());
			if(user.getPassword() != null)
				response.put("password", user.getPassword());
			if(user.getGender() != null)
				response.put("gender", user.getGender());
			if(user.getNationality() != null)
				response.put("nationality", user.getNationality());
			if(user.getBirthdate() != null)
				response.put("birthdate", user.getBirthdate());
			if(user.getEmail() != null)
				response.put("email", user.getEmail());
			if(user.getSince() != null)
				response.put("since", user.getSince());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		} 
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(
			@FormParam("id") String id, 
			@FormParam("name") String name, 
			@FormParam("email") String email, 
			@FormParam("since") String since, 
			@FormParam("gender") String gender, 
			@FormParam("lastname") String lastname, 
			@FormParam("password") String password, 
			@FormParam("nationality") String nationality, 
			@FormParam("birthdate") String birthdate) {
		User user = new User();
		JSONObject response = new JSONObject();
		
		try {
			user = userService.retrieve(Long.parseLong(id));
			if(name != null)
				user.setName(name);
			if(email != null)
				user.setEmail(email);
			if(since != null)
				user.setSince(since);
			if(gender != null)
				user.setGender(gender);
			if(lastname != null)
				user.setLastname(lastname);
			if(password != null)
				user.setPassword(password);
			if(nationality != null)
				user.setNationality(nationality);
			if(birthdate != null)
				user.setBirthdate(birthdate);
			userService.update(user);
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@FormParam("id") String id) {
		User user = new User();
		JSONObject response = new JSONObject();
		try {
			user = userService.retrieve(Long.parseLong(id));
			userService.delete(user);
			response.put("success", true);
		} catch(Exception exception) {
			exception.printStackTrace();
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/retrieveAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAll() {
		JSONObject response = new JSONObject();
		JSONArray users = new JSONArray();
		try {
			LinkedList<User> userNodes = userService.retrieveAll();
			users.put(userNodes);
			response.put("users", users);
			response.put("success", true);
		} catch (Exception exception) {
			response.put("success", false);
		}
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {
		JSONObject response = new JSONObject();
		try {
			User user = userService.login(email, password);
			response.put("id", user.getId());
			response.put("name", user.getName());
			response.put("lastname", user.getLastname());
			response.put("success", true);
		} catch(Exception exception) {
			response.put("success", false);
		}
		
		userService.login(email, password);
		return Response.status(200).entity(response.toString()).header("Access-Control-Allow-Origin", "http://localhost:3000").header("Access-Control-Allow-Methods", "POST").build();
	}
	
}