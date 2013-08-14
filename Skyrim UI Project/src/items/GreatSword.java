package items;

public class GreatSword extends Weapon{
	public GreatSword(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.TWO_HANDS, damage);
	}
}

