package Exceptions;

public class AvajException extends Exception {

	protected String	message;

	public AvajException()
	{
		message = "Error: ";
	}

	public AvajException(int num)
	{
		message = "Error at line " + num + ": ";
	}

	public String toString() {
		return message;
	}
}
