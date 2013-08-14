package items;

public class Weapon extends Item{
	public enum WeaponType {ONE_HAND, TWO_HANDS, BOW};
	private WeaponType weaponType;
	private int damage;
	
	protected Weapon(int weight, int value, String name, String description, WeaponType weaponType, int damage) {
		super(weight,value,name,description);
		setWeaponType(weaponType);
		setDamage(damage);
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
