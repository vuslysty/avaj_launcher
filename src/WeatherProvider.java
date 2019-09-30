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
		if (weatherProvider != null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates){
		int	seed;

		seed = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
		return (weather[seed % weather.length]);
	}
}
