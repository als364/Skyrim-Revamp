package items;
import static UserInterface.Constants.*;

public class PotionRestore extends Potion{

	public PotionRestore(int weight, int value, String name, String description) {
		super(weight, value, name, description, restoreStatus);
	}

}
