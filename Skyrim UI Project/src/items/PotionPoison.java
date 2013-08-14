package items;
import static UserInterface.Constants.*;

public class PotionPoison extends Potion{

	public PotionPoison(int weight, int value, String name, String description) {
		super(weight, value, name, description, poisonStatus);
	}

}
