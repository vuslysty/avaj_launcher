package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;
import Simulation.WeatherProvider;
import Simulation.WeatherTower;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private static HashMap<String, String> phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "Bgggg It's raining, I am completely wet");
		result.put("FOG", "I can't see beyond my own hands");
		result.put("SUN", "Hey, where are my sunglasses?");
		result.put("SNOW", "It's time to make a snowman");
		result.put("TORNADO", "We got caught in a tornadoooooo arggggggr");
		result.put("THUNDERSTORM", "Lightning hit a ball. Bring adhesive tape!");
		result.put("DUSTSTORM", "Sand is everywhere!! My eyes!!!!");
		return result;
	}

	Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "Baloon";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather));

		if (weather.equals("RAIN"))
			changeCoordinates(2, 0, 4);
		else if (weather.equals("FOG"))
			changeCoordinates(0, 0, -5);
		else if (weather.equals("SUN"))
			changeCoordinates(0, 0, -3);
		else if (weather.equals("SNOW"))
			changeCoordinates(0, 0, -15);
		else if (weather.equals("TORNADO"))
			changeCoordinates(4, 13, 15);
		else if (weather.equals("THUNDERSTORM"))
			changeCoordinates(0, 2, -17);
		else if (weather.equals("DUSTSTORM"))
			changeCoordinates(8, 3, -1);

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