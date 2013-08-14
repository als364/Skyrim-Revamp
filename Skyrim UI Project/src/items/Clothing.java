package items;

public class Clothing extends Equipment{
	protected Clothing(int weight, int value, String name, Location location, String description) {
		super(weight,value,name,description,0);
		if (location == Location.SHIELD) {
			//error
		}
		this.location = location;
	}
}
