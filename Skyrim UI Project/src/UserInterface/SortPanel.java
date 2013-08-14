package UserInterface;

import items.*;
import static UserInterface.Constants.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SortPanel extends JMenuBar{

	private JLabel catLabel = new JLabel("Category>>");
	private JMenuBar catMenuBar = new JMenuBar();
	private JMenu catMenu = new JMenu("Categories");
	private boolean catTrueVisible = false;
	
	private JLabel sortLabel = new JLabel("Sort By>>");
	private JMenuBar sortMenuBar = new JMenuBar();
	private JMenu sortMenu = new JMenu("Sort By");
	private boolean sortTrueVisible = false;
	
	private int headerWidth = 90;
	private int headerHeight = 20;
	
	private int currentSort = -1;
	
	InventoryGrid ig;
	
	HashMap<String, Integer> sortMapping = new HashMap<String, Integer>();
	
	JCheckBoxMenuItem allCheck = new JCheckBoxMenuItem("All");
	
	JCheckBoxMenuItem apparelCheck = new JCheckBoxMenuItem("Apparel");
	JCheckBoxMenuItem helmetCheck = new JCheckBoxMenuItem("    -Helmets");
	JCheckBoxMenuItem torsoCheck = new JCheckBoxMenuItem("    -Torso");
	JCheckBoxMenuItem gauntletsCheck = new JCheckBoxMenuItem("    -Gauntlets");
	JCheckBoxMenuItem bootsCheck = new JCheckBoxMenuItem("    -Boots");
	JCheckBoxMenuItem shieldsCheck = new JCheckBoxMenuItem("    -Shields");
	JCheckBoxMenuItem ringsCheck = new JCheckBoxMenuItem("    -Rings");
	JCheckBoxMenuItem amuletsCheck = new JCheckBoxMenuItem("    -Amulets");
	
	JCheckBoxMenuItem weaponsCheck = new JCheckBoxMenuItem("Weapon");
	JCheckBoxMenuItem oneSwordCheck = new JCheckBoxMenuItem("    -1-Hand Sword");
	JCheckBoxMenuItem twoSwordCheck = new JCheckBoxMenuItem("    -2-Hand Sword");
	JCheckBoxMenuItem axeCheck = new JCheckBoxMenuItem("    -Axes");
	JCheckBoxMenuItem bowsCheck = new JCheckBoxMenuItem("    -Bows/Arrows");
	JCheckBoxMenuItem hammersCheck = new JCheckBoxMenuItem("    -Hammers");
	JCheckBoxMenuItem macesCheck = new JCheckBoxMenuItem("    -Maces");

	JCheckBoxMenuItem potionsCheck = new JCheckBoxMenuItem("Potions");
	JCheckBoxMenuItem poisonCheck = new JCheckBoxMenuItem("    -Poison");
	JCheckBoxMenuItem restoreCheck = new JCheckBoxMenuItem("    -Restore");
	JCheckBoxMenuItem fortifyCheck = new JCheckBoxMenuItem("    -Fortify");
	
	JCheckBoxMenuItem foodCheck = new JCheckBoxMenuItem("Food");
	JCheckBoxMenuItem booksCheck = new JCheckBoxMenuItem("Books");
	
	JCheckBoxMenuItem costCheck = new JCheckBoxMenuItem("Price");
	JCheckBoxMenuItem damageCheck = new JCheckBoxMenuItem("Damage");
	JCheckBoxMenuItem armorCheck = new JCheckBoxMenuItem("Armor");
	JCheckBoxMenuItem weightCheck = new JCheckBoxMenuItem("Weight");
	JCheckBoxMenuItem statucCheck = new JCheckBoxMenuItem("Status Effect");
	
	public SortPanel(InventoryGrid ig){
		setOpaque(true);
		
		this.ig = ig;
		
		catMenu.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(catMenu.isPopupMenuVisible() && catTrueVisible){
					catMenu.setPopupMenuVisible(false);
					catTrueVisible = false;
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {	}
			@Override
			public void mouseExited(MouseEvent arg0) {	}
			@Override
			public void mousePressed(MouseEvent arg0) {	}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		sortMenu.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(sortMenu.isPopupMenuVisible() && sortTrueVisible){
					sortMenu.setPopupMenuVisible(false);
					sortTrueVisible = false;
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {	}
			@Override
			public void mouseExited(MouseEvent arg0) {	}
			@Override
			public void mousePressed(MouseEvent arg0) {	}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		catMenu.add(allCheck);
		allCheck.addActionListener(new CatClick());
		allCheck.setSelected(true);
		
		catMenu.add(apparelCheck);
		apparelCheck.addActionListener(new CatClick());
		catMenu.add(helmetCheck);
		helmetCheck.addActionListener(new CatClick());
		catMenu.add(torsoCheck);
		torsoCheck.addActionListener(new CatClick());
		catMenu.add(gauntletsCheck);
		gauntletsCheck.addActionListener(new CatClick());
		catMenu.add(bootsCheck);
		bootsCheck.addActionListener(new CatClick());
		catMenu.add(shieldsCheck);
		shieldsCheck.addActionListener(new CatClick());
		catMenu.add(ringsCheck);
		ringsCheck.addActionListener(new CatClick());
		catMenu.add(amuletsCheck);
		amuletsCheck.addActionListener(new CatClick());
		
		catMenu.add(weaponsCheck);
		weaponsCheck.addActionListener(new CatClick());
		catMenu.add(oneSwordCheck);
		oneSwordCheck.addActionListener(new CatClick());
		catMenu.add(twoSwordCheck);
		twoSwordCheck.addActionListener(new CatClick());
		catMenu.add(axeCheck);
		axeCheck.addActionListener(new CatClick());
		catMenu.add(bowsCheck);
		bowsCheck.addActionListener(new CatClick());
		catMenu.add(hammersCheck);
		hammersCheck.addActionListener(new CatClick());
		catMenu.add(macesCheck);
		macesCheck.addActionListener(new CatClick());
		
		catMenu.add(potionsCheck);
		potionsCheck.addActionListener(new CatClick());
		catMenu.add(poisonCheck);
		poisonCheck.addActionListener(new CatClick());
		catMenu.add(restoreCheck);
		restoreCheck.addActionListener(new CatClick());
		catMenu.add(fortifyCheck);
		fortifyCheck.addActionListener(new CatClick());
		
		catMenu.add(foodCheck);
		foodCheck.addActionListener(new CatClick());
		catMenu.add(booksCheck);
		booksCheck.addActionListener(new CatClick());
		catMenu.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Sort Buttons
		sortMenu.add(costCheck);
		costCheck.addActionListener(new SortClick());
		sortMenu.add(damageCheck);
		damageCheck.addActionListener(new SortClick());
		sortMenu.add(armorCheck);
		armorCheck.addActionListener(new SortClick());
		sortMenu.add(weightCheck);
		weightCheck.addActionListener(new SortClick());
		sortMenu.add(statucCheck);
		statucCheck.addActionListener(new SortClick());
		
		
		sortMenu.setBorder(BorderFactory.createLineBorder(Color.black));
		
		setBackground(Color.orange);
		
		catMenuBar.add(catMenu);
		
		catMenuBar.add(sortMenu);
		
		add(catMenuBar);
		
		//catLabel.setBounds(25, 0, headerWidth, headerHeight);
		catMenu.setBounds(headerWidth, 0, headerWidth + 10, headerHeight);
		//sortLabel.setBounds(headerWidth * 2 + 30, 0, headerWidth, headerHeight);
		sortMenu.setBounds(headerWidth * 3, 0, headerWidth + 10, headerHeight);
		
		catMenu.setPopupMenuVisible(true);
		sort();
	}
	
	private void categorize(){
		ArrayList<GridElement> catList = new ArrayList<GridElement>();
		
		for(GridElement ge: ig.getCurrentList()){
			Item i = ge.getItem();
			
			if(allCheck.isSelected()){
				catList.add(ge);
			}
			
		}
		
		ig.setCurrentList(catList);
	}
	
	private void sort(){
		ArrayList<GridElement> sortedList = new ArrayList<GridElement>();
		
		for(GridElement ge : ig.getCurrentList()){
			Item i = ge.getItem();
			String name = i.getName();
			
			//if()
			
			
		}
		
		catMenu.setPopupMenuVisible(true);
		
	}
	
	public void showMenu(){
		catMenu.setPopupMenuVisible(true);
	}
	
	private class CatClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem currButton = ((JCheckBoxMenuItem) arg0.getSource());
			if(currButton != allCheck && currButton.isSelected()){
				allCheck.setSelected(false);
			}
			
			catMenu.setPopupMenuVisible(true);
			catTrueVisible = true;
			ig.sortList();
		}
		
	}
	
	private class SortClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean unselectBool = false;
			if(!((JCheckBoxMenuItem)arg0.getSource()).isSelected()){
				unselectBool = true;
			}
			
			sortMenu.setPopupMenuVisible(true);
			sortTrueVisible = true;
			
			costCheck.setSelected(false);
			damageCheck.setSelected(false);
			armorCheck.setSelected(false);
			weightCheck.setSelected(false);
			statucCheck.setSelected(false);
			
			if(!unselectBool){
				((JCheckBoxMenuItem) arg0.getSource()).setSelected(true);
			}
			
			if(costCheck.isSelected()){
				currentSort = price;
			} else if (damageCheck.isSelected()){
				currentSort = damage;
			} else if(armorCheck.isSelected()){
				currentSort = armor;
			} else if(weightCheck.isSelected()){
				currentSort = weight;
			} else if(statucCheck.isSelected()){
				currentSort = statusEffect;
			} else{
				currentSort = -1;
			}
			
			ig.sortList();
			
		}
		
	}
	
	public int getCurrentSort(){
		return currentSort;
	}
	
	public ArrayList<Object> retrieveCats(){
		ArrayList<Object> out = new ArrayList<Object>();
		
		if(allCheck.isSelected()){
			out.add(Item.class);
			out.add(Armor.class);
			out.add(Helmet.class);
			out.add(BodyArmor.class);
			out.add(Gauntlets.class);
			out.add(Boots.class);
			out.add(Shield.class);
			out.add(Ring.class);
			out.add(Necklace.class);
			out.add(Weapon.class);
			out.add(Sword.class);
			out.add(GreatSword.class);
			out.add(WarAxe.class);
			out.add(Bow.class);
			out.add(Warhammer.class);
			out.add(Mace.class);
			out.add(Food.class);
			out.add(PotionPoison.class);
			out.add(PotionRestore.class);
			out.add(PotionFortify.class);
			out.add(Book.class);
		} 
		else{
			if(apparelCheck.isSelected()){
				out.add(Armor.class);
				out.add(Helmet.class);
				out.add(BodyArmor.class);
				out.add(Gauntlets.class);
				out.add(Boots.class);
				out.add(Shield.class);
				out.add(Ring.class);
				out.add(Necklace.class);
			}
			else{
				if(helmetCheck.isSelected()){
					out.add(Helmet.class);
				}
				if(torsoCheck.isSelected()){
					out.add(BodyArmor.class);
				}
				if(gauntletsCheck.isSelected()){
					out.add(Gauntlets.class);
				}
				if(bootsCheck.isSelected()){
					out.add(Boots.class);
				}
				if(shieldsCheck.isSelected()){
					out.add(Shield.class);
				}
				if(ringsCheck.isSelected()){
					out.add(Ring.class);
				}
				if(amuletsCheck.isSelected()){
					out.add(Necklace.class);
				}
			}
			if(weaponsCheck.isSelected()){
				out.add(Weapon.class);
				out.add(Sword.class);
				out.add(GreatSword.class);
				out.add(WarAxe.class);
				out.add(Bow.class);
				out.add(Warhammer.class);
				out.add(Mace.class);
			}
			else{
				if(oneSwordCheck.isSelected()){
					out.add(Sword.class);
				}
				if(twoSwordCheck.isSelected()){
					out.add(GreatSword.class);
				}
				if(axeCheck.isSelected()){
					out.add(WarAxe.class);
				}
				if(bowsCheck.isSelected()){
					out.add(Bow.class);
				}
				if(hammersCheck.isSelected()){
					out.add(Warhammer.class);
				}
				if(macesCheck.isSelected()){
					out.add(Mace.class);
				}
			}
			if(potionsCheck.isSelected()){
				out.add(PotionPoison.class);
				out.add(PotionRestore.class);
				out.add(PotionFortify.class);
			}
			else{
				if(poisonCheck.isSelected()){
					out.add(PotionPoison.class);
				}
				if(restoreCheck.isSelected()){
					out.add(PotionRestore.class);
				}
				if(fortifyCheck.isSelected()){
					out.add(PotionFortify.class);
				}
			}
			if(foodCheck.isSelected()){
				out.add(Food.class);
			}
			if(booksCheck.isSelected()){
				out.add(Book.class);
			}
		}
		
		return out;
	}
	
	/*public ArrayList<Object> retrieveSorts(){
		ArrayList<>
	}*/
}

	
