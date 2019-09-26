public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower	weatherTower;

	JetPlane(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		//TODO updateConditions
	}

	@Override
	public void registerTower(WeatherTower WeatherTower) {
		//TODO registerTower
	}
}