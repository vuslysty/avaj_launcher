package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;
import Simulation.WeatherProvider;
import Simulation.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private static HashMap<String, String> phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "Turn the wipers on");
		result.put("FOG", "The fog... It's like in Steven King's book");
		result.put("SUN", "Beautiful weather");
		result.put("SNOW", "My rotor is going to freeze");
		result.put("TORNADO", "Look, tornado is spinning faster than my rotor");
		result.put("THUNDERSTORM", "Mmm.. such beautiful lights in the sky. Stop, I am at the sky too, AAA!!");
		result.put("DUSTSTORM", "I can't see anything, need to go away");
		return result;
	}

	Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "Helicopter";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather));

		if (weather.equals("RAIN"))
			changeCoordinates(10, 0, 2);
		else if (weather.equals("FOG"))
			changeCoordinates(5, 0, 0);
		else if (weather.equals("SUN"))
			changeCoordinates(1, 0, 0);
		else if (weather.equals("SNOW"))
			changeCoordinates(0, 0, -12);
		else if (weather.equals("TORNADO"))
			changeCoordinates(0, 0, 4);
		else if (weather.equals("THUNDERSTORM"))
			changeCoordinates(1, 2, -2);
		else if (weather.equals("DUSTSTORM"))
			changeCoordinates(12, 16, 2);

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
