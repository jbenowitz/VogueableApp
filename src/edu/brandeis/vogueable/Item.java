package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * 
 * @author Jackie
 *
 */
public class Item {
	
	private String name, imageFile, brand, description, link;
	private String price;
	private ArrayList<String> taglist;
	private String categorytag;
	
	
	/**
	 * An easy to use constructor that just takes the name of the item;  
	 * can set other fields later, using set methods; 
	 * @param name - String name of Item
	 */
	public Item(String name){
		this.name = name; 
	}
	/**
	 * Constructor that begins with all inforamtion
	 * @param String Name, Image File name, Brand, Price, Description, taglist, link
	 */
	
	public Item (String name, String imageFile, String brand, 
					String price, String description, ArrayList<String> taglist,
					String link, String categorytag){
		this.name = name;
		this.imageFile = imageFile;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.taglist = taglist;
		this.link = link;
		this.categorytag = categorytag;
	}
	
	/**
	 * 
	 * @return string of Item's name
	 */
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name; 
	}
	
	/**
	 * 
	 * @return filepath as a string of imagefile
	 */
	public String getImageFileString(){
		return imageFile;
	}
	
	public void setImageFileString(String url){
		imageFile = url; 
	}
	/**
	 * 
	 * @return string name of brand of item
	 */
	public String getBrand(){
		return brand;
	}
	
	public void setBrand(String mybrand){
		brand = mybrand; 
	}
	/**
	 * 
	 * @return price of item
	 */
	public String getPrice(){
		return price;
	}
	
	public void setPrice(String money){
		price = money;
	}
	/**
	 * @return description of item
	 */
	public String getDescription(){
		return description;
	}
	public void setDescription(String descriibe){
		description = descriibe;
	}
	
	/**
	 * 
	 * @return list of Tags
	 */
	public ArrayList<String> getTagList(){
		return taglist;
	}
	
	/**
	 * Add a specific tag to the tag list
	 * @param string that is a tag for this specific item
	 * 
	 */
	public void addTag (String newTag){
		if(!taglist.contains(newTag)){
			taglist.add(newTag);
		}
	}
	
	/**
	 * 
	 * @return string of link
	 */
	public String getLink(){
		return link;
	}
	
	/**
	 * 
	 * @return string of Item's name
	 */
	public String getCategoryTag(){
		return categorytag;
	}
	
}
