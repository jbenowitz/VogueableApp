package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.SAXException;

import android.content.Context;

/**
 * Keeps track of what the user likes/dislikes
 * Gives next item based on likes/dislikes
 * 
 * @author Jackie
 *
 */
public class TasteManager {

	HashMap<String,Integer> tagCount;
	ArrayList<Item> itemsUsed;
	Context context;
	ArrayList<String> cats;
	


	/**
	 * Constructor
	 * Initialize tagCount HashMap to be empty.
	 * 
	 * @param ArrayList of items, to choose next item
	 * @throws SAXException 
	 */
	public TasteManager( ArrayList<String> cats) {

		this.cats=cats;
		tagCount = new HashMap<String,Integer>();
		itemsUsed = new ArrayList<Item>();

	}






	/**
	 * Increments each liked tag in HashMap
	 * 
	 * @param taglist- tags to add to like HashMap
	 */
	public void likeFlavor(ArrayList<String> taglist){
		for(String tag : taglist){
			Integer likes = 1;
			if(tagCount.containsKey(tag)){
				likes = likes + tagCount.get(tag);
				tagCount.remove(tag);
			}
			tagCount.put(tag, likes);
		}
	}


	/**
	 * Decrements each disliked tag in HashMap
	 * 
	 * @param taglist - to decrement in HashMap
	 */
	public void dislikeFlavor(ArrayList<String> taglist){
		for(String tag : taglist){
			Integer dislikes = 1;
			if(tagCount.containsKey(tag)){
				dislikes = tagCount.get(tag) - dislikes;
				tagCount.remove(tag);
			}
			tagCount.put(tag, dislikes);
		}
	}

}
