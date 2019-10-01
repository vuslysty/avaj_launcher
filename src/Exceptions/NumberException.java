package Exceptions;

public class NumberException extends AvajException {

	public NumberException(int lineNum) {
		super(lineNum);
		message += "Round of range";
	}
}
