package probeaufgabe.model.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import probeaufgabe.model.identity.UserCheckAccountIdentity;

@Entity
@ApiModel(description = "User checking account table")
@Table(name = "USERCHECKACC")
public class UserCheckAccount {

	@EmbeddedId
	@ApiModelProperty(notes = "Checking account / user mapping")
	private UserCheckAccountIdentity ucaIdentity;
	
	@ApiModelProperty(notes = "Modification username of checking account / user mapping")
	@Column(name = "UCAMODIFUSER")
	@Size(max = 255)
	private String account_modif_user;

	public UserCheckAccount() {
		super();
	}

	public UserCheckAccount(UserCheckAccountIdentity ucaIdentity, @Size(max = 255) String account_modif_user) {
		super();
		this.ucaIdentity = ucaIdentity;
		this.account_modif_user = account_modif_user;
	}

	public UserCheckAccountIdentity getUcaIdentity() {
		return ucaIdentity;
	}

	public void setUcaIdentity(UserCheckAccountIdentity ucaIdentity) {
		this.ucaIdentity = ucaIdentity;
	}

	public String getAccount_modif_user() {
		return account_modif_user;
	}

	public void setAccount_modif_user(String account_modif_user) {
		this.account_modif_user = account_modif_user;
	}
}
