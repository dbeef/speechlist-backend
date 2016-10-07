package com.dbeef.speechlist.backend.hibernate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbeef.speechlist.backend.models.Notification;
import com.dbeef.speechlist.backend.models.Test;
import com.dbeef.speechlist.backend.models.TestNamesContainer;
import com.dbeef.speechlist.backend.models.UniqueIdContainer;
import com.google.gson.Gson;

public class HibernateConnector {

	static final String ABSOLUTE_PATH_TO_PROJECT = "/var/lib/tomcat7/webapps/UserManagement";

	public HibernateConnector() {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();
		if (tests.size() == 0) {
			File folder = new File(ABSOLUTE_PATH_TO_PROJECT + "/WEB-INF/jsons");
			File[] listOfFiles = folder.listFiles();

			String json = null;
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {

					try {
						json = readFile(folder.getAbsolutePath() + "/" + listOfFiles[i].getName(),
								Charset.defaultCharset());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (json != null) {
						Gson gson = new Gson();
						Test test = gson.fromJson(json, Test.class);
						if (test != null) {
							session.save(test);
						} else {
							test = new Test();
							session.save(test);
						}
					} else {
						Test test = new Test();
						session.save(test);
					}
				} else if (listOfFiles[i].isDirectory()) {
					// System.out.println("Directory " +
					// listOfFiles[i].getName());
				}
			}
			tx.commit();
		}
		session.close();
	}

	public List<Test> getTests() {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();

		tx.commit();
		session.close();

		return tests;
	}

	public Test getTest(int uniqueId) {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();

		for (Test a : tests) {
			if (a.getUniqueId() == uniqueId) {
				tx.commit();
				session.close();
				return a;
			}
		}

		tx.commit();
		session.close();

		return null;
	}

	public List<Notification> getNotifications() {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		// all your methods

		Notification notification = new Notification();
		notification.setType("warning");
		notification.setContent("old version");
		notification.setId(0);
		session.save(notification);

		List<Notification> notifications = session.createQuery("from Notification").list();

		tx.commit();
		session.close();

		return notifications;
	}

	public UniqueIdContainer getUniqueIds() {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();

		int[] uniqueIds = new int[tests.size()];

		for (int a = 0; a < tests.size(); a++) {
			uniqueIds[a] = tests.get(a).getUniqueId();
		}

		tx.commit();
		session.close();

		UniqueIdContainer container = new UniqueIdContainer();
		container.setUniqueIds(uniqueIds);
		return container;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public TestNamesContainer getTestNames(UniqueIdContainer uniqueIdContainer) {

		final Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();

		ArrayList<String> names = new ArrayList<String>();

		for (int a = 0; a < tests.size(); a++) {
			for (int b = 0; b < uniqueIdContainer.getUniqueIds().length; b++) {
				if (tests.get(a).getUniqueId() != uniqueIdContainer.getUniqueIds()[b])
					names.add(tests.get(a).getName());
			}
		}

		tx.commit();
		session.close();

		TestNamesContainer container = new TestNamesContainer();

		String[] namesInArray = new String[names.size()];

		for (int a = 0; a < names.size(); a++) {
			namesInArray[a] = names.get(a);
		}
		container.setNames(namesInArray);

		for (int a = 0; a < container.getNames().length; a++) {
			if (container.getNames()[a] == null) {
				container.getNames()[a] = "null";
			}
		}

		return container;

	}
}