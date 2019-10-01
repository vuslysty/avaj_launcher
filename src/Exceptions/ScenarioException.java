package Exceptions;

public class ScenarioException extends AvajException {

	public ScenarioException(int lineNum, String mess) {
		super(lineNum);
		message += "Scenario exception (" + mess + ")";
	}
}