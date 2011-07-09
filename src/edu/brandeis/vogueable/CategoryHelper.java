package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * helps get items of certain categories from the server
 * 
 * @author gasparobimba
 *
 */
public class CategoryHelper extends FakeProxy {

	ProxyHelper proxy=new ProxyHelper();
	RealProxy realProxy=new RealProxy();
	ArrayList<Item> value;		//a list of all items that belong to a certain category
	ArrayList<Item> allItems;
	
	
	//real proxy constructor
	public CategoryHelper(){
		HashMap<String, ArrayList<Item>> map=new HashMap<String, ArrayList<Item>>();
		
		allItems=realProxy.getAllItems();
		//map all categories to their corrsponding items
		for (Item item:allItems){
			
			String category=item.getCategoryTag();
		    value=map.get(category);
			
		    //check to ensure hash map does not have this category already
			if(map.containsKey(category)){
				if(!value.contains(item)){
					value.add(item);
				}		
			}
			//now that the category is not yet in our map, we create it
			else{
				if(!category.isEmpty()){
				value.add(item);
				map.put(category,value);
				}	
			}
			
		}//end for

	}
}

