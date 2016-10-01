package com.dbeef.speechlist.backend.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dbeef.speechlist.backend.models.UniqueIdContainer;
import com.google.gson.Gson;

@Path("/TestService")
public class TestService {

	TestDatabase testDatabase = new TestDatabase();

	@GET
	@Path("/tests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTests() {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(testDatabase.getTests());

		return jsonInString;
	}

	@GET
	@Path("/notifications")
	@Produces(MediaType.APPLICATION_JSON)
	public String getNotifications() {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(testDatabase.getNotifications());
		return jsonInString;
	}

	@POST
	@Path("/tests/testNamesContainer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDataInJSON(String data) {

		Gson gson = new Gson();
		UniqueIdContainer uniqueIdContainer = gson.fromJson(data, UniqueIdContainer.class);

		return Response.status(201).entity(gson.toJson(testDatabase.getTestNames(uniqueIdContainer))).build();
	}

	@GET
	@Path("/tests/{uniqueid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@PathParam("uniqueid") int uniqueid) {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(testDatabase.getTest(uniqueid));
		return jsonInString;
	}

	@GET
	@Path("/tests/uniqueids")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUniqueIds() {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(testDatabase.getUniqueIds());
		return jsonInString;
	}

	@GET
	@Path("/tests/{uniqueid}/name")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTestName(@PathParam("uniqueid") int uniqueid) {
		return testDatabase.getTest(uniqueid).getName();
	}
}