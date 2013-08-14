package items;

public class Clothes extends Clothing{
	public Clothes(int weight, int value, String name, String description) {
		super(weight, value, name, Location.BODY, description);
	}
}
