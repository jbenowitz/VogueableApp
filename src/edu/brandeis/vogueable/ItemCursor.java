package edu.brandeis.vogueable;

/**
 * Keeps track of what item is in viewpoint
 * 
 * @author Jackie
 *
 */
public class ItemCursor {
	Item currItem;
	Item prevItem;
	ItemDB itemDB;
	
	/**
	 * Constructor
	 * @param itemDB- so it can choose items
	 */
	public ItemCursor(ItemDB itemDB){
		currItem = getInitialItem();
		prevItem = currItem;
		this.itemDB = itemDB;
	}
	
	/**
	 * Gets initial item
	 * Calls getRandomItem() from ItemDB
	 * 
	 * @return a random item from database
	 */
	public Item getInitialItem(){
		return itemDB.getRandomItem();
	}
	
	/**
	 * Gets current item in image view
	 * @return current item
	 */
	public Item getCurrentItem(){
		return currItem;
	}
	
	/**
	 * Sets current item
	 * @param Item to be current viewpoint item
	 */
	public void setCurrentItem(Item item){
		prevItem = currItem;
		currItem = item;
	}
	
	/**
	 * @return previous item
	 */
	public Item getPrevItem(){
		return prevItem;
	}
}
