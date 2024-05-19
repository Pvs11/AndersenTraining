package exceptions;

public class FlipCardNotFoundException extends RuntimeException {
	public FlipCardNotFoundException(String msg) {
		super(msg);
	}
}
