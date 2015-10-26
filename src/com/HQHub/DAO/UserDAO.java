package com.HQHub.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HQHub.Util.Connection;
import com.HQHub.pojo.User;

public class UserDAO {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static boolean Add(User user) {
		user.setId(getNextID());
		Session session = null;
		Transaction tx = null;
		try {
			session = Connection.getSession("mysql");
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("×¢²áÊ§°Ü");
			e.printStackTrace();
			return false;
		} finally {
//			session.close();
//			Connection.closeConnection();
		}

	}
	
	public static boolean registerValidate(String phone) {
		boolean conflict_flag;
		Session session = Connection.getSession("mysql");
		String hql = "select u.phone from User as u where u.phone=?";
		Query query = session.createQuery(hql);
		query.setString(0, phone);
		List <User> list = query.list();
		if (list.size() > 0) {
			conflict_flag = true;
			return conflict_flag;
		} else {
			conflict_flag = false;
			return conflict_flag;
		}
	}


	
	public static List <User> select(User user) {
		String sql = "select u from User as u ";
		Session session = Connection.getSession("mysql");
		Query query = session.createQuery(sql);
		List <User> list = query.list();
		return list;
	}

	

	public static User loginValidate(String phone, String pswd) {
		Session session = Connection.getSession("mysql");
		String hql = "select u from User as u where u.phone= ? and u.password= ?";
		Query query = session.createQuery(hql);
		query.setString(0, phone);
		query.setString(1, pswd);
		List <User> list = query.list();
		User user = null;
		if (list.size() > 0){
			user = list.get(0);		
		}
	    		
        return user;

	}
	

	public static long getNextID() {
		Session session = Connection.getSession("mysql");
		String sql = "select max(id) from User";
		Query query = session.createQuery(sql);
		List<Long> list = query.list();
		long nextID = list.get(0) + 1;
		return nextID;
	}
}