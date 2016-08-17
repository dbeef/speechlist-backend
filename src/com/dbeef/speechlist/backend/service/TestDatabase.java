package com.dbeef.speechlist.backend.service;

import java.util.List;

import com.dbeef.speechlist.backend.models.UniqueIdContainer;
import com.dbeef.speechlist.backend.hibernate.HibernateConnector;
import com.dbeef.speechlist.backend.models.*;

public class TestDatabase {

	public List<Test> getTests() {
		HibernateConnector hibernateConnector = new HibernateConnector();
		return hibernateConnector.getTests();
	}

	public List<Notification> getNotifications() {
		HibernateConnector hibernateConnector = new HibernateConnector();
		return hibernateConnector.getNotifications();
	}

	public Test getTest(int id) {
		HibernateConnector hibernateConnector = new HibernateConnector();
		return hibernateConnector.getTest(id);
	}

	public UniqueIdContainer getUniqueIds() {
		HibernateConnector hibernateConnector = new HibernateConnector();
		return hibernateConnector.getUniqueIds();
	}
}