import java.util.LinkedList;

public abstract class Tower {
	private LinkedList<Flyable> observers;

	public void register(Flyable flyable) {
		if (!observers.contains(flyable))
			observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	protected void conditionsChanged(){
		for (Flyable item : observers) {
			item.updateConditions();
		}
	}
}
