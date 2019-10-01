package Simulation;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower						weatherTower;
	private static HashMap<String, String> phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "It's rain");
		result.put("FOG", "It's fog");
		result.put("SUN", "It's sun");
		result.put("SNOW", "It's snow");
		return result;
	}

	Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "Simulation.Baloon";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather) + ".");

		if (weather.equals("RAIN"))
			changeCoordinates(2, 0, 4);
		else if (weather.equals("FOG"))
			changeCoordinates(0, 0, -5);
		else if (weather.equals("SUN"))
			changeCoordinates(0, 0, -3);
		else if (weather.equals("SNOW"))
			changeCoordinates(0, 0, -15);

		if (coordinates.getHeight() == 0)
		{
			getUnregisteredMessage(aircraftType);
			weatherTower.unregister(this);
		}
	}

	@Override
	public void registerTower(WeatherTower WeatherTower) {
		this.weatherTower = WeatherTower;
		this.weatherTower.register(this);
		getRegisteredMessage();
	}
}