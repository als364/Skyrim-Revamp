package items;
import static UserInterface.Constants.*;

public class PotionFortify extends Potion{

	public PotionFortify(int weight, int value, String name, String description) {
		super(weight, value, name, description, fortifyStatus);
	}

}
