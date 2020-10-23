package probeaufgabe.exceptions;

public class DifferentUriAndBodyIdsException extends RuntimeException {
	public DifferentUriAndBodyIdsException() {
		super("URI and body IDs don't match");
	}
}