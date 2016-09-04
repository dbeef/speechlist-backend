package com.dbeef.speechlist.backend.service;

import java.util.List;

import com.dbeef.speechlist.backend.models.UniqueIdContainer;
import com.dbeef.speechlist.backend.hibernate.HibernateConnector;
import com.dbeef.speechlist.backend.models.*;

public class TestDatabase {

	HibernateConnector hibernateConnector = new HibernateConnector();
	
	public List<Test> getTests() {
		return hibernateConnector.getTests();
	}

	public List<Notification> getNotifications() {
		return hibernateConnector.getNotifications();
	}

	public Test getTest(int id) {
		return hibernateConnector.getTest(id);
	}

	public UniqueIdContainer getUniqueIds() {
		return hibernateConnector.getUniqueIds();
	}
}