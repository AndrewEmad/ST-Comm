package Entities;

import java.util.Date;

public class Registrant {

	private String name;
	private Date birthdate;
	private String gender;
	private String mail;
	private String country;
	private String password;
	private boolean confirmed;
	
	
	public void setInfo(String name, Date birthdate, String gender, String mail, String country, String password,
			boolean confirmed) {
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.mail = mail;
		this.country = country;
		this.password = password;
		this.confirmed = confirmed;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	


}