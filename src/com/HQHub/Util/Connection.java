package com.HQHub.Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Connection {

	public static SessionFactory sessionfactory;
	public static Configuration mysql;
	public static Configuration oracle;
	static {
		try {
					
             mysql = new Configuration().configure("/mysql.cfg.xml");
             oracle = new Configuration().configure("/oracle.cfg.xml");   
             
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static Session getSession(String DBName) {
		try {
			
			if(DBName=="mysql"){
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(mysql.getProperties()).build();
	            sessionfactory = mysql.buildSessionFactory(serviceRegistry);
				Session session = sessionfactory.openSession();
				return session;
			}else{
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(oracle.getProperties()).build();
	            sessionfactory = oracle.buildSessionFactory(serviceRegistry);
				Session session = sessionfactory.openSession();
				return session;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeConnection() {
		sessionfactory.close();
	}
}
