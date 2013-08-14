package items;

public class Helmet extends Armor{
	public Helmet(int weight, int value, String name, int rating, String description) {
		super(weight, value, name, rating, Location.HEAD, description);
	}
}
