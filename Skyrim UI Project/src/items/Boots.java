package items;

public class Boots extends Armor{
	public Boots(int weight, int value, String name, int rating, String description) {
		super(weight, value, name, rating, Location.FEET, description);
	}
}
