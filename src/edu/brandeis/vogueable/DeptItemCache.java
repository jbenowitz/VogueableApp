package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * DeptItemCache
 * To be put in the page table
 * Glorified array list with correct methods to work with ImageAdapter
 * @author Jackie
 *
 */
public class DeptItemCache {
	private ArrayList<Item> itemcache;
	private int batchsize;
	
	/**
	 * creates an empty deptitemcache with a certain batchsize
	 * @param batchsize
	 */
	public DeptItemCache(int batchsize){
		this.batchsize = batchsize;
		itemcache = new ArrayList<Item>(this.batchsize);
	}
	
	/**
	 * creates an deptitemcache with an array list of items
	 * makes the batchsize the size of these items
	 * @param items
	 */
	public DeptItemCache(ArrayList<Item> items){
		itemcache = items;
		batchsize = items.size();
	}
	
	/**
	 * gets the batchsize (max count of arraylist)
	 * @return
	 */
	public int getCount(){
		return batchsize;
	}
	
	/**
	 * returns an item at a given position
	 * @param position
	 * @return item
	 */
	public Item getItem(int position){
		return itemcache.get(position);
	}
	
	/**
	 * returns the itemid used by gallery (position)
	 * @param position
	 * @return
	 */
	public int getItemId(int position){
		return position;
	}
	
	/**
	 * gets the image URL of an item at a given position
	 * @param position
	 * @return
	 */
	public String getURL(int position){
		return getItem(position).getImageFileString();
	}
	
	/**
	 * adds a single item to the end of the arraylist
	 * @param i
	 */
	public void addItem(Item i){
		itemcache.add(i);
	}
	
	/**
	 * adds a list of items to the end of the arraylist
	 * @param items
	 */
	public void addItems(ArrayList<Item> items){
		itemcache.addAll(items);
	}

}