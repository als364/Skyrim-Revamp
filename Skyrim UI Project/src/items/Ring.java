package items;

public class Ring extends Clothing {
	public Ring(int weight, int value, String name, String description) {
		super(weight, value, name, Location.FINGER, description);
	}
}
