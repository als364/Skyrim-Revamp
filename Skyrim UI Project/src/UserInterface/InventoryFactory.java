package UserInterface;

import items.*;
import Player.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.IIOException;

import static UserInterface.Constants.*;

public class InventoryFactory {

	public static void loadInventory(Player player){
		
		try{
			  FileInputStream fstream = new FileInputStream(inventoryFile);
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  
			  
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
				  
				  String[] strArr = strLine.split("~");
				  
				  String imageName = strArr[0];
				  String name = strArr[1];
				  String type = strArr[2];
				  int value = -1;
				  int weight = -1;
				  int damage = -1;
				  int rating = -1;
				  
				  try{
					  value = Integer.parseInt(strArr[3]);
				  } catch(NumberFormatException e){
					  
				  }
				  
				  try{
					  weight = Integer.parseInt(strArr[4]);
				  } catch(NumberFormatException e){
					  
				  }
				  
				  try{
					  damage = Integer.parseInt(strArr[5]);
				  } catch(NumberFormatException e){
					  
				  }
				  
				  try{
					  rating = Integer.parseInt(strArr[6]);
				  } catch(NumberFormatException e){
					  
				  }
				  
				  String property = strArr[7];
				  
				  String description = "";
				  if(strArr.length >= 9){
					  description = strArr[8];
				  } 
				  
				  Item i = null;
				  
				  boolean discontinue = false;
				  
				  if(type.equals("Sword")){
					  i = new Sword(weight, value, name, description, damage);
				  } else if(type.equals("War Axe")){
					  i = new WarAxe(weight, value, name, description, damage);
				  } else if(type.equals("Mace")){
					  i = new Mace(weight, value, name, description, damage);
				  } else if(type.equals("Dagger")){
					  i = new Dagger(weight, value, name, description, damage);
				  } else if(type.equals("Greatsword")){
					  i = new GreatSword(weight, value, name, description, damage);
				  } else if(type.equals("Batleaxe")){
					  i = new Battleaxe(weight, value, name, description, damage);
				  } else if(type.equals("Warhammer")){
					  i = new Warhammer(weight, value, name, description, damage);
				  } else if(type.equals("Bow")){
					  i = new Bow(weight, value, name, description, damage);
				  } else if(type.equals("Arrows")){
					  i = new Item(weight, value, name, description);
				  } else if(type.equals("Tool")){
					  i = new Item(weight, value, name, description);
				  } else if(type.equals("Heavy Armor")){
					  i = new BodyArmor(weight, value, name, rating, description);
				  } else if(type.equals("Light Armor")){
					  i = new BodyArmor(weight, value, name, rating, description);
				  } else if(type.equals("Armor")){
					  i = new BodyArmor(weight, value, name, rating, description);
				  } else if(type.equals("BodyArmor")){
					  i = new BodyArmor(weight, value, name, rating, description);
				  } else if(type.equals("Boots")){
					  i = new Boots(weight, value, name, rating, description);
				  } else if(type.equals("Gauntlets")){
					  i = new Gauntlets(weight, value, name, rating, description);
				  } else if(type.equals("Helmet")){
					  i = new Helmet(weight, value, name, rating, description);
				  } else if(type.equals("Shield")){
					  i = new Shield(weight, value, name, rating, description);
				  } else if(type.equals("Clothes")){
					  i = new Clothes(weight, value, name, description);
				  } else if(type.equals("Jewelry")){
					  i = new Necklace(weight, value, name, description);
				  } else if(type.equals("Food")){
					  i = new Food(weight, value, name, description);
				  } else if(type.equals("Potion")){
					  if(property.equals("Fortify")){
						  i = new PotionFortify(weight, value, name, description);
					  }
					  else if(property.equals("Restore")){
						  i = new PotionRestore(weight, value, name, description);
					  }
					  else if(property.equals("Poison")){
						  i = new PotionPoison(weight, value, name, description);
					  }
				  } else if(type.equals("Book")){
					  i = new Book(weight, value, name, description);
				  }
				  else{
					  discontinue = true;
				  }
				  
				  if(!discontinue){
					  try{
						  i.setImage(imageName);
						  
						  player.addToInventory(i);
					  }
					  catch(IIOException e){
						  
					  } 
					  catch (IOException e){
						  
					  }
				  }
			  }
			  //Close the input stream
			  in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
	}
}
