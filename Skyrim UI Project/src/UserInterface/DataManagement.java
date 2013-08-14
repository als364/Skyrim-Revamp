package UserInterface;

import items.*;
import static UserInterface.Constants.*;
import java.util.ArrayList;

public class DataManagement {
	// Returns a sublist of an item class. Doesn't handle getting subsets of the class yet,
	// for example, getting subsets of clothing that are for the body
	
	static ArrayList<Item> toAlpha = new ArrayList<Item>();
	
	public static <T> ArrayList<Item> getInventorySubset(ArrayList<Item> inventory, ArrayList<Object> itemclass) {
		ArrayList<Item> temp = new ArrayList<Item>();
		for (int i = 0; i < inventory.size(); i++) {
			if (itemclass.contains(inventory.get(i).getClass())) {
				temp.add(inventory.get(i));
			}
		}
		return temp;
	}
	
	public static <T> ArrayList<Item> alphaSortedList(ArrayList<Item> inventory) {
		ArrayList<Item> temp = new ArrayList<Item>();
		//Insertion sort
		while(inventory.size() > 0){
			if(!temp.isEmpty()){
				for(int i = 0; i < temp.size(); i++){
					if(inventory.get(0).getName().compareTo(temp.get(i).getName()) <  0){
						temp.add(i, inventory.get(0));
						inventory.remove(0);
						break;
					}
					if(i == temp.size() - 1){
						temp.add(inventory.get(0));
						inventory.remove(0);
						break;
					}
				}
			}
			else{
				temp.add(inventory.get(0));
				inventory.remove(0);
			}
		}
		return temp;
	}
	
	public static <T> ArrayList<Item> getSortedList(ArrayList<Item> inventory, int sortType) {
		ArrayList<Item> temp = new ArrayList<Item>();
		int unsorted = -1;
		int sorted = -1;
		//Insertion sort
		while(inventory.size() > 0 && sortType > -1){
			toAlpha = new ArrayList<Item>();
			toAlpha.add(inventory.get(0));
			if(!temp.isEmpty()){
				for(int i = 0; i < temp.size(); i++){
					if(sortType == price){
						unsorted = inventory.get(0).getValue();
						sorted = temp.get(i).getValue();
					}
					else if(sortType == damage){
						if(inventory.get(0) instanceof Weapon){
							unsorted = ((Weapon)inventory.get(0)).getDamage();
						}
						else{
							unsorted = 0;
						}
						if(temp.get(i) instanceof Weapon){
							sorted = ((Weapon)temp.get(i)).getDamage();
						}
						else{
							sorted = 0;
						}
					}
					else if(sortType == armor){
						if(inventory.get(0) instanceof Armor){
							unsorted = ((Armor)inventory.get(0)).getRating();
						}
						else{
							unsorted = 0;
						}
						if(temp.get(i) instanceof Armor){
							sorted = ((Armor)temp.get(i)).getRating();
						}
						else{
							sorted = 0;
						}
											
					}
					else if(sortType == weight){
						unsorted = inventory.get(0).getWeight();
						sorted = temp.get(i).getWeight();
					}
					else if(sortType == statusEffect){
						if(inventory.get(0) instanceof Potion){
							unsorted = ((Potion)inventory.get(0)).getStatusEffect();
							System.out.println("HELLO POTION ? " + unsorted);
						}
						else{
							System.out.println("no potion");
							unsorted = 0;
						}
						if(temp.get(i) instanceof Potion){
							sorted = ((Potion)temp.get(i)).getStatusEffect();
						}
						else{
							sorted = 0;
						}
					}
					if(unsorted > sorted){
						temp.add(i, inventory.get(0));
						/*int alphaSize = toAlpha.size();
						
						toAlpha = alphaSortedList(toAlpha);
						
						System.out.println("i " + i + " alphasize " + alphaSize);
						for(int a = alphaSize; a > 0; a--){
							//temp.remove(i - a + 1);
							temp.add(i - a + 1, toAlpha.get(alphaSize - a));
						}*/
						
						inventory.remove(0);
						break;
					} else if(unsorted == sorted){
						toAlpha.add(temp.get(i));
						//temp.remove(i);
					}
					if(i == temp.size() - 1){
						temp.add(inventory.get(0));
						/*int alphaSize = toAlpha.size();
						
						toAlpha = alphaSortedList(toAlpha);
						
						System.out.println("i " + i + " alphasize " + alphaSize);
						for(int a = alphaSize; a > 0; a--){
							//temp.remove(i - a + 1);
							temp.add(i - a + 1, toAlpha.get(alphaSize - a));
						}*/
						
						inventory.remove(0);
						break;
					}
				}
			}
			else{
				temp.add(inventory.get(0));
				inventory.remove(0);
			}
		}
		return temp;
	}
}
