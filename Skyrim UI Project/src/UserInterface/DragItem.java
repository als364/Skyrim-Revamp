package UserInterface;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class DragItem extends JInternalFrame{

	public DragItem(GridElement ge){
		this.setSize(70, 70);
		this.setVisible(true);
		this.setEnabled(true);
		JLabel picture = new JLabel(new ImageIcon(ge.getItem().getImage()));
		this.add(picture);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	}
}
