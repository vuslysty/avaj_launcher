package Simulation;

import Exceptions.AvajException;
import Exceptions.CiclesException;
import Exceptions.NoFileException;
import Exceptions.ScenarioException;
import Parsing.FileParsing;

import java.io.*;
import java.util.*;

public class Simulator {

	public static PrintWriter			writer;
	private static int					cicles;
	private static LinkedList<Flyable>	flyables;

	public static void main(String[] args) throws IOException, ScenarioException, CiclesException, NoFileException {

		File simulationFile = new File("simulation.txt");
		WeatherTower	weatherTower = new WeatherTower();
		FileParsing	fileParsing;

		try {
			writer = new PrintWriter(simulationFile);
			fileParsing = new FileParsing(args[0]);

			cicles = fileParsing.getCicles();
			flyables = fileParsing.getFlyables();


			for (Flyable item : flyables) {
				item.registerTower(weatherTower);
			}

			while (cicles-- > 0)
				weatherTower.changeWeather();
		} catch (AvajException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		writer.close();
	}

//	private static void parseFile(String fileName) throws IOException
//	{
//		BufferedReader	reader = new BufferedReader(new FileReader(fileName));
//		String			line;
//		String[]		splitted;
//
//		line = reader.readLine();
//		cicles = Integer.parseInt(line);
//
//		while ((line = reader.readLine()) != null)
//		{
//			splitted = line.split(" ");
//			flyables.add(AircraftFactory.newAircraft(splitted[0], splitted[1],
//					Integer.parseInt(splitted[2]),
//					Integer.parseInt(splitted[3]),
//					Integer.parseInt(splitted[4])));
//		}
//
////		if ((line = reader.readLine()) != null)
////		{
////			lineNum++;
////			splitted = line.split(" ");
////			if (splitted.length != 1)
////				throw new CiclesException(lineNum);
////		}
//	}


}
