package items;

public class Battleaxe extends Weapon{
	public Battleaxe(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.TWO_HANDS, damage);
	}
}
