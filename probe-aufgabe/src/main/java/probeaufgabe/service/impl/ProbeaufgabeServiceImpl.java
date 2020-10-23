package probeaufgabe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import probeaufgabe.config.Constants;
import probeaufgabe.exceptions.CheckAccountNotFoundException;
import probeaufgabe.exceptions.CustomUserNumberCreationException;
import probeaufgabe.exceptions.DifferentUriAndBodyIdsException;
import probeaufgabe.exceptions.GenderKeyNotFoundException;
import probeaufgabe.exceptions.UserNotFoundException;
import probeaufgabe.model.beans.CheckAccountInput;
import probeaufgabe.model.beans.UserInput;
import probeaufgabe.model.beans.UserResponse;
import probeaufgabe.model.dao.CheckAccountsRepository;
import probeaufgabe.model.dao.UserGenderRepository;
import probeaufgabe.model.dao.UsersRepository;
import probeaufgabe.model.entities.CheckAccount;
import probeaufgabe.model.entities.UserGender;
import probeaufgabe.model.entities.User;
import probeaufgabe.service.ProbeaufgabeService;

@Service
@Transactional
public class ProbeaufgabeServiceImpl implements ProbeaufgabeService {

	@Autowired
	UsersRepository userRepository;
	@Autowired
	UserGenderRepository userGenderRepository;
	@Autowired
	CheckAccountsRepository checkAccountRepository;
	
	/*
	 * Users management
	 */
	
	@Override
	public List<UserResponse> getAllUsers() {
		List<UserResponse> usersResponse = new ArrayList<UserResponse>();
		
		for (User user : userRepository.findAll()) {
			usersResponse.add(new UserResponse(user));
		}
		
		return usersResponse;
	}
	
	@Override
	public UserResponse getUser(int user_number) {
		User user = userRepository.findById(user_number);
		
		if (user == null) {
			throw new UserNotFoundException(user_number);
		}
		
		return new UserResponse(user);
	}
	
	@Override
	public void putUser(UserInput userInput, int user_number) throws GenderKeyNotFoundException, 
			DifferentUriAndBodyIdsException, CustomUserNumberCreationException {
		if (userInput.getUser_number() != 0 && userInput.getUser_number() != user_number) {
    		throw new DifferentUriAndBodyIdsException();
    		
    	} else if (!userRepository.existsById(user_number)) {
    		throw new CustomUserNumberCreationException();
    		
        } else {
        	userInput.setUser_number(user_number); //in case user number is missing in body object
        	updateUser(userInput);
        }
	}
	
	@Override
	public User createUser(UserInput userInput) throws GenderKeyNotFoundException, CustomUserNumberCreationException {
		if (userInput.getUser_number() != 0) {
			throw new CustomUserNumberCreationException();
		}
		
		UserGender gender = getGenderFromKey(userInput.getGender());
		return userRepository.save(new User(userInput, gender));
	}
	
	@Override
	public void updateUser(UserInput userInput) throws GenderKeyNotFoundException {
		UserGender gender = getGenderFromKey(userInput.getGender());
		User user = userRepository.findById(userInput.getUser_number());
		
		user.setBirth_date(userInput.getBirth_date());
		user.setFirst_name(userInput.getFirst_name());
		user.setLast_name(userInput.getLast_name());
		user.setGender(gender);
		user.setModif_user(userInput.getModif_user());
		
		userRepository.save(user);
	}
	
	@Override
	public String getAvailableGenderKeys() {
		String availableGenderKeys = "";
		
		for (UserGender userGender : userGenderRepository.findAll()) {
			availableGenderKeys += "'" + userGender.getInsert_key() + "' for " + userGender.getGender() + ", ";
		}
		
		if (availableGenderKeys.length() >= 2) {
			availableGenderKeys = availableGenderKeys.substring(0, availableGenderKeys.length() - 2);
		}
		
		return availableGenderKeys;
	}
	
	private UserGender getGenderFromKey(char genderKey) {
		UserGender gender = userGenderRepository.findByInsertkey(genderKey);
		
		if (gender == null) {
			throw new GenderKeyNotFoundException(genderKey);
		}
		
		return gender;
	}
	
	
	/*
	 * Checking accounts management
	 */
	
	
	@Override
	public List<CheckAccount> getAllCheckAccounts() {
		return checkAccountRepository.findAll();
	}
	 
	@Override
	public CheckAccount getCheckAccount(int account_number) {
		CheckAccount account = checkAccountRepository.findById(account_number);
			
		if (account == null) {
			throw new CheckAccountNotFoundException(account_number);
		}
		
		return account;
	}
	
	@Override
	public CheckAccount createCheckAccount(CheckAccountInput checkAccount) {
		CheckAccount accountToCreate = new CheckAccount(checkAccount, randomizePinCode());

		return checkAccountRepository.save(accountToCreate);
	}
	
	private String randomizePinCode() {
		String pinCode = "";
		Random r = new Random();
		int low = 0;
		int high = 10;
		int random;
		
		for (int i = 0; i < Constants.PIN_CODE_DEFAULT_LENGTH; i++) {
			random = r.nextInt(high-low) + low;
			pinCode += String.valueOf(random);
		}
		
		return pinCode;
	}
}
