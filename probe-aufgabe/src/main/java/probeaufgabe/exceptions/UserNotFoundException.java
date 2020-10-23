package probeaufgabe.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(int user_number) {
		super( String.valueOf(user_number));
	}
}