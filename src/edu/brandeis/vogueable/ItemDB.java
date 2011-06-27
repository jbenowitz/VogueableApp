package edu.brandeis.vogueable;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;



/**
 * 
 * @author Jackie
 *
 */
public class ItemDB {
	ArrayList<Item> items = new ArrayList<Item>();
	FakeProxy prox;
	
	/**
	 * Constructor to loadFromDisk()
	 */
	public ItemDB(){
		this.loadFromDisk();
		prox = new FakeProxy();
		prox.connect();
	}

	/**
	 * To be implemented later
	 * 
	 * @author Yulia
	 * 
	 */
	public ArrayList<Item> loadFromDisk(){
		 items = prox.getAllItems();
		 return items;
			
			}

		
		
	
	
	
	/**
	 * Loads an Item from disk and returns 
	 * said item
	 * 
	 * @param string name
	 * @return Item with that name  Returns nothing if
	 * 	that name doesn't link up with any item.
	 */
	public Item getItem(String name){
		for(Item item : items){
			if(item.getName() == name){
				return item;
			}
		}
		return null;
	}
	
	//TODO getAllItemsForCategory("_______")
	
	/**
	 * @return ArrayList<Item> of all the items in the database
	 */
	public ArrayList<Item> getAllItem(){
		return items;
	}
	
	/**
	 * Draws a random item from the item list
	 * 
	 * @return an item
	 */
	public Item getRandomItem(){
		return items.get(new Random().nextInt(items.size()));
	}
}
