package probeaufgabe.model.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CheckAccountInput {
	
	@NotNull
	@Size(max = 255)
	private String account_name;
	
	@NotNull
	private double overdraft_limit;

	private String modif_user;
	
	public CheckAccountInput() {
	}

	public CheckAccountInput(@NotNull @Size(max = 255) String account_name, @NotNull double overdraft_limit,
			String modif_user) {
		super();
		this.account_name = account_name;
		this.overdraft_limit = overdraft_limit;
		this.modif_user = modif_user;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
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
}
