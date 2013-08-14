package UserInterface;

import static UserInterface.Constants.gridElementHeight;
import static UserInterface.Constants.gridElementWidth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ThreeDView extends JPanel{
	JLabel picture;
	private JLayeredPane layer;
	
	public ThreeDView(){
		super(new BorderLayout());
		setBackground(Color.yellow);
		add(new JLabel("3d view"));
	}
	
	public void update() {
		this.removeAll();
		if (Launcher.getMainPanel().getInventoryGrid().focusElement != null) {
			int bound = Math.min(getWidth(), getHeight());
			layer = new JLayeredPane();
			picture = new JLabel(new ImageIcon((Launcher.getMainPanel().getInventoryGrid().focusElement.getItem().getImage().getScaledInstance(bound-30, bound-30, Image.SCALE_SMOOTH))));
			layer.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
			add(layer);
			layer.setVisible(true);
			layer.setOpaque(false);
			layer.add(picture, JLayeredPane.DEFAULT_LAYER);
			
			picture.setBounds(15, 15, bound-15, bound-15);
		}
		this.revalidate();
		this.repaint();
	}
}
