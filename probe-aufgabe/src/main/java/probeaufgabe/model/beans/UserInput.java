package probeaufgabe.model.beans;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class UserInput {
	private int user_number;
	
	@NotNull
	private String first_name;
	
	@NotNull
	private String last_name;
	
	@NotNull
	private LocalDate birth_date;
	
	@NotNull
	private char gender;

	private String modif_user;
	
	public UserInput() {
	}
	
	//constructor for unit tests
	public UserInput(@NotNull String first_name, @NotNull String last_name,
			@NotNull LocalDate birth_date, @NotNull char gender, String modif_user) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.gender = gender;
		this.modif_user = modif_user;
	}

	public UserInput(int user_number, @NotNull String first_name, @NotNull String last_name,
			@NotNull LocalDate birth_date, @NotNull char gender, String modif_user) {
		super();
		this.user_number = user_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.gender = gender;
		this.modif_user = modif_user;
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getModif_user() {
		return modif_user;
	}

	public void setModif_user(String modifuser) {
		this.modif_user = modifuser;
	}
}
