package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;
import Simulation.WeatherProvider;
import Simulation.WeatherTower;

import java.util.HashMap;

public class StarShip extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private static HashMap<String, String> phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "It is just rain. We are not frightened you!!");
		result.put("FOG", "Fog.. okay");
		result.put("SUN", "The sun is shining right into the eye");
		result.put("SNOW", "How cold! But the snowflakes are pretty");
		result.put("TORNADO", "I can't believe. It's a tornado, let's fly there");
		result.put("THUNDERSTORM", "Thunderstorm.. my favorite weather");
		result.put("DUSTSTORM", "Fucking sand, it's everywhere, even in my underwear");
		return result;
	}

	StarShip(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "StarShip";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather));

		if (weather.equals("RAIN"))
			changeCoordinates(2, 0, 4);
		else if (weather.equals("FOG"))
			changeCoordinates(0, 2, -2);
		else if (weather.equals("SUN"))
			changeCoordinates(0, 0, 2);
		else if (weather.equals("SNOW"))
			changeCoordinates(5, 0, -4);
		else if (weather.equals("TORNADO"))
			changeCoordinates(3, 16, 5);
		else if (weather.equals("THUNDERSTORM"))
			changeCoordinates(3, 2, -1);
		else if (weather.equals("DUSTSTORM"))
			changeCoordinates(4, 6, -3);

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
