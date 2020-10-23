package probeaufgabe.service;

import java.util.List;

import probeaufgabe.model.beans.CheckAccountInput;
import probeaufgabe.model.beans.UserInput;
import probeaufgabe.model.beans.UserResponse;
import probeaufgabe.model.entities.CheckAccount;
import probeaufgabe.model.entities.User;

public interface ProbeaufgabeService {
	/*
	 * Users management
	 */
	public abstract List<UserResponse> getAllUsers();
	public abstract UserResponse getUser(int user_number);
	public abstract void putUser(UserInput user, int user_number);
	public abstract User createUser(UserInput user);
	public abstract void updateUser(UserInput user);
	public abstract String getAvailableGenderKeys();
	
	/*
	 * Accounts management
	 */
	public abstract List<CheckAccount> getAllCheckAccounts();
	public abstract CheckAccount getCheckAccount(int account_number);
	public abstract CheckAccount createCheckAccount(CheckAccountInput checkAccount);
}
