package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * This wishlist is the data structure for storing all the items
 * added to a user's wishlist.
 * 
 * @author Yulia
 *
 */
public class Wishlist {
	ArrayList<Item> wishlist;
	String name; 
	
	public Wishlist(String Name){
		wishlist = new ArrayList<Item>();
		name = Name;
	}
	
	public void addItem(Item like){
		wishlist.add(like);
	}
	
	public ArrayList<Item> showWishlist(){
		return wishlist;
	}
	
	public void remove(Item thing){
		wishlist.remove(thing);
	}
}
