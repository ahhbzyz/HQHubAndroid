package com.HQHub.pojo;


import java.sql.Timestamp;

public class Booking {
	
	private long id;
    private long id_p;
	private String phone;
	private Timestamp booked_start_time;
	private Timestamp booked_end_time;
	private int booked_duration;

	

	

	public Booking() {
		super();
	}





	public Booking(long id_p, String phone,
			Timestamp booked_start_time, Timestamp booked_end_time,
			int booked_duration) {
		super();

		this.id_p = id_p;
		this.phone = phone;
		this.booked_start_time = booked_start_time;
		this.booked_end_time = booked_end_time;
		this.booked_duration = booked_duration;
	}


	public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public long getId_p() {
		return id_p;
	}





	public void setId_p(long id_p) {
		this.id_p = id_p;
	}





	public String getPhone() {
		return phone;
	}





	public void setPhone(String phone) {
		this.phone = phone;
	}





	public Timestamp getBooked_start_time() {
		return booked_start_time;
	}





	public void setBooked_start_time(Timestamp booked_start_time) {
		this.booked_start_time = booked_start_time;
	}





	public Timestamp getBooked_end_time() {
		return booked_end_time;
	}





	public void setBooked_end_time(Timestamp booked_end_time) {
		this.booked_end_time = booked_end_time;
	}





	public int getBooked_duration() {
		return booked_duration;
	}





	public void setBooked_duration(int booked_duration) {
		this.booked_duration = booked_duration;
	}










	

	
	

}
