package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Keeps track of what the user likes/dislikes
 * Gives next item based on likes/dislikes
 * 
 * @author Jackie
 *
 */
public class TasteManager {
	
	HashMap<Item,Integer> tagCount;
	ArrayList<Item> items;
	
	/**
	 * Constructor
	 * Initialize tagCount HashMap to be empty.
	 * 
	 * @param ArrayList of items, to choose next item
	 */
	public TasteManager(ArrayList<Item> items){
		tagCount = new HashMap<Item,Integer>();
		this.items = items;
	}
	
	/**
	 * Increments each liked tag in HashMap
	 * 
	 * @param taglist- tags to add to like HashMap
	 */
	public void likeFlavor(ArrayList<Item> taglist){
		for(Item item:taglist){
			Integer likes = 1;
			if(tagCount.containsKey(item)){
				likes = likes + tagCount.get(item);
				tagCount.remove(item);
			}
			tagCount.put(item, likes);
		}
	}
	
	/**
	 * Decrements each disliked tag in HashMap
	 * 
	 * @param taglist - to decrement in HashMap
	 */
	public void dislikeFlavor(ArrayList<Item> taglist){
		for(Item item:taglist){
			Integer dislikes = 1;
			if(tagCount.containsKey(item)){
				dislikes = tagCount.get(item) - dislikes;
				tagCount.remove(item);
			}
			tagCount.put(item, dislikes);
		}
	}
	
	
	//TODO finishthis one
	public Item getNextItem(Item currItem){
		items.remove(currItem);
		return items.get(new Random().nextInt(items.size()));
	}

}
