package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
	RealProxy prox;
	Context context;
	ArrayList<String> cats;
	


	/**
	 * Constructor
	 * Initialize tagCount HashMap to be empty.
	 * 
	 * @param ArrayList of items, to choose next item
	 * @throws SAXException 
	 */
	public TasteManager( RealProxy Prox, ArrayList<String> cats) {

		this.cats=cats;
		tagCount = new HashMap<String,Integer>();
		prox = Prox;
		prox.connect();
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


	/**
	 * TO COMMENT
	 * @param currItem
	 * @param currCat
	 * @return
	 */
	public Item getNextItem(Item currItem){
         
		RealProxy proxy = new RealProxy();
		Context context = null; 
		Provider provide = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
		
		Item next = null;
		ArrayList<Item> filtered = new ArrayList<Item>();
		next = prox.getNextItem(currItem);
		if(cats.isEmpty()){
			return next;
		}else 
		
		//for(Item it : itemsUsed){
		//	if (cats.contains(it.getCategoryTag().toLowerCase())){
		//		filtered.add(it);
		//	}
		//}
			
		//if(filtered.contains(next)){
		//	return next;
		//
		if (cats.contains(next.getCategoryTag().toLowerCase())){
			return next;
		} else {
			return next = getNextItem(currItem);
		}
	}
}
