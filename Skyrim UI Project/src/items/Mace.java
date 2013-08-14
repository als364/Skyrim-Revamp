package items;

public class Mace extends Weapon{
	public Mace(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.ONE_HAND, damage);
	}
}
