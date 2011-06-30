package edu.brandeis.vogueable;

import android.content.Context;

public class Provider {
	private User user;
	private Item curritem;
	private static Provider provider; 
	
	private Provider(){
		user = new User(null, null);	
	    curritem = new Item(null);
	}
	
	public static synchronized Provider instance() {
		if (provider == null)
			provider = new Provider();
		return provider;
	}

}
