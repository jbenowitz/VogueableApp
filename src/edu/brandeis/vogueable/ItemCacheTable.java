package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * ItemCacheTable is the Page table of DeptItemCache arrays
 * @author Jackie
 *
 */
public class ItemCacheTable {
	private ArrayList<DeptItemCache> table;
	private int maxVirtAddy;
	private int batchsize;
	private int nextpage = 0;
	
	/**
	 * Creates table based on batchsize
	 * @param batchsize
	 */
	public ItemCacheTable(int batchsize){
		this.batchsize = batchsize;
		table = new ArrayList<DeptItemCache>(this.batchsize);
		maxVirtAddy = this.batchsize*this.batchsize;
	}
	
	/**
	 * Returns the max count of all nodes in the page table
	 * (batchsize*batchsize)
	 * @return
	 */
	public int getCount(){
		return maxVirtAddy;
	}
	
	/**
	 * gets an item from a certain virtual position
	 * @param virtual position in page table
	 * @return item
	 */
	public Item getItem(int position){
		return table.get(position/batchsize).getItem(position%batchsize);
	}
	
	/**
	 * gets the item Id of a position (for gallery, not P_ID)
	 * @param position
	 * @return
	 */
	public int getItemId(int position){
		return position;
	}
	
	/**
	 * gets the URL of the image of an item in a certian virtual position
	 * @param position
	 * @return
	 */
	public String getURL(int position){
		return getItem(position).getImageFileString();
	}
	
	/**
	 * adds a batch of items to an arraylist of items.
	 * assumes correct batchsize
	 * @param items
	 */
	public void addBatch(ArrayList<Item> items){
		table.add(nextpage, new DeptItemCache(items));
		nextpage++;
	}
	
	/**
	 * checks to see if there is more room for another batch
	 *  at the end of the table.
	 * @return
	 */
	public boolean moreBatchEmpty(){
		return (nextpage<=batchsize);
	}
	
	

}
