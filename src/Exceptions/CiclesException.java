package Exceptions;

public class CiclesException extends AvajException {

	public CiclesException(int lineNum) {
		super(lineNum);
		message += "Bad cicles number";
	}
}