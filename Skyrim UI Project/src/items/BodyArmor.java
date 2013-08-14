package items;

public class BodyArmor extends Armor{
	public BodyArmor(int weight, int value, String name, int rating, String description) {
		super(weight, value, name, rating, Location.BODY, description);
	}
}
