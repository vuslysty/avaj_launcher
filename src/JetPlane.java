import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower					weatherTower;
	private static HashMap<String, String>	phrases = createHashMap();

	private static HashMap<String, String> createHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("RAIN", "It's rain");
		result.put("FOG", "It's fog");
		result.put("SUN", "It's sun");
		result.put("SNOW", "It's snow");
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
		Simulator.writer.println(phrases.get(weather) + ".");

		if (weather == "RAIN")
			changeCoordinates(0, 10, 2);
		else if (weather == "FOG")
			changeCoordinates(0, 5, 0);
		else if (weather == "SUN")
			changeCoordinates(0, 1, 0);
		else if (weather == "SNOW")
			changeCoordinates(0, 0, 7);

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