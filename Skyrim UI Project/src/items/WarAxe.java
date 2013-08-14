package items;

public class WarAxe extends Weapon{
	public WarAxe(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.ONE_HAND, damage);
	}
}
