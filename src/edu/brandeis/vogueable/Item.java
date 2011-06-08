package edu.brandeis.vogueable;

import java.util.ArrayList;

/**
 * 
 * @author Jackie
 *
 */
public class Item {
	
	private String name, imageFile, brand, description, link;
	private double price;
	private ArrayList<String> taglist;
	
	
	/**
	 * Constructor that begins with all inforamtion
	 * @param String Name, Image File name, Brand, Price, Description, taglist, link
	 */
	public Item (String name, String imageFile, String brand, 
					double price, String description, ArrayList<String> taglist,
					String link){
		this.name = name;
		this.imageFile = imageFile;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.taglist = taglist;
		this.link = link;
	}
	
	/**
	 * 
	 * @return string of Item's name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return filepath as a string of imagefile
	 */
	public String getImageFileString(){
		return imageFile;
	}
	
	/**
	 * 
	 * @return string name of brand of item
	 */
	public String getBrand(){
		return this.brand;
	}
	
	/**
	 * 
	 * @return price of item
	 */
	public double getPrice(){
		return price;
	}
	
	/**
	 * @return description of item
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * 
	 * @return list of Tags
	 */
	public ArrayList<String> getTagList(){
		return this.taglist;
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
		return this.link;
	}
	
}
