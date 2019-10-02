package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;
import Simulation.WeatherProvider;
import Simulation.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private static HashMap<String, String>	phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "Rain.. It's good I'm in a warm seat");
		result.put("FOG", "Great, everything is white around of me");
		result.put("SUN", "Fuck, I forgot a sunscreen");
		result.put("SNOW", "Let it snow, let it snow, let it snow");
		result.put("TORNADO", "Uuuh, how a big TORNADO!!");
		result.put("THUNDERSTORM", "Aaaaa we lost our wing");
		result.put("DUSTSTORM", "Sand in rotors!! We are losing altitude");
		return result;
	}

	JetPlane(String name, Coordinates coordinates)
	{
		super(name, coordinates);
		this.aircraftType = "JetPlane";
	}

	@Override
	public void updateConditions() {
		String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);

		Simulator.writer.print(getFormattingName() + ": ");
		Simulator.writer.println(phrases.get(weather));

		if (weather.equals("RAIN"))
			changeCoordinates(0, 10, 2);
		else if (weather.equals("FOG"))
			changeCoordinates(0, 5, 0);
		else if (weather.equals("SUN"))
			changeCoordinates(0, 1, 0);
		else if (weather.equals("SNOW"))
			changeCoordinates(0, 0, -7);
		else if (weather.equals("TORNADO"))
			changeCoordinates(2, 4, 10);
		else if (weather.equals("THUNDERSTORM"))
			changeCoordinates(0, 10, -8);
		else if (weather.equals("DUSTSTORM"))
			changeCoordinates(8, 0, -2);

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