public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {"Lol"};

	private WeatherProvider(){}

	public static WeatherProvider getProvider(){
		if (weatherProvider != null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates){
		return null; //TODO getCurrentWeather
	}
}
