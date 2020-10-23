package probeaufgabe.model.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import probeaufgabe.model.beans.UserInput;

@Entity
@ApiModel(description = "Users table")
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USRNUMBER", insertable = false)
	@ApiModelProperty(notes = "User number")
	private int user_number;

	@ApiModelProperty(notes = "User first name")
	@Column(name = "USRFIRSTNAME")
	@NotEmpty(message = "USRFIRSTNAME is mandatory")
	@Size(max = 255)
	private String first_name;
	
	@ApiModelProperty(notes = "User last name")
	@Column(name = "USRLASTNAME")
	@NotEmpty(message = "USRLASTNAME is mandatory")
	@Size(max = 255)
	private String last_name;

	@ApiModelProperty(notes = "User birth date")
	@Column(name = "USRBIRTHDATE")
	@NotNull(message = "USRBIRTHDATE is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Europe/Berlin")
	private LocalDate birth_date;

	@ApiModelProperty(notes = "User gender")
	@NotNull(message = "USRGENDER is mandatory")
	@ManyToOne
	@JoinColumn(name = "USRGENDER")
	private UserGender gender;
	
	@ApiModelProperty(notes = "User's modification username")
	//@NotNull(message = "USRMODIFUSER is mandatory")
	@Column(name = "USRMODIFUSER")
	private String modif_user;
	
	public User() {
	}
	
	public User(UserInput user, UserGender gender) {
		this.first_name = user.getFirst_name();
		this.last_name = user.getLast_name();
		this.birth_date = user.getBirth_date();
		this.gender = gender;
		this.modif_user = user.getModif_user();
	}
	
	public User(int user_number, @NotEmpty(message = "USRFIRSTNAME is mandatory") @Size(max = 255) String first_name,
			@NotEmpty(message = "USRLASTNAME is mandatory") @Size(max = 255) String last_name,
			@NotNull(message = "USRBIRTHDATE is mandatory") LocalDate birth_date,
			@NotNull(message = "USRGENDER is mandatory") UserGender gender) {
		super();
		this.user_number = user_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.gender = gender;
	}

	public User(int user_number, @NotEmpty(message = "USRFIRSTNAME is mandatory") @Size(max = 255) String first_name,
			@NotEmpty(message = "USRLASTNAME is mandatory") @Size(max = 255) String last_name,
			@NotNull(message = "USRBIRTHDATE is mandatory") LocalDate birth_date,
			@NotNull(message = "USRGENDER is mandatory") UserGender gender, String modif_user) {
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

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getModif_user() {
		return modif_user;
	}

	public void setModif_user(String modif_user) {
		this.modif_user = modif_user;
	}
}
