package com.dbeef.speechlist.backend.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

	@GET
	@Path("/tests/{testid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@PathParam("testid") int testid) {
		Gson gson = new Gson();
		String jsonInString = gson.toJson(testDatabase.getTest(testid));
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
	@Path("/tests/{testid}/name")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTestName(@PathParam("testid") int testid) {
		return testDatabase.getTest(testid).getName();
	}

}
