package probeaufgabe.exceptions;

public class GenderKeyNotFoundException extends RuntimeException {
	public GenderKeyNotFoundException(char key) {
		super(String.valueOf(key));
	}
}