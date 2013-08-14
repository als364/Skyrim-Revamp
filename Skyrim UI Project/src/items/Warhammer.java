package items;

public class Warhammer extends Weapon{
	public Warhammer(int weight, int value, String name, String description, int damage) {
		super(weight, value, name, description, WeaponType.TWO_HANDS, damage);
	}
}
