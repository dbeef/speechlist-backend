package com.dbeef.speechlist.backend.hibernate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbeef.speechlist.backend.models.Notification;
import com.dbeef.speechlist.backend.models.Test;
import com.dbeef.speechlist.backend.models.UniqueIdContainer;
import com.google.gson.Gson;

public class HibernateConnector {

	public HibernateConnector() {

		Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();
		if (tests.size() == 0) {
			File folder = new File("/var/lib/tomcat8/webapps/UserManagement/WEB-INF/jsons");
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

		Session session = HibernateUtil.getHibernateSession();
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

	public Test getTest(int id) {

		Session session = HibernateUtil.getHibernateSession();
		session.beginTransaction();

		Transaction tx = null;

		tx = session.getTransaction();

		if (tx.isActive() == false)
			tx = session.beginTransaction();

		List<Test> tests = session.createQuery("from Test").list();

		for (Test a : tests) {
			if (a.getUniqueId() == id)
				return a;
		}
		session.close();

		return null;
	}

	public List<Notification> getNotifications() {

		Session session = HibernateUtil.getHibernateSession();
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

		Session session = HibernateUtil.getHibernateSession();
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

		session.close();

		UniqueIdContainer container = new UniqueIdContainer();
		container.setUniqueIds(uniqueIds);
		return container;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}