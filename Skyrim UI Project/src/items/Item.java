package items;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import UserInterface.Constants.*;

public class Item {
	private int weight;
	private int value;
	private String name;
	private String description;
	private BufferedImage img;
	
	
	public Item(int weight, int value, String name, String description) {
		setWeight(weight);
		setValue(value);
		setName(name);
		setDescription(description);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCostWeightRatio() {
		return (float) weight / (float) value;
	}

	public void setImage(String imageName) throws IIOException, IOException{
			img = ImageIO.read(new File("Skyrim Art/" + imageName));
	}
	
	public BufferedImage getImage(){
		return img;
	}
	

}
