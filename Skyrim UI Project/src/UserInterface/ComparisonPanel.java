package UserInterface;

import items.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ComparisonPanel extends JPanel{
	
	public ComparisonPanel(){
		super(new GridLayout(1,3,0,0));
		setBackground(Color.GREEN);
		//add(new JLabel("Comparison Panel"));
	}
	
	public void update() {
		this.removeAll();
		setBackground(Color.GREEN);
		InventoryGrid inventoryToCheck;
		if (Launcher.getMainPanel().getComparisonPanel() == this)
			inventoryToCheck = Launcher.getMainPanel().getInventoryGrid();
		else
			inventoryToCheck = Launcher.getFavPanel().getInventoryGrid();
		if (inventoryToCheck.focusElement != null) {
			Item comparisonItem = inventoryToCheck.focusElement.i;
			// Check for equipment
			if (comparisonItem instanceof Armor) {
				String stats = "<HTML><center><P><P><P><P>Name<P>Rating<P>Value<P>Weight</HTML>";
				JLabel label3 = new JLabel(stats, SwingConstants.CENTER);
				label3.setVerticalAlignment(SwingConstants.TOP);
				add(label3);
				Item currentEquipment = null;
				
				// Gets the appropriate location based on the equipment
				if(comparisonItem instanceof BodyArmor)
					currentEquipment = Constants.player.getBody();
				if(comparisonItem instanceof Gauntlets)
					currentEquipment = Constants.player.getHands();
				if(comparisonItem instanceof Shield)
					currentEquipment = Constants.player.getShield();
				if(comparisonItem instanceof Boots)
					currentEquipment = Constants.player.getFeet();
				if(comparisonItem instanceof Helmet)
					currentEquipment = Constants.player.getHead();
				
				String oldStats = "<HTML><center>Current Equipment<P><P><P>";
				if (currentEquipment != null && currentEquipment.getName() != "") {
					int rating = (currentEquipment instanceof Armor ? ((Armor)currentEquipment).rating : 0);
					oldStats += currentEquipment.getName() + "<P>";
					oldStats += rating + "<P>";
					oldStats += currentEquipment.getValue() + "<P>";
					oldStats += currentEquipment.getWeight() + "<P>";
				}
				else
					oldStats += "<left>No " + comparisonItem.getClass().toString().substring(12) + " equipped";
				oldStats += "</HTML>";
				JLabel label = new JLabel(oldStats, SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.TOP);
				add(label);
				
				Armor newArmor = (Armor)comparisonItem;
				String newStats = "<HTML><center>New Armor<P><P><P>";

				newStats += newArmor.getName() + "<P>";
				newStats += newArmor.getRating() + "<P>";
				newStats += newArmor.getValue() + "<P>";
				newStats += newArmor.getWeight() + "<P>";
				newStats += "</HTML>";
				JLabel label2 = new JLabel(newStats, SwingConstants.CENTER);
				label2.setVerticalAlignment(SwingConstants.TOP);
				add(label2);
			}
			else  {
				if (comparisonItem instanceof Weapon) {
					String stats = "<HTML><center><P><P><P><P>Name<P>Damage<P>Value<P>Weight</HTML>";
					JLabel label3 = new JLabel(stats, SwingConstants.CENTER);
					label3.setVerticalAlignment(SwingConstants.TOP);
					add(label3);
					
					String oldStats = "<HTML><center>Current Weapon<P><P><P>";
					if (Constants.player.getWeapon() != null) {
						Weapon current = Constants.player.getWeapon();
						oldStats += current.getName() + "<P>";
						oldStats += current.getDamage() + "<P>";
						oldStats += current.getValue() + "<P>";
						oldStats += current.getWeight() + "<P>";
					}
					else
						oldStats += "<left>No weapon equipped";
					oldStats += "</HTML>";
					JLabel label = new JLabel(oldStats, SwingConstants.CENTER);
					label.setVerticalAlignment(SwingConstants.TOP);
					add(label);
					
					String newStats = "<HTML><center>New Weapon<P><P><P>";
					
					Weapon newWeapon = (Weapon) comparisonItem;
					newStats += newWeapon.getName() + "<P>";
					newStats += newWeapon.getDamage() + "<P>";
					newStats += newWeapon.getValue() + "<P>";
					newStats += newWeapon.getWeight() + "<P>";
					newStats += "</HTML>";
					JLabel label2 = new JLabel(newStats, SwingConstants.CENTER);
					label2.setVerticalAlignment(SwingConstants.TOP);
					add(label2);
				}
			
				else {
					add(new JLabel("No need to compare this"));
				}
			}
		}
		this.revalidate();
		this.repaint();
	}
}
