package probeaufgabe.model.identity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import probeaufgabe.model.entities.CheckAccount;
import probeaufgabe.model.entities.User;

@Embeddable
@Table(name = "USERCHECKACC")
public class UserCheckAccountIdentity implements Serializable {
	@NotNull
	@ManyToOne
	@JoinColumn(name = "UCAUSERNR")
	private User user_number;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "UCACHECKACCOUNT")
	private CheckAccount account_number;

	public UserCheckAccountIdentity() {
		super();
	}

	public UserCheckAccountIdentity(@NotNull User user_number, @NotNull CheckAccount account_number) {
		super();
		this.user_number = user_number;
		this.account_number = account_number;
	}

	public User getUser_number() {
		return user_number;
	}

	public void setUser_number(User user_number) {
		this.user_number = user_number;
	}

	public CheckAccount getAccount_number() {
		return account_number;
	}

	public void setAccount_number(CheckAccount account_number) {
		this.account_number = account_number;
	}
}
