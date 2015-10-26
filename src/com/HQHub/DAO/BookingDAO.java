package com.HQHub.DAO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HQHub.Util.Connection;
import com.HQHub.pojo.Booking;
import com.HQHub.pojo.ParkingSpace;
import com.HQHub.pojo.User;

public class BookingDAO {
	
	private Booking booking;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public static long getNextID() {
		Session session = Connection.getSession("mysql");
		String sql = "select b from Booking b";
		Query query = session.createQuery(sql);
		List<Long> list = query.list();
		long nextID = list.size() + 1;
		return nextID;
	}

	public static boolean Add(Booking book) {
		book.setId(getNextID());
		Session session = null;
		Transaction tx = null;
		try {
			session = Connection.getSession("mysql");
			tx = session.beginTransaction();
			session.save(book);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
//			session.close();
//			Connection.closeConnection();
		}

	}
	
	public static List<ParkingSpace> getAvailableSpace(String start, int duration){
		Session session = Connection.getSession("mysql");
		String hql = "select b from Booking b"
				+ " where (:start <= b.booked_end_time"
				+ " and :end >= b.booked_start_time)";
		Query query = session.createQuery(hql);
		query.setTimestamp("start", toTimestamp(start));
		query.setTimestamp("end", getEndTime(toTimestamp(start), duration));
		List <Booking> bList = query.list();	
		
		String hql2 = "select p from ParkingSpace p";
		List <ParkingSpace> pList = session.createQuery(hql2).list();
		for(int j = 0; j<bList.size();j++){
		    for(int i =0;i<pList.size();i++){		
				if(pList.get(i).getId() == bList.get(j).getId_p()){
					pList.remove(i);
				}
			}
		}
		return pList;
		
	}
	
	public static Timestamp getEndTime(Timestamp start, int duraiton){

		  Calendar cc = Calendar.getInstance();
		  cc.setTime(start);
		  cc.add(Calendar.HOUR_OF_DAY, duraiton);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateStr = sdf.format(cc.getTime());	
		return toTimestamp(dateStr);
		
	}
	
	public static Timestamp toTimestamp(String time){
		Timestamp start = Timestamp.valueOf(time); 		
		return start;
		
	}

	
}
