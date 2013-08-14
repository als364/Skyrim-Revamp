package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import Player.EquipLogic;

import items.Armor;
import items.Weapon;
import items.Item;

import static UserInterface.Constants.*;

public class GridElement extends JPanel{

	Item i;
	GridElement instance = this;
	JLabel picture;
	JLabel name;
	JLabel properties;
	public boolean selected = false;
	private InventoryGrid gridParent;
	Timer t = new Timer(-1, null);
	private boolean preventPopup = false;
	private boolean equiped = false;
	
	private boolean favorite = false;
	private JLabel favIcon;
	private JLabel hotKeyIcon;
	private JLabel equipIcon;
	
	private JLayeredPane layer = new JLayeredPane();
	
	private String hotKey = "";
	
	private int row;
	private int col;
	
	public GridElement(final Item i, InventoryGrid parent){
		this.i = i;
		instance = this;
		gridParent = parent;
		setSize(gridElementWidth, gridElementHeight);
		
		try {
			favIcon = new JLabel(new ImageIcon(ImageIO.read(new File("Images/fav.png"))));
			
			hotKeyIcon = new JLabel();
			hotKeyIcon.setVisible(true);
			
			equipIcon = new JLabel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		picture = new JLabel(new ImageIcon(i.getImage()));
		layer.setPreferredSize(new Dimension(gridElementWidth, gridElementHeight));
		add(layer);
		layer.setVisible(true);
		layer.setBackground(Color.yellow);
		layer.setOpaque(false);
		
		
		layer.add(picture, JLayeredPane.DEFAULT_LAYER);
		picture.setBounds(15, 0, 70, 70);
		layer.add(favIcon, JLayeredPane.DRAG_LAYER);
		favIcon.setBounds(this.getWidth() - 25, -5, 25, 25);
		favIcon.setVisible(false);
		layer.add(hotKeyIcon, JLayeredPane.DRAG_LAYER);
		hotKeyIcon.setBounds(1, -3, 25, 25);
		
		name = new JLabel(i.getName());
		layer.add(name, JLayeredPane.DEFAULT_LAYER);
		name.setBounds(2, 70, this.getWidth(), 10);
		
		properties = new JLabel();
		properties.setLayout(new FlowLayout());
		layer.add(properties, JLayeredPane.DRAG_LAYER);
		properties.setBounds(1, 75, this.getWidth(), 20);
		try {
			Font f = new Font(null, Font.PLAIN, 9);
			
			JLabel coinLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Images/coins.png"))));
			properties.add(coinLabel);
			coinLabel.setBounds(coinLabel.getX(), coinLabel.getY() - 100, 10, 10);
			
			JLabel coinAmtLabel = new JLabel(i.getValue() + "");
			coinAmtLabel.setFont(f);
			properties.add(coinAmtLabel);
			
			JLabel featherLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Images/feather.png"))));
			properties.add(featherLabel);
			featherLabel.setBounds(coinLabel.getX(), coinLabel.getY() - 100, 10, 10);
			
			JLabel featherAmtLabel = new JLabel(i.getWeight() + "");
			featherAmtLabel.setFont(f);
			properties.add(featherAmtLabel);
			
			
			if(i instanceof Armor){
				JLabel shieldLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Images/shield.png"))));
				properties.add(shieldLabel);
				shieldLabel.setBounds(coinLabel.getX(), coinLabel.getY() - 100, 10, 10);
				
				JLabel shieldAmtLabel = new JLabel(((Armor)i).getRating() + "");
				shieldAmtLabel.setFont(f);
				properties.add(shieldAmtLabel);
			}
			else if(i instanceof Weapon){
				JLabel swordLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Images/sword.png"))));
				properties.add(swordLabel);
				swordLabel.setBounds(coinLabel.getX(), coinLabel.getY() - 100, 10, 10);
				
				JLabel swordAmtLabel = new JLabel(((Weapon)i).getDamage() + "");
				swordAmtLabel.setFont(f);
				properties.add(swordAmtLabel);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.cyan);
		
		
		
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() > 1){
					setEquip(EquipLogic.equip(instance.getItem(), player));
					Launcher.updateElementConsistency(gridParent.getParentPanel(), instance);
				}
				else{
					if(!selected){
						setSelect(true);
					}
					else{
						setSelect(false);
						gridParent.setSelectedElement(null);
					}
				}
				Launcher.getMainPanel().updatePanels();
				if(Launcher.getFavPanel() != null)
					Launcher.getFavPanel().updatePanels();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(final MouseEvent arg0) {	
				int delay = 180; //milliseconds
				preventPopup = false;
				ActionListener taskPerformer = new ActionListener() {
				      public void actionPerformed(ActionEvent evt) {
				    	  if(!preventPopup && (gridParent.getParentPanel() == Launcher.getMainPanel())){
				    		  gridParent.createDragable(instance, arg0.getXOnScreen(), 
				    				  arg0.getYOnScreen());
				    		  picture.setVisible(false);
				    		  setSelect(true);
				    	  }
				      }
				  };
				  
				 t = new Timer(delay, taskPerformer);
				 t.setRepeats(false);
				 t.start();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				preventPopup = true;
				picture.setVisible(true);
				Point newPoint = new Point(instance.getLocation().x + arg0.getPoint().x - Launcher.getMainPanel().splitPane.getDividerLocation(), instance.getLocation().y + arg0.getPoint().y);
				if (Launcher.getMainPanel().paperMap.getBounds().contains(newPoint)) {
					EquipLogic.equip(Launcher.getMainPanel().currentItem, Constants.player);
					Launcher.getMainPanel().updatePanels();
				}
				gridParent.removeDragable(instance);
				System.out.println("mouse released");
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				gridParent.setDragPos(arg0.getXOnScreen(), arg0.getYOnScreen());
				//gridParent.setDragPos(arg0.getXOnScreen(), arg0.getYOnScreen());
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
			
		});
		
		this.setFocusable(true);
	}

	

