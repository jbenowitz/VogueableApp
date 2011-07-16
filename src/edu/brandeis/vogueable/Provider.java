package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;


public class Provider {
	
	private static final int BATCH_SIZE = 30;
	private static final String TAG = "Provider";
	
	private User user;
	private Item curritem;
	private TasteManager usertaste;
	private static Provider provider = null;
	private ArrayList<String> cats; 
	private Context context;
	private DeptItemCache itemcache;
	
	
	private Provider(String username, Context con){
		Log.i(TAG, "constructing provider");
		
		user = new User(username);
		user.setTasteManager(usertaste);
	    cats = new ArrayList<String>();
	    itemcache = new DeptItemCache(BATCH_SIZE);
	    context = con;
	    try {
			usertaste = new TasteManager(cats);
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
		Log.i(TAG, "getting current item: " + curritem);
		return curritem;
	}
	/**
	 * Sets the current item to be displayed
	 * @param it - the Item that will be currently displayed; 
	 */
	public void setCurItem(Item it){
		Log.i(TAG, "setting current item to " + it);
		curritem = it;
	}
	
	public User getCurUser(){
		Log.i(TAG, "Getting current user name " + user.getName());
		return user;
	}
	
	public TasteManager getCurTM(){
		Log.i(TAG, "Getting current Taste Manager");
		return usertaste; 
	}
	
	public void setAcat(String cat){
		Log.i(TAG, "adding categor " + cat + " to category list");
		 cats.add(cat);
	}
	public void clearCatList(){
		Log.i(TAG, "clearing category list");
		 cats.clear();
	}
	public ArrayList<String> getCatList(){
		Log.i(TAG, "getting current category list");
		 return cats;
	}
	
	public DeptItemCache getItemCache(){
		Log.i(TAG, "getting item cache");
		return itemcache;
	}
	
	public void setItemCache(DeptItemCache dic){
		Log.i(TAG, "resetting item cache");
		itemcache = dic;
	}
	
	
	public static synchronized Provider instance(String username, Context con) {
		if (provider == null){
			provider = new Provider(username, con);
		}
		return provider;
	}
	

}
