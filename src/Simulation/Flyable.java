package Simulation;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower WeatherTower); // can be confuse in Simulation.WeatherTower
}
