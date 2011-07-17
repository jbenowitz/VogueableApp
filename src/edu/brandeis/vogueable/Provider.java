package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.content.Context;

public class Provider {
	private User user;
	private Item curritem;
	private TasteManager usertaste;
	private static Provider provider = null;
	private ArrayList<String> cats; 
	private Context context;
	
	
	private Provider(RealProxy proxy, String username, Context con, String item){
		
		user = new User(username);
		user.setTasteManager(usertaste);
	    curritem = new Item(item);
	    cats = new ArrayList<String>();
	    context = con;
	    try {
			usertaste = new TasteManager(proxy,cats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	/**
	 * 
	 * @return - current item displayed; 
	 */
	public Item getCurItem(){
		return curritem;
	}
	/**
	 * Sets the current item to be displayed
	 * @param it - the Item that will be currently displayed; 
	 */
	public void setCurItem(Item it){
		curritem = it;
	}
	
	public User getCurUser(){
		return user;
	}
	
	public TasteManager getCurTM(){
		return usertaste; 
	}
	
	public void setAcat(String cat){
		 cats.add(cat);
	}
	public void clearCatList(){
		 cats = new ArrayList<String>();
	}
	public ArrayList<String> getCatList(){
		 return cats;
	}
	
	
	public static synchronized Provider instance(RealProxy proxy, String username, Context con, String item) {
		if (provider == null){
			provider = new Provider(proxy, username, con, item);
		}
		provider.context = con;
		return provider;
	}

}
