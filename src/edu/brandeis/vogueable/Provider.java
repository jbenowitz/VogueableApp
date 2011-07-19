package edu.brandeis.vogueable;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
	private ItemCacheTable itemcache;
	private RealProxy proxy;
	
	
	private Provider(String username, Context con, String item){
		
		user = new User(username);
		user.setTasteManager(usertaste);
	    curritem = new Item(item);
	    cats = new ArrayList<String>();
	    itemcache = new ItemCacheTable(BATCH_SIZE);
	    proxy = new RealProxy(this);
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
		Log.i(TAG, "getCurItem() called");
		return curritem;
	}
	/**
	 * Sets the current item to be displayed
	 * @param it - the Item that will be currently displayed; 
	 */
	public void setCurItem(int position){
		Log.i(TAG, "setcuritem(position) called with position " + position );
		curritem = itemcache.getItem(position);
	}
	
	public User getCurUser(){
		Log.i(TAG, "getting current user name : " + user.getName());
		return user;
	}
	
	public TasteManager getCurTM(){
		Log.i(TAG, "getting current taste manager");
		return usertaste; 
	}
	
	public void setAcat(String cat){
		Log.i(TAG, "adding a category " + cat);
		 cats.add(cat);
	}
	public void clearCatList(){
		Log.i(TAG, "clearing category list");
		 cats.clear();
	}
	public ArrayList<String> getCatList(){
		Log.i(TAG, "getting category list");
		 return cats;
	}
	
	public ItemCacheTable getItemCache(){
		Log.i(TAG, "getting itemcache");
		return itemcache;
	}
	public RealProxy getProxy(){
		Log.i(TAG, "getting proxy");
		return proxy;
	}
	
	public void fillItemCache(){
		Log.i(TAG, "adding a batch with fillItemCache");
		if(!itemcache.moreBatchEmpty()){
			Log.i(TAG, "recirculating itemcache");
			itemcache.circulate();
		}
		try {
			itemcache.addBatch(getProxy().getBatch(BATCH_SIZE));
		} catch (ParserConfigurationException e) {
			Log.e(TAG, e.toString());
		} catch (SAXException e) {
			Log.e(TAG, e.toString());
		} catch (IOException e) {
			Log.e(TAG, e.toString());
		}
	}
	
	public int getBatchSize(){
		Log.i(TAG, "getting batch size " + BATCH_SIZE);
		return BATCH_SIZE;
	}
	
	public static synchronized Provider instance(String username, Context con, String item) {
		if (provider == null){
			provider = new Provider(username, con, item);
		}

		return provider;
	}

}
