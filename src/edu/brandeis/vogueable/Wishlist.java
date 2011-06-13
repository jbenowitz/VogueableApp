package edu.brandeis.vogueable;

import java.util.ArrayList;

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
