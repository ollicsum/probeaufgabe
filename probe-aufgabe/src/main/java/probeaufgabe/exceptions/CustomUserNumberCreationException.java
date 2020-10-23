package probeaufgabe.exceptions;

public class CustomUserNumberCreationException extends RuntimeException {
	public CustomUserNumberCreationException() {
		super("User creation with customer number not allowed.");
	}
}