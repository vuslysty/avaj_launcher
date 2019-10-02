package Parsing;

import Exceptions.CyclesException;
import Exceptions.NoFileException;
import Exceptions.NumberException;
import Exceptions.ScenarioException;
import FlyableObjs.AircraftFactory;
import FlyableObjs.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileParsing {

	private int							lineNum;
	private String[]					split;
	private int 						cycles;
	private LinkedList<Flyable> 		flyables;
	private BufferedReader 				reader;

	public LinkedList<Flyable>			getFlyables()
	{
		return flyables;
	}

	public int getCycles()
	{
		return cycles;
	}

	public FileParsing(String fileName) throws NoFileException, IOException, CyclesException, ScenarioException, NumberException {
		String	line;
		this.flyables = new LinkedList<>();

		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new NoFileException();
		}

		readCicles();

		while ( (line = reader.readLine()) != null)
		{
			lineNum++;

			if (line.isEmpty())
				throw new ScenarioException(lineNum, "Empty line instead of scenario");

			split = line.split(" ");
			if (split.length != 5)
				throw new ScenarioException(lineNum, "Wrong arguments number");

			try {
				checkCoordinates(split[2], "Longitude");
				checkCoordinates(split[3], "Latitude");
				checkHeight(split[4]);

				Flyable	flyable = AircraftFactory.newAircraft(split[0], split[1],
						Integer.parseInt(split[2]),
						Integer.parseInt(split[3]),
						Integer.parseInt(split[4]));

				if (flyable != null)
					flyables.add(flyable);
				else
					throw new ScenarioException(lineNum, "Undeclared type of flyable object");

			} catch (NumberFormatException e)  {
				throw new NumberException(lineNum);
			}
		}
	}

	boolean	isNumber(String str){
		return str.matches("[-+]?\\d+");
	}

	void 	readCicles() throws IOException, CyclesException, NumberException {
		String	line;

		lineNum++;

		if ((line = reader.readLine()) != null && !line.isEmpty())
		{
			split = line.split(" ");
			if (split.length != 1)
				throw new CyclesException(lineNum, "Wrong arguments number");
			if (!isNumber(split[0]))
				throw new CyclesException(lineNum, "Argument is not a number");
			try {
				if ((cycles = Integer.parseInt(split[0])) < 0)
					throw new CyclesException(lineNum, "Argument must be a positive number");
			} catch (NumberFormatException e) {
				throw new NumberException(lineNum);
			}
		}
		else
			throw new CyclesException(lineNum, "Empty Line");
	}

	void	checkCoordinates(String coord, String type) throws ScenarioException {
		if (!isNumber(coord))
			throw new ScenarioException(lineNum, type + " is not a number");

		if (Integer.parseInt(coord) < 0)
			throw new ScenarioException(lineNum, type + " must be a positive number");
	}

	void	checkHeight(String height) throws ScenarioException {
		if (!isNumber(height))
			throw new ScenarioException(lineNum, "Height is not a number");
	}

}
