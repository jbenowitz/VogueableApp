package edu.brandeis.vogueable;

import android.content.Context;

public class Provider {
	private User user;
	private Item curritem;
	private TasteManager usertaste;
	private static Provider provider = null; 
	
	
	private Provider(RealProxy proxy, String username, Context con, String item){
		try {
			usertaste = new TasteManager(con,proxy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user = new User(username);
		user.setTasteManager(usertaste);
	    curritem = new Item(item);
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
	
	
	public static synchronized Provider instance(RealProxy proxy, String username, Context con, String item) {
		if (provider == null){
			provider = new Provider(proxy, username, con, item);
	}
		return provider;
	}

}
