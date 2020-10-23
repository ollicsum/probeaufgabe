package probeaufgabe.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Users possible genders")
@Table(name = "USERGENDER")
public class UserGender implements Serializable {

	@Id
	//@OneToMany
	@Column(name = "UGID", insertable = false)
	@ApiModelProperty(notes = "Gender ID")
	private int gender_id;
	
    //Set subMenu = new HashSet();

	@ApiModelProperty(notes = "Gender label")
	@Column(name = "UGLABEL")
	@NotEmpty(message = "UGLABEL is mandatory")
	@Size(max = 255)
	private String gender;
	
	@ApiModelProperty(notes = "Gender inserting key")
	@Column(name = "UGINSERTKEY")
	//@NotEmpty(message = "UGLABEL is mandatory")
	@Size(max = 1)
	private char insertkey;
	
	@ApiModelProperty(notes = "Gender's modification username")
	//@NotNull(message = "UGMODIFUSER is mandatory")
	@Column(name = "UGMODIFUSER")
	private String modif_user;
	
	public UserGender() {
	}

	public UserGender(int gender_id, @NotEmpty(message = "UGLABEL is mandatory") @Size(max = 255) String gender, @Size(max = 1) char insert_key) {
		super();
		this.gender_id = gender_id;
		this.gender = gender;
		this.insertkey = insert_key;
	}

	public UserGender(int gender_id, @NotEmpty(message = "UGLABEL is mandatory") @Size(max = 255) String gender, @Size(max = 1) char insert_key,
			String modif_user) {
		super();
		this.gender_id = gender_id;
		this.gender = gender;
		this.insertkey = insert_key;
		this.modif_user = modif_user;
	}

	public int getGender_id() {
		return gender_id;
	}

	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public char getInsert_key() {
		return insertkey;
	}

	public void setInsert_key(char insert_key) {
		this.insertkey = insert_key;
	}

	public String getModif_user() {
		return modif_user;
	}

	public void setModif_user(String modif_user) {
		this.modif_user = modif_user;
	}
}
