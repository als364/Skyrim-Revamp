package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static UserInterface.Constants.*;

public class FavoritesPanel extends JFrame {

	private static InventoryGrid favInventory;
	private static JScrollPane inventoryScroll;
	private static SortPanel favSort;
	private static HotKeyPanel favHotKeys;
	private static ComparisonPanel favCompare;
	
	
	public FavoritesPanel(){
		
	}
	
	public void init(){
		favInventory = new InventoryGrid(this);
		favSort = new SortPanel(favInventory);
		add(favSort, BorderLayout.NORTH);
		
		favHotKeys = new HotKeyPanel();
		favCompare = new ComparisonPanel();
		
		inventoryScroll = new JScrollPane(favInventory);
		 inventoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 setResizable(false);
		 
		add(inventoryScroll);
		add(favHotKeys);
		add(favCompare);
		add(new JPanel());
		
		
		inventoryScroll.setBounds(0, 23, (int)(this.getWidth() * leftRightDivider), this.getHeight());
		favInventory.setBounds(0, 23, (int)(this.getWidth() * leftRightDivider), this.getHeight());
		favHotKeys.setBounds((int)(this.getWidth() * leftRightDivider) + 5, 23, 
				this.getWidth() - (int)(this.getWidth() * leftRightDivider),
				(int)(this.getHeight() * topBottomDivider));
		favCompare.setBounds((int)(this.getWidth() * leftRightDivider),
				(int)(this.getHeight() * topBottomDivider), 
				this.getWidth() - (int)(this.getWidth() * leftRightDivider),
				this.getHeight() - (int)(this.getHeight() * topBottomDivider));
		
		favInventory.setBackground(Color.pink);
		favInventory.sortList();
		System.out.println("FAV SIZE " + favInventory.getAllGridElements().size() + " " + favInventory.getCurrentList().size());
		
		this.repaint();
	}
	
	public SortPanel getSortPanel(){
		return favSort;
	}
	
	public HotKeyPanel getHotKeyPanel(){
		return favHotKeys;
	}
	
	public InventoryGrid getInventoryGrid(){
		return favInventory;
	}
	
	public void updatePanels() {
		favCompare.update();
	}

	public void setScrollLoc(int curRow, KeyEvent arg0) {
		if((curRow * gridElementHeight < (inventoryScroll.getVerticalScrollBar().getValue() + 110) && 
				arg0.getKeyCode() == KeyEvent.VK_UP)){
			inventoryScroll.getVerticalScrollBar().setValue(curRow * gridElementHeight - gridElementHeight);
		}
		else if( (curRow * gridElementHeight > inventoryScroll.getVerticalScrollBar().getValue() + inventoryScroll.getHeight() - 100) 
				&& arg0.getKeyCode() == KeyEvent.VK_DOWN){
			inventoryScroll.getVerticalScrollBar().setValue(curRow * gridElementHeight + gridElementHeight);
		}
		
		System.out.println("SCROLL " + inventoryScroll.getVerticalScrollBar().getMaximum()
				+ " " + inventoryScroll.getVerticalScrollBar().getMinimum());
	}
}
