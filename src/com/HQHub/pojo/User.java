package com.HQHub.pojo;

public class User {
	
	private long id;
	private String username;
	private String password;
	private String phone;
	private String create_date;


	
	public User() {
		super();
	}


	public User(String username, String password, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.phone = phone;
	}




	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCreate_date() {
		return create_date;
	}


	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	
	

}
