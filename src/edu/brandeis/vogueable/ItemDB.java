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
	
	/**
	 * Constructor to loadFromDisk()
	 */
	public ItemDB(){
		this.loadFromDisk();
	}

	/**
	 * To be implemented later
	 * 
	 * @author Yulia
	 * 
	 */
	public ArrayList<Item> loadFromDisk(){
		
		File directory = new File("/Users/Jackie/Documents/2010-2011/Summer_2011/JBS/VogueableApp/res/drawable-ldpi/itemdb");
		
		if (directory.isDirectory()) {
			
			File AllFiles[] = directory.listFiles();
			for(File aFile: AllFiles){
				File ImageFiles[] = aFile.listFiles();
				for(File image: ImageFiles){
					
					ArrayList<String> tags= new ArrayList<String>();
					tags.add("i'm a tag");
					Item thing = new Item(image.getName(),image.getName(),"BRAND",12.00,"Oh Hey! You could wear this!", tags,"link");
					
					items.add(thing);
				}
			}
			
			}

		//TODO Just make a item for each of these using all the information
		// in the item Constructor.
		//Then add that item to the items ArrayList
		
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