	public Item getItem(){
		return i;
	}
	
	public void setSelect(boolean setting){
		if(setting){
			setBackground(Color.white);
			selected = true;
			gridParent.setSelectedElement(this);
		} else{
			setBackground(Color.cyan);
			selected = false;
		}
	}
	
	public void setCoord(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public void toggleFavorite(){
		if(favorite){
			favorite = false;
			favIcon.setVisible(false);
			this.repaint();
			player.removeFavorite(this);
		} else{
			favorite = true;
			//remove(favIcon);
			favIcon.setVisible(true);
			System.out.println("setting true");
			this.repaint();
			player.addFavorite(this);
		}
		
	}

	public boolean isFavorite(){
		return favorite;
	}

	public void setHotkey(char keyChar) {
		GridElement[] hotKeys = player.getHotKey();
		int key = Integer.parseInt(keyChar + "");
		
		try{
			if(hotKeys[key] == this){
				System.out.println("ELMINATING HOTKEY");
				hotKeys[key] = null;
				
				hotKey = "";
				hotKeyIcon.setIcon(null);
			} else if(hotKeys[key] != null){
				System.out.println("SHOULD BE OVERRIDING");
				hotKeys[key].overrideHotKey("");
				Launcher.updateElementConsistency(gridParent.getParentPanel(), hotKeys[key]);
				
				hotKeys[key] = this;
				
				
				hotKey = keyChar + "";
				hotKeyIcon.setIcon(new ImageIcon(ImageIO.read(new File("Images/red-number-" + keyChar + ".png"))));
			} else {
				System.out.println("NOTHING HERE");
				hotKeys[key] = this;
						
				hotKey = keyChar + "";
				hotKeyIcon.setIcon(new ImageIcon(ImageIO.read(new File("Images/red-number-" + keyChar + ".png"))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Launcher.updateElementConsistency(gridParent.getParentPanel(), this);
		Launcher.getFavPanel().getHotKeyPanel().refreshHotKeys();
		
		//*****************
		/*hotKey = keyChar + "";
		try {
			hotKeyIcon.setIcon(new ImageIcon(ImageIO.read(new File("Images/red-number-" + keyChar + ".png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.addHotKey(Integer.parseInt(keyChar + ""), this);*/
	}
	
	public String getHotKey(){
		return hotKey;
	}
	
	public void overrideHotKey(String keyChar){
		hotKey = keyChar + "";
		
		if(!hotKey.equals("")){
			try {
				hotKeyIcon.setIcon(new ImageIcon(ImageIO.read(new File("Images/red-number-" + keyChar + ".png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			hotKeyIcon.setIcon(null);
		}
	}
	
	public void removeHotkey(){
		hotKey = "";
		hotKeyIcon.setIcon(null);
	}

	public void hideFavIcon() {
		favIcon.setVisible(false);
		
	}
	
	public boolean isEquiped(){
		return equiped;
	}
	
	public void toggleEquip(){
		if(isEquiped()){
			equiped = false;
		} else{
			equiped = true;
		}
	}



	public void setEquip(boolean equip) {
		equiped = equip;
		
		if(equiped){
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		} else{
			setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
}
