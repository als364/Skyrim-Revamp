package items;

public class Bow extends Weapon{
	public Bow(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.BOW, damage);
	}
}
