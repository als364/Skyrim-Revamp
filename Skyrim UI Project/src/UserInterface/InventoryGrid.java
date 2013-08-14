package UserInterface;

import items.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Player.Player;
import Player.EquipLogic;

import static UserInterface.Constants.*;

public class InventoryGrid extends JPanel{

	private int elementWidth = 100;
	private int elementHeight = 80;
	
	
	ArrayList<GridElement> currList = new ArrayList<GridElement>();
	ArrayList<GridElement> allGridElements = new ArrayList<GridElement>();
	ArrayList<Item> currItemList = new ArrayList<Item>();
	
	private GridLayout gLayout = new GridLayout();
	public  GridElement focusElement;
	public Object parent;
	
	public GridElement[][] gridCoord;
	public int focusRow;
	public int focusCol;
	
	public InventoryGrid(final Object parent){
		this.parent = parent;

		setLayout(new BorderLayout());
		
		//if(parent instanceof MainPanel){
			for(Item i : player.getInventory()){
				addElement(i);
			}
			allGridElements.addAll(currList);
		/*} else{
			for(GridElement ge : player.getFavorites()){
				add(ge);
				currList.add(ge);
			}
		}*/
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher(){

			@Override
			public boolean dispatchKeyEvent(KeyEvent arg0) {
				if(parent == Launcher.getFavPanel() && Launcher.getFavPanel().isVisible()){
					handleKeys(arg0);
				} else if(parent == Launcher.getMainPanel() && !Launcher.getFavPanel().isVisible()){
					handleKeys(arg0);
				}
				
	            return false;
			}
        	
        });

	}
	
	public void handleKeys(KeyEvent arg0){
		if (arg0.getID() == KeyEvent.KEY_PRESSED) {
			if(focusElement != null){
				System.out.println("focus element not null");
				int curRow = focusElement.getRow();
				int curCol = focusElement.getCol();
				
				/*System.out.println("GRID COORD SIZE " + gridCoord.length);
				//GRID COORD PRINTOUT
				for(int i = 0; i < gridCoord.length; i++){
					for(int j = 0; j < gridCoord[i].length; j++){
						if(i * gLayout.getColumns() + j < currList.size()){
							System.out.println("GRID: " + i + " " + j + " " + gridCoord[i][j].getItem().getName());
						}
					}
				}
				System.out.println("CURRENT COORD " + curRow + " " + curCol);*/
				
				try{
					if (arg0.getKeyCode() == KeyEvent.VK_UP) {
						if(gridCoord[curRow - 1][curCol] != null){
							gridCoord[curRow - 1][curCol].setSelect(true);
							System.out.println(gridCoord[curRow - 1][curCol].getItem().getName());
						}
						System.out.println("UP");
					}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
						if(gridCoord[curRow + 1][curCol] != null){
							gridCoord[curRow + 1][curCol].setSelect(true);
							System.out.println(gridCoord[curRow + 1][curCol].getItem().getName());
						}
						System.out.println("DOWN");
					}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
						if(gridCoord[curRow][curCol - 1] != null){
							gridCoord[curRow][curCol - 1].setSelect(true);
							System.out.println(gridCoord[curRow][curCol - 1].getItem().getName());
						}
						System.out.println("LEFT");
					} else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
						if(gridCoord[curRow][curCol + 1] != null){
							gridCoord[curRow][curCol + 1].setSelect(true);
							System.out.println(gridCoord[curRow][curCol + 1].getItem().getName());
						}
						System.out.println("RIGHT");
					} else if(arg0.getKeyCode() == KeyEvent.VK_F){
						gridCoord[curRow][curCol].toggleFavorite();
						Launcher.updateElementConsistency(parent, gridCoord[curRow][curCol]);
						if(parent == Launcher.getFavPanel()){
							System.out.println("REMOVING FAVORITE");
							sortList();
						}
						System.out.println("PRESSED F");
					} else if(arg0.getKeyCode() >= KeyEvent.VK_0 && arg0.getKeyCode() <= KeyEvent.VK_9){
						gridCoord[curRow][curCol].setHotkey(arg0.getKeyChar());
						Launcher.updateElementConsistency(parent, gridCoord[curRow][curCol]);
						System.out.println("PRESSED " + arg0.getKeyChar());
					} else if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
						//EQUIP ITEM
						gridCoord[curRow][curCol].setEquip(EquipLogic.equip(gridCoord[curRow][curCol].getItem(), player));
						Launcher.updateElementConsistency(parent, gridCoord[curRow][curCol]);
					}
				} catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Array index out of bounds");
				}
				
				if(parent instanceof MainPanel){
					Launcher.getMainPanel().setScrollLoc(curRow, arg0);
				} else{
					Launcher.getFavPanel().setScrollLoc(curRow, arg0);
				}
			}
			Launcher.getMainPanel().updatePanels();
        } else if (arg0.getID() == KeyEvent.KEY_RELEASED) {
        } else if (arg0.getID() == KeyEvent.KEY_TYPED) {
        }
	}

	public void addElement(Item i){
		GridElement ge = new GridElement(i, this);
		add(ge);
		currList.add(ge);
		
	}
	
	public void sortList() {

		ArrayList<Object> genericList = null;
		ArrayList<Item> out = null;
		
		if(parent instanceof MainPanel){
			genericList = ((MainPanel) parent).getSortPanel().retrieveCats();
			out = DataManagement.getInventorySubset(player.getInventory(), genericList);
			out = DataManagement.alphaSortedList(out);	
			if(((MainPanel) parent).getSortPanel().getCurrentSort() != -1){
				out = DataManagement.getSortedList(out, ((MainPanel) parent).getSortPanel().getCurrentSort());
			}
		}
		else{
			genericList = Launcher.getFavPanel().getSortPanel().retrieveCats();
			out = DataManagement.getInventorySubset(player.getInventory(), genericList);
			out = DataManagement.alphaSortedList(out);	
			if(Launcher.getFavPanel().getSortPanel().getCurrentSort() != -1){
				out = DataManagement.getSortedList(out, Launcher.getFavPanel().getSortPanel().getCurrentSort());
			}
		}
		
		
		for(GridElement ge: currList){
			ge.setVisible(false);
		}
		
		currList.clear();
		System.out.println("OUT SIZE " + out.size());
		for(Item i : out){
			GridElement newGE = getElementFromItem(i);
			if(parent instanceof MainPanel){
				add(newGE);
				currList.add(newGE);
				newGE.setVisible(true);
			}
			//For fav panel, ensure that this GE is favorited
			else if(newGE.isFavorite()){
				add(newGE);
				currList.add(newGE);
				newGE.hideFavIcon();
				newGE.setVisible(true);
			}
		}
		
		System.out.println("RESULTANT SIZE " + currList.size());
		refreshLayout();
		
	}
	
	public Object getParentPanel(){
		return parent;
	}
	
	public ArrayList<GridElement> getAllGridElements(){
		return allGridElements;
	}
	
	private GridElement getElementFromItem(Item i){
		for(GridElement ge: allGridElements){
			if(ge.getItem() == i){
				return ge;
			}
		}
		return null;
	}

	private ArrayList<Item> getCurrentListAsItems() {
		ArrayList<Item> out = new ArrayList<Item>();
		
		for(GridElement ge: getCurrentList()){
			out.add(ge.getItem());
		}
		
		return out;
	}


	public void refreshLayout(){
		
		//setLayout(gLayout);
		
		ArrayList<GridElement> tempList = new ArrayList<GridElement>();
		
		//if(parent instanceof MainPanel){
			tempList.addAll(currList); 
		/*} else{
			for(GridElement ge : currList){
				if(player.getFavorites().contains(ge)){
					System.out.println(this );
					tempList.add(ge);
				}
			}
		}*/
		
		gLayout = determineLayout(tempList);
		
		int itemIndex = 0;
		
		gridCoord = new GridElement[gLayout.getRows()][gLayout.getColumns()]; 
		
		
		for(int r = 0; r < gLayout.getRows(); r++){
			for(int c = 0; c < gLayout.getColumns(); c++){
				if(itemIndex < currList.size()){
					//System.out.println("SETTING " + currList.get(itemIndex).getItem().getName() + " at r:" + r + " c:" + c);
					currList.get(itemIndex).setBounds(c * gridElementWidth, r * gridElementHeight, 
							gridElementWidth, gridElementHeight);
					gridCoord[r][c] = currList.get(itemIndex);
					currList.get(itemIndex).setCoord(r, c);
					currList.get(itemIndex).setVisible(true);
					itemIndex++;
				}
			}
		}
		
		if(currList.size() > 0){
			//System.out.println("GRID COORD SIZE AFTER INIT " + gridCoord.length + " " + gLayout.getRows() 
				//	+ " " + gLayout.getColumns() + " " + currList.size());
			
			//GRID COORD PRINTOUT
			/*for(int i = 0; i < gridCoord.length; i++){
				for(int j = 0; j < gridCoord[i].length; j++){
					if(i * gLayout.getColumns() + j < currList.size()){
						System.out.println("GRID: " + i + " " + j + " " + gridCoord[i][j].getItem().getName());
					}
				}
			}*/
		}
		
		System.out.println("");
		
		add(new JLabel(""));
		
		this.setPreferredSize(new Dimension(gLayout.getColumns() * gridElementWidth, gLayout.getRows() * gridElementHeight));
		revalidate();
		this.repaint();
		
		//this.setSize(gLayout.getColumns() * gridElementWidth, gLayout.getRows() * gridElementHeight);
		//System.out.println("width " + this.getWidth() + " height " + this.getHeight());
	}
	
	public GridElement[][] getGridCoords(){
		return gridCoord;
	}
	
	public GridLayout getGridLayout(){
		return gLayout;
	}
	
	private GridLayout determineLayout(ArrayList<GridElement> in){
		GridLayout gl = new GridLayout();
		
		if(this.getWidth() > 0){
			int numCols = this.getWidth() / (elementWidth + 5); 
			
			gl.setColumns(numCols);
			
			int numRows;
			if(numCols > 0){
				numRows = (in.size() / numCols) + 1;
			}
			else{
				numRows = 0;
			}
			
			gl.setRows(numRows);
			System.out.println("cols " + numCols + " " + numRows);
		}
		
		return gl;
	}
	
	public ArrayList<GridElement> getCurrentList(){
		return currList;
	}
	
	public void setCurrentList(ArrayList<GridElement> newList){
		currList = newList;
		refreshLayout();
	}

	public void setSelectedElement(GridElement gridElement) {
		if(focusElement != null){
			focusElement.setSelect(false);
		}
		
		focusElement = gridElement;
	}

	public void createDragable(GridElement instance, int x, int y) {
		if(parent instanceof MainPanel){
			((MainPanel) parent).createDragable(instance, x, y);
		}
	}
	
	public void removeDragable(GridElement instance) {
		if(parent instanceof MainPanel){
			((MainPanel) parent).removeDragable(instance);
		}
	}


	public void setDragPos(Point point) {
		if(parent instanceof MainPanel){
			((MainPanel) parent).setDragPos(point);
		}
		
	}


	public void setDragPos(int xOnScreen, int yOnScreen) {
		if(parent instanceof MainPanel){
			((MainPanel) parent).setDragPos(xOnScreen, yOnScreen);
		}
	}
	
}
