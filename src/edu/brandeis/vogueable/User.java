package edu.brandeis.vogueable;

import java.util.ArrayList;

public class User {
	public String name; 
	public ArrayList<Wishlist> MyLists;
	public TasteManager mytaste;
	
	public User(String myName){
		name = myName;
		MyLists = new ArrayList<Wishlist>();
		mytaste = new TasteManager();
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
