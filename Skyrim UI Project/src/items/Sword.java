package items;

public class Sword extends Weapon{
	public Sword(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.ONE_HAND, damage);
	}
}
