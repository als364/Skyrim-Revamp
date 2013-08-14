package items;

public class Dagger extends Weapon{
	public Dagger(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.ONE_HAND, damage);
	}
}
