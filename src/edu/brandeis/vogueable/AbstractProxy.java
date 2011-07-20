package edu.brandeis.vogueable;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.util.Log;

/**
 * This is an abstract class for the proxy for the server for Vogueable; 
 * @author Yulia
 *
 */
public abstract class AbstractProxy {
	User curruser;// user currently using app
	private static final String TAG = "AbstractProxy";
	
	
	public AbstractProxy(){
		//curruser = new User("ananoymous");
	}
	/**
	 * connects to web service
	 * @throws SAXException 
	 */
	public void connect(Context con) {
		
	}
	
	/**
	 * disconnectes from webservice
	 */
	public void disconnect(){
		
	}
	
	/***
	 * Checks if user is in out database
	 * @param email - user's email- hopefully provided by Android; 
	 * @return
	 */
	public boolean isKnownUser(String email){
		return false; 
	}
	
	/**
	 * Creates a User Entry, if we don't yet have that user' 
	 * @param email user's email- hopefully provided by Android; 
	 */
	public void createUser(String email){
		
	}
	
	
	/**
	 * Returns Item that has the most tags out of the give list of tags; 
	 * @param currentitem - item user is currently looking at (we need to not show the same item)
	 * @param tags - list of tags  (tags with the highest counts from the user's TasteManager)
	 * @return an Item that has the most tags out of the give list of tags; 
	 */
	public Item getNextItem(Item currentitem, ArrayList<String> tags){
		Item nextit = new Item("next");
		return nextit; 
	}
	
	/**
	 * Updates the TasteManager table; It looks at each tag for the user, 
	 * and if the counts in the local TasteManager are not the same as the webserver ones - chagnes them; 
	 * @param taste
	 */
	public void updateTasteManager(TasteManager taste){
		
	}
	
	/**
	 * 
	 * @param email - user's email
	 * @return TasteManager item for user, generated from TasteManager table; 
	 */
	public TasteManager getTasteManager(String email){
		TasteManager tastes = null;
		return tastes; 
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
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	public String getTagValue(String sTag, Element eElement) {
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
	
	
}
