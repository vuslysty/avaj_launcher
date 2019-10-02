package FlyableObjs;

import Simulation.Coordinates;
import Simulation.Simulator;

public abstract class Aircraft {
	protected long 			id;
	protected String		name;
	protected Coordinates 	coordinates;
	protected String		aircraftType;

	private static long			idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates)
	{
		this.id = nextId();
		this.name = name;
		this.coordinates = coordinates;
	}

	protected String	getFormattingName()
	{
		return (aircraftType + "#" + name + "(" + id + ")");
	}

	protected void getRegisteredMessage()
	{
		Simulator.writer.println("Tower says: " + getFormattingName() +
				" registered to weather tower.");
	}

	protected void getUnregisteredMessage(String aircraftType)
	{
		Simulator.writer.println(getFormattingName() + " landing at " +
				"longitude: " + coordinates.getLongitude() + " " +
				"latitude: " + coordinates.getLatitude() + ".");
		Simulator.writer.println("Tower says: " + getFormattingName() +
				" unregistered from weather tower.");
	}

	protected void changeCoordinates(int longitude, int latitude, int height)
	{
		coordinates = new Coordinates(
				coordinates.getLongitude() + longitude,
				coordinates.getLatitude() + latitude,
				coordinates.getHeight() + height
		);
	}

	private long nextId() {
		return ++idCounter;
	}
}
