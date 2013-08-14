package Player;

import java.util.ArrayList;

import UserInterface.*;

import items.*;

// Class to store all of the information about the 'player'
// Only stores data, doesn't manage it.
public class Player {
	protected final int PLAYER_MAXIMUM_WEIGHT = 370;
	
	private Equipment body;
	private Equipment head;
	private Equipment feet;
	private Equipment hands;
	private Equipment neck;
	private Equipment finger;
	private Equipment shield;
	private Weapon weapon;
	
	private ArrayList<Item> inventory;
	
	private ArrayList<GridElement> favorites = new ArrayList<GridElement>();
	
	private static GridElement[] hotKeys = new GridElement[10];
	
	
	public Player() {
		setBody(null);
		setHead(null);
		setFeet(null);
		setHands(null);
		setNeck(null);
		setFinger(null);
		setShield(null);
		setWeapon(null);
		inventory = new ArrayList<Item>();
	}
	
	private Player(Equipment body, Equipment head, Equipment feet, Equipment hands,
					Equipment shield) {
		setBody(body);
		setHead(head);
		setFeet(feet);
		setHands(hands);
		setNeck(null);
		setFinger(null);
		setShield(shield);
	}
	
	public int getTotalRating() {
		return body.getRating() + head.getRating() + feet.getRating()
				+ hands.getRating() + shield.getRating();
	}
	
	public static int getNewTotalRating(Equipment e, Player p) {
		Player temp = p.copy();
		EquipLogic.equipEquipment(e, temp);
		return temp.getTotalRating();
	}
	
	// Need this function for when we equip a two-handed weapon
	public static int getNewTotalRating(Weapon w, Player p) {
		Player temp = p.copy();
		EquipLogic.equipWeapon(w, temp);
		return temp.getTotalRating();
	}

	public Equipment getBody() {
		return body;
	}

	public boolean setBody(Equipment body) {
		if(this.body == body){
			this.body = null;
			return false;
		}
		if(this.body != null){
			Launcher.unequip(this.body);
		}
		this.body = body;
		return true;
	}

	public Equipment getHead() {
		return head;
	}

	public boolean setHead(Equipment head) {
		if(this.head == head){
			this.head = null;
			return false;
		}
		if(this.head != null){
			Launcher.unequip(this.head);
		}
		this.head = head;
		return true;
	}

	public Equipment getFeet() {
		return feet;
	}

	public boolean setFeet(Equipment feet) {
		if(this.feet == feet){
			this.feet = null;
			return false;
		}
		if(this.feet != null){
			Launcher.unequip(this.feet);
		}
		this.feet = feet;
		return true;
	}

	public Equipment getHands() {
		return hands;
	}

	public boolean setHands(Equipment hands) {
		if(this.hands == hands){
			this.hands = null;
			return false;
		}
		if(this.hands != null){
			Launcher.unequip(this.hands);
		}
		this.hands = hands;
		return true;
	}

	public Equipment getNeck() {
		return neck;
	}

	public boolean setNeck(Equipment neck) {
		if(this.neck == neck){
			this.neck = null;
			return false;
		}
		if(this.neck != null){
			Launcher.unequip(this.neck);
		}
		this.neck = neck;
		return true;
	}

	public Equipment getFinger() {
		return finger;
	}

	public boolean setFinger(Equipment finger) {
		if(this.finger == finger){
			this.finger = null;
			return false;
		}
		if(this.finger != null){
			Launcher.unequip(this.finger);
		}
		this.finger = finger;
		return true;
	}

	public Equipment getShield() {
		return shield;
	}

	public boolean setShield(Equipment shield) {
		if(this.shield == shield){
			this.shield = null;
			return false;
		}
		if(this.shield != null){
			Launcher.unequip(this.shield);
		}
		this.shield = shield;
		return true;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public boolean setWeapon(Weapon weapon) {
		if(this.weapon == weapon){
			this.weapon = null;
			return false;
		}
		if(this.weapon != null){
			Launcher.unequip(this.weapon);
		}
		this.weapon = weapon;
		return true;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void addToInventory(Item item) {
		inventory.add(item);
	}
	
	public void removeFromInventory(Item item) {
		int index = inventory.indexOf(item);
		if (index != -1) {
			inventory.remove(index);
		}
	}
	
	public Player copy() {
		return new Player(body,head,hands,feet,shield);
	}
	
	public void addHotKey(int key, GridElement ge){
		if(hotKeys[key] != null){
			hotKeys[key].removeHotkey();
			hotKeys[key] = null;
		} else{
			hotKeys[key] = ge;
		}
		Launcher.getFavPanel().getHotKeyPanel().refreshHotKeys();
	}
	
	public GridElement[] getHotKey(){
		return hotKeys;
	}
	
	public void addFavorite(GridElement ge){
		favorites.add(ge);
		//Launcher.getFavPanel().getInventoryGrid().getCurrentList().add(ge);
		System.out.println("check 1");
		Launcher.getFavPanel().getInventoryGrid().sortList();
	}
	
	public void removeFavorite(GridElement ge){
		favorites.remove(ge);
		//Launcher.getFavPanel().getInventoryGrid().getCurrentList().add(ge);
		//Launcher.getFavPanel().getInventoryGrid().sortList();
	}
	
	public ArrayList<GridElement> getFavorites(){
		return favorites;
	}
	
	public static GridElement[] getHotKeys(){
		return hotKeys;
	}
	
	public ArrayList<Item> getItemFavs(){
		ArrayList<Item> out = new ArrayList<Item>();
		for(GridElement ge : favorites){
			out.add(ge.getItem());
		}
		
		return out;
	}
	
}
