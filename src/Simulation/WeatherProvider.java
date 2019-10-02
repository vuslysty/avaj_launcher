package Simulation;

import java.util.Random;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {
			"RAIN",
			"FOG",
			"SUN",
			"SNOW",
			"TORNADO",
			"THUNDERSTORM",
			"DUSTSTORM"
	};

	private WeatherProvider(){}

	public static WeatherProvider getProvider(){
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates){
		Random	random = new Random();

		return (weather[random.nextInt(weather.length)]);
	}
}
