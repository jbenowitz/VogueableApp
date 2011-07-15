package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Used as an item collection and cache for what's up next on the ImageAdapter/Gallery
 * 
 * 
 * @author Jackie
 *
 */
public class DeptItemCache {
	
	private int batchsize, virtindex;
	private ArrayList<Item> itemcache;
	private boolean beenfilled = false;
	
	/**
	 * Sets up itemcache, batchsize, and starts the virtual address at 0
	 * @param batchsize
	 */
	public DeptItemCache(int batchsize){
		this.batchsize=batchsize;
		itemcache = new ArrayList<Item>(batchsize);
		virtindex=0;
	}
	
	
	/**
	 * Checks to see if the list was ever filled with items
	 * 
	 * @return boolean
	 */
	public boolean hasData(){
		if(beenfilled){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * Finds where the starting point of this array virtual address is
	 * 
	 * @return
	 */
	public int getVirtIndex(){
		return virtindex;
	}
	
	
	/**
	 * Privately increments the virtual index
	 */
	private void incrVirtIndex(){
		virtindex += batchsize;
	}
	
	
	/**
	 * inserts the ArrayList items to the end of itemcache
	 * @param items
	 */
	public void insertItems(ArrayList<Item> items){
		itemcache.addAll(items);
	}
	
	
	/**
	 * shuffles arraylist
	 */
	public void shuffle(){
		Collections.shuffle(itemcache);
	}
	
	
	/**
	 * gets an item from position n(virtual position)
	 * @param n
	 * @return
	 */
	public Item getItem(int n){
		return itemcache.get(n-virtindex);
	}

}
