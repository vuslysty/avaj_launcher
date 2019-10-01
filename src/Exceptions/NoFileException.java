package Exceptions;

public class NoFileException extends AvajException {
	public NoFileException() {
		super();
		message += "File not found";
	}
}
