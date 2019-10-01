package Simulation;

import java.util.Random;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {
			"RAIN",
			"FOG",
			"SUN",
			"SNOW"
	};

	private WeatherProvider(){}

	public static WeatherProvider getProvider(){
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates){
//		int	seed;
		Random	random = new Random();

//		seed = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
//		random.setSeed(seed);
//
//		seed = random.nextInt(4);
//
//		seed = seed < 0 ? seed * -1 : seed;

		return (weather[random.nextInt(4)]);
	}
}
