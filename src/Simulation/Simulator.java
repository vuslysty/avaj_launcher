package Simulation;

import Exceptions.AvajException;
import Exceptions.CyclesException;
import Exceptions.NoFileException;
import Exceptions.ScenarioException;
import FlyableObjs.Flyable;
import Parsing.FileParsing;

import java.io.*;
import java.util.*;

public class Simulator {

	public static PrintWriter			writer;
	private static int					cicles;
	private static LinkedList<Flyable>	flyables;

	public static void main(String[] args) throws IOException, ScenarioException, CyclesException, NoFileException {

		if (args.length != 1) {
			System.out.println("usage: ./simulator [scenarioFile]");
			return ;
		}


		File simulationFile = new File("simulation.txt");
		WeatherTower	weatherTower = new WeatherTower();
		FileParsing	fileParsing;

		try {
			writer = new PrintWriter(simulationFile);
			fileParsing = new FileParsing(args[0]);

			cicles = fileParsing.getCycles();
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
}
