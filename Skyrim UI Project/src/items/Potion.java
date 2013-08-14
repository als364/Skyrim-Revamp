package items;

public class Potion extends Item{
	private int statusEffect;
	
	public Potion(int weight, int value, String name, String description, int status) {
		super(weight, value, name, description);
		statusEffect = status;
	}
	
	public int getStatusEffect(){
		return statusEffect;
		
	}
}
