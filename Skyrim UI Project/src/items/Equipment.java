package items;

// Basically an Item, but we want to differentiate because we need to check equipment rules
public class Equipment extends Item {
	public enum Location {HEAD, BODY, FEET, HANDS, NECK, FINGER, SHIELD};
	public Location location;
	public int rating;
	
	protected Equipment(int weight, int value, String name, String description, int rating) {
		super(weight, value, name, description);
		this.rating = rating;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public int getRating() {
		return rating;
	}
}
