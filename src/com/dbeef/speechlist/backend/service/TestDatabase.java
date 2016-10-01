package com.dbeef.speechlist.backend.service;

import java.util.List;

import com.dbeef.speechlist.backend.hibernate.HibernateConnector;
import com.dbeef.speechlist.backend.models.Notification;
import com.dbeef.speechlist.backend.models.Test;
import com.dbeef.speechlist.backend.models.TestNamesContainer;
import com.dbeef.speechlist.backend.models.UniqueIdContainer;

public class TestDatabase {

	HibernateConnector hibernateConnector = new HibernateConnector();

	public List<Test> getTests() {
		return hibernateConnector.getTests();
	}

	public List<Notification> getNotifications() {
		return hibernateConnector.getNotifications();
	}

	public Test getTest(int uniqueId) {
		return hibernateConnector.getTest(uniqueId);
	}

	public UniqueIdContainer getUniqueIds() {
		return hibernateConnector.getUniqueIds();
	}

	public TestNamesContainer getTestNames(UniqueIdContainer container) {
		return hibernateConnector.getTestNames(container);
	}
}