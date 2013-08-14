package items;

import items.Equipment.Location;

public class Necklace extends Clothing{
	public Necklace(int weight, int value, String name, String description) {
		super(weight, value, name, Location.NECK, description);
	}
}