package items;

public class Armor extends Equipment{
	public Armor (int weight, int value, String name, int rating, Location location, String description) {
		super(weight, value, name, description, rating);
		if (location == Location.NECK || location == Location.FINGER) {
			//Some kind of error? Should never get here though
		}
		this.location = location;
	}
}
