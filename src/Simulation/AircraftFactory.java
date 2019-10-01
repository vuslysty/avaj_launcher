package Simulation;

public class AircraftFactory {

	public static Flyable	newAircraft(String type, String name, int longitude, int latitude, int height){
		Flyable		aircraft = null;

		switch (type)
		{
			case "Baloon":
				aircraft = new Baloon(name, new Coordinates(longitude, latitude, height));
				break;
			case "JetPlane":
				aircraft = new JetPlane(name, new Coordinates(longitude, latitude, height));
				break;
			case "Helicopter":
				aircraft = new Helicopter(name, new Coordinates(longitude, latitude, height));
				break;
		}
		return aircraft;
	}
}
