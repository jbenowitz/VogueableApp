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
	private Context context;
	private DeptItemCache itemcache;
	private RealProxy proxy;
	
	
	private Provider(String username, Context con, String item){
		
		user = new User(username);
		user.setTasteManager(usertaste);
	    curritem = new Item(item);
	    cats = new ArrayList<String>();
	    itemcache = new DeptItemCache(BATCH_SIZE);
	    proxy = new RealProxy(this);
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
		 //cats = new ArrayList<String>();
		 cats.clear();
	}
	public ArrayList<String> getCatList(){
		 return cats;
	}
	
	public DeptItemCache getItemCache(){
		return itemcache;
	}
	public RealProxy getProxy(){
		return proxy;
	}
	
	public void fillItemCache(){
		try {
			itemcache.addItems(getProxy().getBatch(BATCH_SIZE));
		} catch (ParserConfigurationException e) {
			Log.e(TAG, e.toString());
		} catch (SAXException e) {
			Log.e(TAG, e.toString());
		} catch (IOException e) {
			Log.e(TAG, e.toString());
		}
	}
	
	public static synchronized Provider instance(String username, Context con, String item) {
		if (provider == null){
			provider = new Provider(username, con, item);
		}
		provider.context = con;
		return provider;
	}

}
