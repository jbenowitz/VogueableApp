package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.content.Context;


public class Provider {
	
	private static final int BATCH_SIZE = 30;
	
	private User user;
	private Item curritem;
	private TasteManager usertaste;
	private static Provider provider = null;
	private ArrayList<String> cats; 
	private Context context;
	private DeptItemCache itemcache;
	private RealProxy proxy;
	
	
	private Provider(String username, Context con, String item){
		
		user = new User(username);
		user.setTasteManager(usertaste);
	    curritem = new Item(item);
	    cats = new ArrayList<String>();
	    itemcache = new DeptItemCache(BATCH_SIZE);
	    proxy = new RealProxy(itemcache, cats);
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
		 proxy = new RealProxy(itemcache, cats); //update proxy
	}
	public void clearCatList(){
		 cats.clear();
		 proxy = new RealProxy(itemcache,cats); //update proxy
	}
	public ArrayList<String> getCatList(){
		 return cats;
	}
	
	public DeptItemCache getItemCache(){
		return itemcache;
	}
	
	
	public static synchronized Provider instance(String username, Context con, String item) {
		if (provider == null){
			provider = new Provider(username, con, item);
		}
		provider.context = con;
		return provider;
	}

}
