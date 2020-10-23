package probeaufgabe.model.beans;

import java.time.LocalDate;

import probeaufgabe.model.entities.User;

public class UserResponse {
	private int user_number;

	private String first_name;

	private String last_name;

	private LocalDate birth_date;

	private String gender;

	public UserResponse() {
	}

	public UserResponse(int user_number, String first_name, String last_name, LocalDate birth_date, String gender) {
		this.user_number = user_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.gender = gender;
	}

	public UserResponse(User user) {
		this.user_number = user.getUser_number();
		this.first_name = user.getFirst_name();
		this.last_name = user.getLast_name();
		this.birth_date = user.getBirth_date();
		this.gender = user.getGender().getGender();
	}

	public int getUser_number() {
		return user_number;
	}

	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
		this.birth_date = birth_date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
