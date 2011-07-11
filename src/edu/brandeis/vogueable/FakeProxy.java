package edu.brandeis.vogueable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.Resources;
import android.nfc.Tag;
import android.util.Log;

public class FakeProxy extends AbstractProxy {

	User curruser;// user currently using app
	NodeList nList;
	Context con;
	private final static String TAG = "FakeProxy";

	private ArrayList<User> userList=new ArrayList<User>();//list of all our users

	public FakeProxy(){}

	/**
	 * connects to web service
	 * @throws SAXException 
	 */
	public void connect(Context context) {
		con = context;


		try {

			/* get xmlfiles of items from server*/
			Resources rr = context.getResources();

			InputStream stream = rr.openRawResource(R.raw.ouritems);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stream);
			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("item");//list of all item nodes
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//end connect to server

	/**
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	protected String getTagValue(String sTag, Element eElement) {
		NodeList list = eElement.getElementsByTagName(sTag);
		Node el = list.item(0);
		Log.d(TAG,"error on el");

		if (el != null) {
			NodeList nlList = el.getChildNodes();//get all children of the item node
			Node nValue = (Node) nlList.item(0);
			if (nValue != null){
				return nValue.getNodeValue();
			}
		}
		return null; 
	}
	/**
	 * disconnectes from webservice
	 */
	public void disconnect(){

	}

	/***
	 * Checks if user is in out database
	 * will use this to determine whether user is also logged in or not
	 * @param email - user's email- hopefully provided by Android; 
	 * @return
	 */
	public boolean isKnownUser(String email){
		if (userList.contains(getUser(email)))
			return true;
		else
			return false; 
	}

	/**
	 * Creates a User Entry, if we don't yet have that user' 
	 * @param email user's email- hopefully provided by Android; 
	 */
	public void createUser(String email){
		if (!userList.contains(getUser(email))){
			userList.add(getUser(email));
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

	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Item it = new Item("it");
				Element eElement = (Element) nNode;
				it.setName(getTagValue("title", eElement));
				Log.d(TAG,"name" + it.getName());
				it.setImageFileString(getTagValue("imgage_url", eElement));
				//it.setPrice(getTagValue("price", eElement));
				//it.setBrand(getTagValue("brand", eElement));
				//it.addTag(getTagValue("fabric_type", eElement));
				items.add(it);

			}
		}
		return items;
	}
	/**
	 * Returns Item that has the most tags out of the give list of tags; 
	 * @param currentitem - item user is currently looking at (we need to not show the same item)
	 * @param tags - list of tags  (tags with the highest counts from the user's TasteManager)
	 * @return an Item that has the most tags out of the give list of tags; 
	 */
	public Item getNextItem(Item currentitem, ArrayList<String> tags){
		Item nextit = new Item("next");
		int temp = new Random().nextInt(nList.getLength());
		//Node nNode = nList.item(temp);

		for (int k = 0; k < nList.getLength(); k++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				//if(getTagValue("fabric_type",eElement)== tags.get(0)){


				nextit.setName(getTagValue("title", eElement));
				nextit.setImageFileString(getTagValue("image_url", eElement));
				//nextit.setPrice(getTagValue("price", eElement));
				nextit.setBrand(getTagValue("brand", eElement));
				//nextit.addTag(getTagValue("fabric_type", eElement));
				break;

				//}
			}
		}
		return nextit; 
	}


	/**
	 * Updates the TasteManager table; It looks at each tag for the user, 
	 * and if the counts in the local TasteManager are not the same as the webserver ones - changes them; 
	 * @param taste
	 */
	public void updateTasteManager(TasteManager taste){
		TasteManager serverTasteManager = null;//TO_DO implement server taste manager 
		//check if counts on server differ from ones on the local database
		//
		for(String tags:taste.tagCount.keySet())
			if(serverTasteManager.tagCount.containsKey(tags))
				if(taste.tagCount.get(tags)!=serverTasteManager.tagCount.get(tags)){ //compare counts	
					int cnt=taste.tagCount.get(tags);//change web server count	
					serverTasteManager.tagCount.get(tags);
				}
	}//end update


	/**
	 * 
	 * @param email - user's email
	 * @return TasteManager item for user, generated from TasteManager table; 
	 */
	public TasteManager getTasteManager(String email){
		TasteManager tastes = null;
		if (userList.contains(getUser(email))){  //check if user is logged in
			return getUser(email).getTasteManager();

		}
		else  { //create user account and initialise his taste manager
			User t=new User(email);
			t.setTasteManager(tastes);
			return t.getTasteManager();
		}


	}

	/**
	 * 
	 * @param email - user's email; 
	 * @return Wishlist item generated from Wishlist Table 
	 */
	public ArrayList<Item> getWishlistforUser(String email){
		ArrayList<Item> wishlist = new ArrayList<Item>();
		return wishlist; 
	}
	/**
	 * Updates wishlist on server for user; 
	 * @param wishlist - Wishlsit for User
	 */
	public void updateWishList(Wishlist wishlist){

	}


	/**
	 * return all the users currently in our database
	 */
	public void getAllUsers(){
		if (!userList.isEmpty()){
			for (User usr:userList){
				System.out.println(usr.getName());

			}
		}
	}

}