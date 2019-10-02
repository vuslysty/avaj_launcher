package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;
import Simulation.WeatherProvider;
import Simulation.WeatherTower;

import java.util.HashMap;

public class UFO extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private static HashMap<String, String> phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "Strange liquid falls from the sky");
		result.put("FOG", "What is the hell? Why we don't see anything?");
		result.put("SUN", "turn on the stealth mode");
		result.put("SNOW", "Brrr, cold things falling from the sky");
		result.put("TORNADO", "Oou the world is spinning, so funny");
		result.put("THUNDERSTORM", "Funny lights in the sky");
		result.put("DUSTSTORM", "Duststorm!!! Let's go away from here");
		return result;
	}

	UFO(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "UFO";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather));

		if (weather.equals("RAIN"))
			changeCoordinates(2, 0, 4);
		else if (weather.equals("FOG"))
			changeCoordinates(0, 2, -1);
		else if (weather.equals("SUN"))
			changeCoordinates(0, 3, 10);
		else if (weather.equals("SNOW"))
			changeCoordinates(0, 0, -3);
		else if (weather.equals("TORNADO"))
			changeCoordinates(7, 3, -3);
		else if (weather.equals("THUNDERSTORM"))
			changeCoordinates(0, 2, 0);
		else if (weather.equals("DUSTSTORM"))
			changeCoordinates(1, 14, 2);

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
