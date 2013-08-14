package UserInterface;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Player.Player;

public class HotKeyPanel extends JPanel{
	JLabel textLabel = new JLabel();
	
	public HotKeyPanel(){
		
		
		add(textLabel);
		textLabel.setBounds(0, 0, this.WIDTH, this.HEIGHT);
		
		refreshHotKeys();
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		textLabel.setVerticalAlignment(JLabel.TOP);
	}
	
	public void refreshHotKeys(){
		String labelText =   "<html><div style=\"text-align: left;\"><h3>HotKeys</h3> ";
		for(int i = 0; i < Player.getHotKeys().length; i++){
			labelText += "<p align='left'><font size='5' color='red'>" + i + "</font>.  ";
			if(Player.getHotKeys()[i] != null){
				if(Player.getHotKeys()[i].getItem().getName().length() > 20){
					labelText += Player.getHotKeys()[i].getItem().getName().substring(0, 19) + "...</p>";
				}
				else{
					labelText += Player.getHotKeys()[i].getItem().getName() + "</p>";
				}
			}
			else{
				labelText+= "</p>";
			}
		}
		labelText += "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>";
		labelText += "</div></html>";
		textLabel.setText(labelText);
	}
}
