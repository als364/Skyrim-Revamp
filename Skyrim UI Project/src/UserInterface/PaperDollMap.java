package UserInterface;

import items.BodyArmor;
import items.Boots;
import items.Gauntlets;
import items.Helmet;
import items.Item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Player.EquipLogic;
import Player.Player;

public class PaperDollMap extends JPanel{
	JLabel picture;
	private JLayeredPane layer;
	Item head;
	Item body;
	Item feet;
	Item hands;
	Item weapon;
	
	
	public PaperDollMap(){
		super(new BorderLayout());
		setBackground(Color.red);
	}
	
	public void update() {
		this.removeAll();
		try {
			Image i = ImageIO.read(new File("Skyrim Art/character_.png"));
			double scale = Math.min(getWidth()/402.0, getHeight()/952.0);
			layer = new JLayeredPane();
			picture = new JLabel(new ImageIcon((i.getScaledInstance((int) Math.floor(402*scale), (int) Math.floor(952*scale), Image.SCALE_SMOOTH))),SwingConstants.CENTER);
			layer.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
			add(layer);
			layer.setVisible(true);
			layer.setOpaque(false);
			layer.add(picture, -1, 0);
			picture.setBounds((int)(getWidth()-402*scale)/2, 0,(int) Math.floor(402*scale), (int) Math.floor(952*scale));
			int x = (int)(getWidth()-402*scale)/2;
			int height = (int) Math.floor(952*scale);
			int width = (int) Math.floor(402*scale);
			
			body = Constants.player.getBody();
			head = Constants.player.getHead();
			feet = Constants.player.getFeet();
			hands = Constants.player.getHands();
			weapon = Constants.player.getWeapon();
			int totalLayers = 0;
			
			/////////////////////////////////////////////////////////////////
			// TO DO : GET LAYERS IN ALL OF THESE FUNCTIONS WORKING PROPERLY
			/////////////////////////////////////////////////////////////////
			if (head != null) {
				JLabel headPicture = new JLabel(new ImageIcon(head.getImage().getScaledInstance((int) Math.floor(140*scale), (int) Math.floor(140*scale), Image.SCALE_SMOOTH)));
				layer.add(headPicture, 0);
				headPicture.setBounds(width/2 - (int) Math.floor(70*scale) + x,0,(int) Math.floor(140*scale), (int) Math.floor(140*scale));
				headPicture.setOpaque(true);
				totalLayers++;
			}
			
			if (body != null) {
				JLabel bodyPicture = new JLabel(new ImageIcon(body.getImage().getScaledInstance((int) Math.floor(140*scale), (int) Math.floor(140*scale), Image.SCALE_SMOOTH)));
				layer.add(bodyPicture, 0);
				bodyPicture.setBounds(width/2 - (int) Math.floor(70*scale) + x,height/3 - (int) Math.floor(70*scale),(int) Math.floor(140*scale), (int) Math.floor(140*scale));
				bodyPicture.setOpaque(true);
				totalLayers++;
			}
			
			if (hands != null) {
				JLabel handsPicture = new JLabel(new ImageIcon(hands.getImage().getScaledInstance((int) Math.floor(140*scale), (int) Math.floor(140*scale), Image.SCALE_SMOOTH)));
				layer.add(handsPicture, 0);
				handsPicture.setBounds(x + width - (int) Math.floor(140*scale),height/2 - (int) Math.floor(70*scale),(int) Math.floor(140*scale), (int) Math.floor(140*scale));
				handsPicture.setOpaque(true);
				totalLayers++;
			}
			
			if (weapon != null) {
				JLabel weaponPicture = new JLabel(new ImageIcon(weapon.getImage().getScaledInstance((int) Math.floor(140*scale), (int) Math.floor(140*scale), Image.SCALE_SMOOTH)));
				layer.add(weaponPicture, 0);
				weaponPicture.setBounds(x,height/2 - (int) Math.floor(70*scale),(int) Math.floor(140*scale), (int) Math.floor(140*scale));
				weaponPicture.setOpaque(true);
				totalLayers++;
			}
			
			if (feet != null) {
				JLabel feetPicture = new JLabel(new ImageIcon(feet.getImage().getScaledInstance((int) Math.floor(140*scale), (int) Math.floor(140*scale), Image.SCALE_SMOOTH)));
				layer.add(feetPicture, 0);
				feetPicture.setBounds(width/2 - (int) Math.floor(70*scale) + x,height*3/4 - (int) Math.floor(140*scale),(int) Math.floor(140*scale), (int) Math.floor(140*scale));
				feetPicture.setOpaque(true);
				totalLayers++;
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.revalidate();
		this.repaint();
	}
}
