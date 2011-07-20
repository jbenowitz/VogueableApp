package edu.brandeis.vogueable;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;


/**
 * 
 * @author Jackie
 *
 */
public class Item {
	
	private static final String TAG = "Vogueable Item";
	private String name, imageFile, brand, description, link, price, categorytag,id;
	private ArrayList<String> taglist;

	private boolean itemliked=false, itemdisliked=false; //tells MainActivity if user has item 'liked' or 'disliked' enabled

	
	
	/**
	 * An easy to use constructor that just takes the name of the item;  
	 * can set other fields later, using set methods; 
	 * @param name - String name of Item
	 */
	public Item(String name){
		this.name = name; 
		taglist = new ArrayList<String>();
	}
	
	
	/**
	 * Constructor that begins with all inforamtion
	 * @param String Name, Image File name, Brand, Price, Description, taglist, link
	 */
	public Item (String name, String imageFile, String brand, 
					String price, String description, ArrayList<String> taglist,
					String link, String categorytag,String id){
		this.name = name;
		this.imageFile = imageFile;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.taglist = taglist;
		this.link = link;
		this.categorytag = categorytag;
		this.id=id;
	}
	
	
	/**
	 * 
	 * @return string of Item's name
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Re-sets the name of a certain Item
	 * 
	 * @param name of a certain Item
	 */
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
	
	/**
	 * Set the url of an imagefile 
	 * @param url
	 */
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
	
	
	/**
	 * Sets the brand name of an item
	 * @param mybrand
	 */
	public void setBrand(String mybrand){
		brand = mybrand; 
	}
	
	
	/**
	 * 
	 * @return price of item
	 */
	public String getPrice(){
		if (price != null){
			return price;
		} else {
			return "";
		}
	}
	
	
	/**
	 * Sets the price
	 * 
	 * @param money
	 */
	public void setPrice(String money){
		price = money;
	}
	
	
	/**
	 * @return description of item
	 */
	public String getDescription(){
		return description;
	}
	
	
	/**
	 * Sets description 
	 * 
	 * @param descriibe
	 */
	public void setDescription(String descript){
		description = descript;
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
	
	
	public void setLink(String st){
		link= st;
	}
	
	
	/**
	 * 
	 * @return string of Item's name
	 */
	public String getCategoryTag(){
		return categorytag;
	}
	

	/**
	 * Set category of this item
	 * @param s- category string (dress, shirt, shoes...)
	 */
	public void setCategory(String s){
		categorytag =s;
	}

	
	/**
	 * toggles the itemliked boolean (used to see if item was previously liked)
	 */
	public void toggleItemLiked(){
		if(itemliked){
			itemliked=false;
		}else{
			itemliked=true;
		}
	}
	
	
	/**
	 * toggles the itemdisliked boolean (used to see if an item was previously disliked)
	 */
	public void toggleItemDisliked(){
		if(itemdisliked){
			itemdisliked=false;
		}else{
			itemdisliked=false;
		}
	}
	
	
	/**
	 * gets the boolean of whether item was previously liked
	 * @return itemliked boolean
	 */
	public boolean getItemLiked(){
		return itemliked;
	}
	
	
	/**
	 * gets the boolean ofwhtether item was previously disliked
	 * @return itemdisliked boolean
	 */
	public boolean getItemDisliked(){
		return itemdisliked;
	}
	
	
	/**
	 * Sets a boolean into itemliked (when likeing it, true, when dislikeing it, false)
	 * @param liked boolean
	 */
	public void setItemLiked(boolean liked){
		itemliked = liked;
	}
	
	
	/**
	 * Sets a boolean into itemdisliked (when dislikeing it, true, when likeing it, false)
	 * @param disliked boolean
	 */
	public void setItemDisliked(boolean disliked){
		itemdisliked = disliked;
	}
	
	
	/**
	 * This Method creates a list of tags to describe the item that will be used by the Taste Manager,
	 * it sees if the name and description contain some key words stores in the Tags constant class; 
	 */
	public void getTags(){
		
		ArrayList<String> dis = new ArrayList<String>();
		dis.addAll(Arrays.asList(getName().split(" ")));
		if(getDescription()!= null){
			dis.addAll(Arrays.asList(getDescription().split(" ")));
		}
		for(String tag: dis){
				if(Arrays.asList(Tags.tags).contains(tag.toLowerCase()) && !getTagList().contains(tag.toLowerCase())){
					addTag(tag.toLowerCase());
				}
		}
		
	}


	public String getItemID() {
		// TODO Auto-generated method stub
		return id;
	}


	public void setID(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	
	/**
	 * Now we tell the server that User x has viewed item y and we shouldnt show him the 
	 * item again
	 */
	public void markAsViewed(User user){
		
		
		String link ="http://vogueable.heroku.com/users/"+user.getID()+"/items/"+this.getItemID()+"/viewed";
		//HttpPost httppost = new HttpPost(link);
		
			HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            try {
				request.setURI(new URI(link));
			
            try {
				HttpResponse response = client.execute(request);
				Log.d(TAG, "Executed: " + link);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            }
            catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
	}
}
