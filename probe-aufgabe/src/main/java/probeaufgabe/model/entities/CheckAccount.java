package probeaufgabe.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import probeaufgabe.config.Constants;
import probeaufgabe.model.beans.CheckAccountInput;

@Entity
@ApiModel(description = "Checking account table")
@Table(name = "CHECKACCOUNT")
public class CheckAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Checking account number")
	@Column(name = "CANUMBER", insertable = false)
	@NotNull(message = "CANUMBER is mandatory")
	private int account_number;

	@ApiModelProperty(notes = "Checking account name")
	@Column(name = "CANAME")
	@NotEmpty(message = "CANAME is mandatory")
	@Size(max = 255)
	private String account_name;
	
	@ApiModelProperty(notes = "Checking account credit amount")
	@Column(name = "CACREDIT")
	@NotNull(message = "CACREDIT is mandatory")
	private double credit_amount;
	
	@ApiModelProperty(notes = "Checking account PIN number")
	@Column(name = "CAPIN")
	@NotNull(message = "CAPIN is mandatory")
	@Size(min = Constants.PIN_CODE_MIN_LENGTH, max = Constants.PIN_CODE_MAX_LENGTH)
	private String pin_code;
	
	@ApiModelProperty(notes = "Checking account overdraft limit amount")
	@Column(name = "CAOVERDRAFTLIM")
	private double overdraft_limit;
	
	@ApiModelProperty(notes = "Checking account modification username")
	@Column(name = "CAMODIFUSER")
	@Size(max = 255)
	private String modif_user;

	public CheckAccount() {
		super();
	}

	public CheckAccount(CheckAccountInput accountInput, @NotNull(message = "CAPIN is mandatory") @Size(min = 4, max = 6) String pin_code) {
		super();
		this.account_name = accountInput.getAccount_name();
		this.overdraft_limit = accountInput.getOverdraft_limit();
		this.credit_amount = Constants.ACCOUNT_DEFAULT_CREDIT;
		this.pin_code = pin_code;
		this.modif_user = accountInput.getModif_user();
	}

	public CheckAccount(@NotNull(message = "CANUMBER is mandatory") int account_number,
			@NotEmpty(message = "CANAME is mandatory") @Size(max = 255) String account_name,
			@NotNull(message = "CACREDIT is mandatory") double credit_amount,
			@NotNull(message = "CAPIN is mandatory") @Size(min = Constants.PIN_CODE_MIN_LENGTH, max = Constants.PIN_CODE_MAX_LENGTH) String pin_code, double overdraft_limit,
			@Size(max = 255) String modif_user) {
		super();
		this.account_number = account_number;
		this.account_name = account_name;
		this.credit_amount = credit_amount;
		this.pin_code = pin_code;
		this.overdraft_limit = overdraft_limit;
		this.modif_user = modif_user;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public double getCredit_amount() {
		return credit_amount;
	}

	public void setCredit_amount(double credit_amount) {
		this.credit_amount = credit_amount;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public double getOverdraft_limit() {
		return overdraft_limit;
	}

	public void setOverdraft_limit(double overdraft_limit) {
		this.overdraft_limit = overdraft_limit;
	}

	public String getModif_user() {
		return modif_user;
	}

	public void setModif_user(String modif_user) {
		this.modif_user = modif_user;
	}

	@Override
	public String toString() {
		return "CheckAccount [account_number=" + account_number + ", account_name=" + account_name + ", credit_amount="
				+ credit_amount + ", pin_code=" + pin_code + ", overdraft_limit=" + overdraft_limit + ", modif_user="
				+ modif_user + "]";
	}
}
