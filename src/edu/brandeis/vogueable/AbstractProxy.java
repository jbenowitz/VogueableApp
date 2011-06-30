package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * This is an abstract class for the proxy for the server for Vogueable; 
 * @author Yulia
 *
 */
public abstract class AbstractProxy {
	User curruser;// user currently using app
	
	
	public AbstractProxy(){
		//curruser = new User("ananoymous");
	}
	/**
	 * connects to web service
	 */
	public void connect(){
		
	}
	
	/**
	 * disconnectes from webservice
	 */
	public void disconnect(){
		
	}
	
	/***
	 * Checks if user is in out database
	 * @param email - user's email- hopefully provided by Android; 
	 * @return
	 */
	public boolean isKnownUser(String email){
		return false; 
	}
	
	/**
	 * Creates a User Entry, if we don't yet have that user' 
	 * @param email user's email- hopefully provided by Android; 
	 */
	public void createUser(String email){
		
	}
	
	/**
	 * Returns user object
	 * @param email - user's email- hopefully provided by Android; 
	 * @return Returns user object instantiated with data from webservice 
	 */
	public User getUser(String email){
		User user = new User("yoooser@mail.ru", null);
		return user; 
	}
	
	/**
	 * Returns Item that has the most tags out of the give list of tags; 
	 * @param currentitem - item user is currently looking at (we need to not show the same item)
	 * @param tags - list of tags  (tags with the highest counts from the user's TasteManager)
	 * @return an Item that has the most tags out of the give list of tags; 
	 */
	public Item getNextItem(Item currentitem, ArrayList<String> tags){
		Item nextit = new Item("next");
		return nextit; 
	}
	
	/**
	 * Updates the TasteManager table; It looks at each tag for the user, 
	 * and if the counts in the local TasteManager are not the same as the webserver ones - chagnes them; 
	 * @param taste
	 */
	public void updateTasteManager(TasteManager taste){
		
	}
	
	/**
	 * 
	 * @param email - user's email
	 * @return TasteManager item for user, generated from TasteManager table; 
	 */
	public TasteManager getTasteManager(String email){
		TasteManager tastes = null;
		return tastes; 
	}
	
	/**
	 * 
	 * @param email - user's email; 
	 * @return Wishlist item generated from Wishlist Table 
	 */
	public ArrayList<Item> getWishlistforUser(String email){
		ArrayList<Item> wishlist = new ArrayList<Item>();
		return wishlist; 
	}
	/**
	 * Updates wishlist on server for user; 
	 * @param wishlist - Wishlsit for User
	 */
	public void updateWishList(Wishlist wishlist){
		
	}
	
	
}
