package probeaufgabe.exceptions;

public class CheckAccountNotFoundException extends RuntimeException {
	public CheckAccountNotFoundException(int account_number) {
		super( String.valueOf(account_number));
	}
}