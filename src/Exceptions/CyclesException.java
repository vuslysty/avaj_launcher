package Exceptions;

public class CyclesException extends AvajException {

	public CyclesException(int lineNum, String mess) {
		super(lineNum);
		message += "Cicles number exception (" + mess + ")";
	}
}