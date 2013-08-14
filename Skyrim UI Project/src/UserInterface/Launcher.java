package UserInterface;

import items.Equipment;
import items.Item;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JApplet;

import Player.Player;
import static UserInterface.Constants.*;

public class Launcher extends JApplet{
	

	GraphicsEnvironment ge;
    GraphicsDevice[] gs;
    GraphicsDevice monitor;
    
    static private MainPanel mainPanel;
    
    
	private static final long serialVersionUID = 1L;
	
	static Launcher instance;
	
	static Object lastClicked;
	
	static FavoritesPanel favPanel = new FavoritesPanel();
	
	public void init(){
		instance = this;
		
		player = new Player();
		
		InventoryFactory.loadInventory(player);
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gs = ge.getScreenDevices();

        monitor = gs[0];
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        monitorWidth = monitor.getDisplayMode().getWidth() - 20;
        monitorHeight = maxBounds.height;
        
        setSize(monitorWidth, monitorHeight);

        favPanel.setBounds((int)(Constants.monitorWidth * favPaddingPercent), (int)(monitorHeight * favPaddingPercent), 
        		monitorWidth - (int)(monitorWidth * favPaddingPercent * 2), 
        		monitorHeight - (int)(monitorHeight * favPaddingPercent * 2));
        favPanel.init();
        mainPanel = new MainPanel(monitorWidth, monitorHeight);
        add(mainPanel);
	}

	public static void showFavorites() {
		favPanel.setVisible(true);
	}

	public static FavoritesPanel getFavPanel(){
		return favPanel;
	}
	
	public static MainPanel getMainPanel(){
		return mainPanel;
	}

	public static void setLastClicked(Object o) {
		lastClicked = o;
	}
	
	public static Object getLastClicked(){
		return lastClicked;
	}

	public static void updateElementConsistency(Object parent, GridElement geIN) {
		GridElement toUpdate = null;
		InventoryGrid ig;
		
		if(parent == mainPanel){
			ig = favPanel.getInventoryGrid();
		} else {
			ig = mainPanel.getInventoryGrid();
		}
			
			for(GridElement ge: ig.getAllGridElements()){
				if(ge.getItem() == geIN.getItem()){
					toUpdate = ge;
				}
			}
			
			System.out.println("GEIN FAV " + geIN.getName() + " " + geIN.isFavorite());
			//Update Favorite consistency
			if((geIN.isFavorite() && !toUpdate.isFavorite()) 
					|| (!geIN.isFavorite() && toUpdate.isFavorite())){
				toUpdate.toggleFavorite();
			} 
			
			//Update hotkey consistency
			if(!geIN.getHotKey().equals(toUpdate.getHotKey())){
				toUpdate.overrideHotKey(geIN.getHotKey());
			}
			
			//Update equipment consistency
			if((geIN.isEquiped() && !toUpdate.isEquiped())
					|| (!geIN.isEquiped() && toUpdate.isEquiped())){
				toUpdate.setEquip(geIN.isEquiped());
			}
		
	}

	public static void unequip(Item i) {
		for(GridElement ge : mainPanel.getInventoryGrid().getAllGridElements()){
			if(ge.getItem() == i){
				ge.setEquip(false);
			}
		}
		for(GridElement ge : favPanel.getInventoryGrid().getAllGridElements()){
			if(ge.getItem() == i){
				ge.setEquip(false);
			}
		}
	}
}
