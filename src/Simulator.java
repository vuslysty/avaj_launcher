import Exceptions.AvajException;
import Exceptions.CiclesException;

import java.io.*;
import java.util.*;

public class Simulator {

	public static PrintWriter			writer;
	private static int					cicles;
	private static LinkedList<Flyable>	flyables = new LinkedList<Flyable>();

	public static void main(String[] args) throws IOException {

		try {
			parseFile(args[0]);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		File simulationFile = new File("simulation.txt");
		writer = new PrintWriter(simulationFile);

		WeatherTower	weatherTower = new WeatherTower();

		for (Flyable item : flyables) {
			item.registerTower(weatherTower);
		}

		while (cicles-- > 0)
			weatherTower.changeWeather();

		writer.close();
	}

	private static void parseFile(String fileName) throws IOException
	{
		BufferedReader	reader = new BufferedReader(new FileReader(fileName));
		String			line;
		String[]		splitted;

		line = reader.readLine();
		cicles = Integer.parseInt(line);

		while ((line = reader.readLine()) != null)
		{
			splitted = line.split(" ");
			flyables.add(AircraftFactory.newAircraft(splitted[0], splitted[1],
					Integer.parseInt(splitted[2]),
					Integer.parseInt(splitted[3]),
					Integer.parseInt(splitted[4])));
		}

//		if ((line = reader.readLine()) != null)
//		{
//			lineNum++;
//			splitted = line.split(" ");
//			if (splitted.length != 1)
//				throw new CiclesException(lineNum);
//		}
	}
}
