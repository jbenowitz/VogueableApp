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
	public ArrayList<Wishlist> MyLists;
	public TasteManager mytaste;
	public Context con;

	
	/*public User(User user){
		this.name = user.name;
		this.MyLists = new ArrayList<Wishlist>();
		this.MyLists=user.MyLists;
		this.mytaste=new TasteManager();
		this.mytaste= user.mytaste;
	}*/
	
	
	public User(String myName, Context con){
		name = myName;
		MyLists = new ArrayList<Wishlist>();
		mytaste = new TasteManager( con);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Wishlist> getWishlists(){
		return MyLists;
	}
	
	public void addWishlist(String wish){
		MyLists.add(new Wishlist(wish));
	}
	
	public void removeWishlist( String list){
		MyLists.remove(list);
	}
	
	public TasteManager getTasteManager(){
		return mytaste;
	}
	
}
