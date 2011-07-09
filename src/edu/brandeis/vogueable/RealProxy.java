package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.Random;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import android.content.Context;
import android.util.Log;


public class RealProxy extends FakeProxy {

	ProxyHelper proxy=new ProxyHelper();;
	private final static String TAG = "VogueableRealProxy";
	ArrayList<User> userList;		//a list of all current users of our app

	//real proxy constructor
	public RealProxy(){

	}


	/**
	 * connects to web service
	 * @throws SAXException 
	 * Gets all items and users on the server as xml files
	 */
	public void connect(Context context) {
		
	}


	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		for (int temp = 0; temp < proxy.nListItems.getLength(); temp++) {

			Node nNode = proxy.nListItems.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Item it = new Item("it");
				Element eElement = (Element) nNode;
				it.setName(getTagValue("name", eElement));
				Log.d(TAG,"name" + it.getName());
				it.setImageFileString(getTagValue("img-url", eElement));
				it.setLink(getTagValue("link-to-buy", eElement));
				it.setPrice(getTagValue("item-price", eElement));
				it.setCategory(getTagValue("category", eElement));
				it.setBrand(getTagValue("brand", eElement));
				//it.addTag(getTagValue("fabric_type", eElement));
				it.addTag("this");
				items.add(it);

			}
		}
		return items;
	}	

	public Item getNextItem(Item currentitem, ArrayList<String> tags){
		Item nextit = new Item("next");
		int temp = new Random().nextInt(proxy.nListItems.getLength());
		//Node nNode = nList.item(temp);

		for (int k = 0; k < proxy.nListItems.getLength(); k++) {

			Node nNode = proxy.nListItems.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				nextit.setName(getTagValue("name", eElement));
				nextit.setImageFileString(getTagValue("img-url", eElement));
				nextit.setLink(getTagValue("link-to-buy", eElement));
				nextit.setPrice(getTagValue("item-price", eElement));

				//nextit.addTag(getTagValue("fabric_type", eElement));
				nextit.addTag("this");
				break;

			}
		}
		return nextit; 
	}
	
	public ArrayList<User> getAllUsers(){
		userList = new ArrayList<User>();
		for (int temp = 0; temp < proxy.nListUsers.getLength(); temp++) {

			Node nNode = proxy.nListUsers.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				User user = new User("user");
				Element eElement = (Element) nNode;
				user.setName(getTagValue("user-name", eElement));
				Log.d(TAG,"name" + user.getName());
				user.setEmail(getTagValue("email", eElement));
				userList.add(user);

			}
		}
		return userList;
	}
	/***
	 * Checks if user is in our database
	 * will use this to determine whether user is also logged in or not
	 * @param email - user's email- hopefully provided by Android; 
	 * @return
	 */
	public boolean isKnownUser(String email){
		if (getAllUsers().contains(getUser(email)))
			return true;
		else
			return false; 
	}

	/**
	 * Creates a User Entry, if we don't yet have that user' 
	 * @param email user's email- hopefully provided by Android; 
	 */
	public void createUser(String email){
		if (!getAllUsers().contains(getUser(email))){
			getAllUsers().add(getUser(email));
		}
		//return userList; 
	}

	/**
	 * Returns user object
	 * @param email - user's email- hopefully provided by Android; 
	 * @return Returns user object instantiated with data from webservice 
	 */
	public User getUser(String email){
		User user = new User(email);//
		return user; 
	}
}

