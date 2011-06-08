package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Keeps track of what the user likes/dislikes
 * Gives next item based on likes/dislikes
 * 
 * @author Jackie
 *
 */
public class TasteManager {
	
	HashMap<Item,Integer> tagCount;
	
	
	/**
	 * Constructor
	 * Initialize tagCount HashMap to be empty.
	 */
	public TasteManager(){
		tagCount = new HashMap<Item,Integer>();
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
		
	}

}
