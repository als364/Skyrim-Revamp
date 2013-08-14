package Player;

import items.*;

// Class designed to manipulate the information stored in Player
public class EquipLogic{
	public static boolean equip(Item item, Player player) {
		if (item instanceof Equipment)
			return equipEquipment((Equipment) item, player);
		else if (item instanceof Weapon)
			return equipWeapon((Weapon) item, player);
		else{
			System.out.println("Can't equip that!");
			return false;
		}
	}
	
	// Equips Clothing and Armor
	static boolean equipEquipment(Equipment equipment, Player player) {
		switch (equipment.getLocation()) {
		case BODY:
			return player.setBody(equipment);
		case HEAD:
			return player.setHead(equipment);
		case FEET:
			return player.setFeet(equipment);
		case HANDS:
			return player.setHands(equipment);
		case NECK:
			return player.setNeck(equipment);
		case FINGER:
			return player.setFinger(equipment);
		case SHIELD:
			return player.setShield(equipment);
		default:
			return false;
		}
	}
	// Equips Weapons
	// Two handed weapons and bows need to unequip shields
	static boolean equipWeapon(Weapon weapon, Player player) {
		if (weapon.getWeaponType() != Weapon.WeaponType.ONE_HAND) {
			player.setShield(null);
		}
		return player.setWeapon(weapon);
	}
}
