package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.content.Context;
/**
 * User class keeps track of a user's taste and wishlist
 * 
 * @author Yulia
 *
 */
public class User {
	public String name; 
	public Wishlist wishlist;
	public TasteManager mytaste;
	public Context con;
	
	
	/**
	 * Constructor to create a user
	 * 
	 * Initializes the name, 
	 * 
	 * @param myName- string name
	 * @param con- context to create a TasteManager
	 */
	public User(String myName, Context con){
		name = myName;
		wishlist = new Wishlist(null); //now initialized without a type
		mytaste = new TasteManager(con);
	}
	
	
	/**
	 * Name getter
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Wishlist getter
	 * 
	 * @return wishlist 
	 */
	public Wishlist getWishlists(){
		return wishlist;
	}
	
	
	/**
	 * Adds a wishlist item
	 * 
	 * @param itemA (an item for the wishlist)
	 */
	public void addWishlist(Item itemA){
		wishlist.addItem(itemA);
	}
	
	
	/**
	 * removes an item from the wishlist
	 * 
	 * @param itemR (an item to be removed)
	 */
	public void removeWishlist(Item itemR){
		wishlist.remove(itemR);
	}
	
	
	/**
	 * returns the tasteManager
	 * 
	 * @return mytaste TasteMangager type
	 */
	public TasteManager getTasteManager(){
		return mytaste;
	}
	
}
