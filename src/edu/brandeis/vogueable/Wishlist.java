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
	private ArrayList<Item> wishlist;
	private String name; 
	
	
	/**
	 * Constructor to WishList.  
	 * takes in a name for wishlist (like christmas or hannukkah for future)
	 * @param Name
	 */
	public Wishlist(String Name){
		wishlist = new ArrayList<Item>();
		name = Name;
	}
	
	
	/**
	 * Adds an item to the wishlist
	 * @param itemA item to be added
	 */
	public void addItem(Item itemA){
		if (!wishlist.contains(itemA))
		wishlist.add(0,itemA);
	}
	
	
	/**
	 * removes an item from the wishlist
	 * @param itemR - removable item
	 */
	public void remove(Item itemR){
		if (wishlist.contains(itemR)){
			wishlist.remove(itemR);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Item> showWishlist(){
		return wishlist;
	}
	
	
}
