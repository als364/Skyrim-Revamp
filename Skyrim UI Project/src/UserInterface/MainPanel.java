package UserInterface;



import items.Item;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import static UserInterface.Constants.*;

public class MainPanel extends JDesktopPane{
	
	public JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	
	
	private JButton favoritesButton = new JButton("Favorites");
	
	public static JScrollPane inventoryScroll;
	
	private JPanel leftSplit = new JPanel(new BorderLayout());
	private JPanel rightSplit = new JPanel(new BorderLayout());
	
	//Main Panels
	private static InventoryGrid inventoryG;
	private static PaperDollDisplay paperDisplay = new PaperDollDisplay();
	public static PaperDollMap paperMap = new PaperDollMap();
	private static ThreeDView tdView = new ThreeDView();
	private static ComparisonPanel compView = new ComparisonPanel();
	private static SortPanel mainSort;
	
	private DragItem currentDrag;
	public Item currentItem;

	
	public MainPanel(int width, int height){
		inventoryG = new InventoryGrid(this);
		mainSort = new SortPanel(inventoryG);
		
		setSize(width, height);
		
		//Setup favorites button
        favoritesButton.setBounds(monitorWidth - 100, 0, 100, 30);
        favoritesButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Launcher.showFavorites();
			}
        });
        this.add(favoritesButton, JLayeredPane.DRAG_LAYER);
        
        //Set up components right side of split pane
        setupRightPanel();
        
          
        
        inventoryScroll = new JScrollPane(inventoryG);
        leftSplit.setLayout(new BorderLayout());
        leftSplit.add(mainSort, BorderLayout.NORTH);

        
       
        inventoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //add(mainSort);
        //mainSort.setBounds(0, 0, (int)(getWidth() * mainSplitPercent) - 20, 20);
      
        leftSplit.add(inventoryScroll);
        setLeftSplitSizes();
        
        
        //Setup split pane
        splitPane.add(leftSplit);
        splitPane.add(rightSplit);
        splitPane.setBounds(0, 0, this.getWidth(), this.getHeight() - 50);
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				//HERE IS WHERE PANELS ARE RESIZED ACCORDING TO SPLITPANE
				leftSplit.setSize(monitorWidth - (monitorWidth - splitPane.getDividerLocation()), monitorHeight);
				setRightSplitSizes();
				rightSplit.setSize(monitorWidth - splitPane.getDividerLocation(), monitorHeight);
				
				setLeftSplitSizes();
				setRightSplitSizes();
				
				mainSort.setSize(splitPane.getDividerLocation() - 18, 10);
			}

			
        });
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(monitorWidth * mainSplitPercent / monitorWidth);
        
        this.add(splitPane, JLayeredPane.DEFAULT_LAYER);
	}


	/**
	 * Sets up component on right side of splitpane
	 */
	private void setupRightPanel() {
		rightSplit.setBounds(0, 0, monitorWidth - splitPane.getDividerLocation(), monitorHeight);
		
		rightSplit.add(paperDisplay);
		rightSplit.add(paperMap);
		rightSplit.add(tdView);
		rightSplit.add(compView);
		//Put in to make compView completely visible, some bug
		rightSplit.add(new JLabel(""));
		
		setRightSplitSizes();
	}


	private void setRightSplitSizes() {
		paperDisplay.setBounds(0, 0, 
				(int)(rightSplit.getWidth() * rightSplitPercent), (int)(monitorHeight - (monitorHeight * paperDividerHeightPercentage)));
		paperMap.setBounds(0, rightSplit.getHeight() - (int)(rightSplit.getHeight() * paperDividerHeightPercentage), 
				(int)(rightSplit.getWidth() * rightSplitPercent), (int)(monitorHeight * paperDividerHeightPercentage));
		tdView.setBounds((int)(rightSplit.getWidth() * rightSplitPercent), 0, 
				(int)(rightSplit.getWidth() * rightSplitPercent), (int)(monitorHeight - (monitorHeight * compare3dDividerHeightPercentage)));
		compView.setBounds((int)(rightSplit.getWidth() * rightSplitPercent), rightSplit.getHeight() - (int)(rightSplit.getHeight() * compare3dDividerHeightPercentage),  
				(int)(rightSplit.getWidth() * rightSplitPercent), (int)(monitorHeight * compare3dDividerHeightPercentage));
		
	}
	
	private void setLeftSplitSizes() {
		inventoryG.setBackground(Color.pink);
	    inventoryG.setBounds(0, 0, monitorWidth - (monitorWidth -  splitPane.getDividerLocation()), monitorHeight);
		
	    inventoryG.sortList();
	}


	public void createDragable(GridElement instance, int xOnScreen, int yOnScreen) {
		System.out.println("creating draggable main");
		DragItem di = new DragItem(instance);
		currentItem = instance.i;
		this.add(di, JLayeredPane.DRAG_LAYER);
		di.setLocation((int)( xOnScreen - this.getLocationOnScreen().getX() - 37),
				(int)( yOnScreen - this.getLocationOnScreen().getY() - 37));
		currentDrag = di;
	}
	
	
	public void removeDragable(GridElement instance){
		if(currentDrag != null){
			System.out.println("remove drag");
			this.remove(currentDrag);
			currentDrag = null;
			currentItem = null;
			this.repaint();
		}
	}

	public void setDragPos(Point point) {
		if(currentDrag != null){
			currentDrag.setLocation((int)( point.getX() - this.getLocationOnScreen().getX() - 37),
					(int)( point.getY() - this.getLocationOnScreen().getY() - 37));
		}
	}


	public void setDragPos(int xOnScreen, int yOnScreen) {
		if(currentDrag != null){
			currentDrag.setLocation((int)( xOnScreen - this.getLocationOnScreen().getX() - 37),
					(int)( yOnScreen - this.getLocationOnScreen().getY() - 37));
		}
	}
	
	public SortPanel getSortPanel(){
		return mainSort;
	}
	
	public InventoryGrid getInventoryGrid(){
		return inventoryG;
	}
	
	public ComparisonPanel getComparisonPanel() {
		return compView;
	}
	
	public void updatePanels() {
		compView.update();
		tdView.update();
		paperDisplay.update();
		paperMap.update();
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
