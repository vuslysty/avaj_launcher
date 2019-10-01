package Exceptions;

public class CiclesException extends AvajException {

	public CiclesException(int lineNum, String mess) {
		super(lineNum);
		message += "Cicles number exception (" + mess + ")";
	}
}