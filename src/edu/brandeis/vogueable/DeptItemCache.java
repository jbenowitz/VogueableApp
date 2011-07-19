package edu.brandeis.vogueable;

import java.util.ArrayList;

public class DeptItemCache {
	private ArrayList<Item> itemcache;
	private int batchsize;
	
	public DeptItemCache(int batchsize){
		this.batchsize = batchsize;
		itemcache = new ArrayList<Item>(this.batchsize);
	}
	
	public int getCount(){
		return batchsize;
	}
	
	public Item getItem(int position){
		return itemcache.get(position);
	}
	
	public int getItemId(int position){
		return position;
	}
	
	public String getURL(int position){
		return getItem(position).getImageFileString();
	}
	
	public void addItem(Item i){
		itemcache.add(i);
	}
	
	public void addItems(ArrayList<Item> items){
		itemcache.addAll(items);
	}

}