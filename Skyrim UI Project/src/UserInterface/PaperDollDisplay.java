package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Player.Player;
import items.*;

public class PaperDollDisplay extends JPanel{
	JLabel picture;
	private JLayeredPane layer;
	
	public PaperDollDisplay(){
		super(new BorderLayout());
		setBackground(Color.blue);
	}
	
	public void update() {
		this.removeAll();
		Player player = Constants.player;
		String image = "character_";
		if (player.getBody() != null && player.getBody() instanceof BodyArmor)
			image+="A";
		if (player.getFeet() != null && player.getFeet() instanceof Boots)
			image+="B";
		if (player.getHands() != null && player.getHands() instanceof Gauntlets)
			image+="G";
		if (player.getHead() != null && player.getHead() instanceof Helmet)
			image+="H";
		if (player.getWeapon() != null){
			image+="S";
		}
		image+=".png";
		try {
			Image i = ImageIO.read(new File("Skyrim Art/" + image));
			double scale = Math.min(getWidth()/402.0, getHeight()/952.0);
			layer = new JLayeredPane();
			picture = new JLabel(new ImageIcon((i.getScaledInstance((int) Math.floor(402*scale), (int) Math.floor(952*scale), Image.SCALE_SMOOTH))),SwingConstants.CENTER);
			layer.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
			add(layer);
			layer.setVisible(true);
			layer.setOpaque(false);
			layer.add(picture, JLayeredPane.DEFAULT_LAYER);
			
			picture.setBounds((int)(getWidth()-402*scale)/2, 0,(int) Math.floor(402*scale), (int) Math.floor(952*scale));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.revalidate();
		this.repaint();
	}
}
