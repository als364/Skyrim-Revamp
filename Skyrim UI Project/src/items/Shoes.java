package items;

public class Shoes extends Clothing{
	public Shoes(int weight, int value, String name, String description) {
		super(weight, value, name, Location.FEET, description);
	}

}
