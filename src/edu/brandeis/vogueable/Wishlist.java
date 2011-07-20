package edu.brandeis.vogueable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.monoid.web.Resty;
import us.monoid.web.XMLResource;
import android.util.Log;

/**
 * This wishlist is the data structure for storing all the items
 * added to a user's wishlist.
 * 
 * @author Yulia
 *
 */
public class Wishlist {
	private ArrayList<Item> wishlist;
	//private String name; 
	private String TAG;
	private User you;
	
	/**
	 * Constructor to WishList.  
	 * takes in a name for wishlist (like christmas or hannukkah for future)
	 * @param Name
	 */
	public Wishlist(User you){
		wishlist = new ArrayList<Item>();
		this.you=you;
	}
	public void mergeWishlist(Item itemA){
		if (!wishlist.contains(itemA)){
			wishlist.add(0,itemA);
		}
		
	}
	
	/**
	 * Adds an item to the wishlist
	 * @param itemA item to be added
	 */
	public void addItem(Item itemA){
		if (!wishlist.contains(itemA)){
			wishlist.add(0,itemA);
			pushWishlistItemToServer(itemA);
		}
		
	}
	
	private void pushWishlistItemToServer(Item itemA){
		/** This method should add an item to the server once its been added to the local wishlist on the device
		 * TAKES IN: user -to extract his id and an Item, to extract the id too
		 */
		
			if (!checkItemOnServerWishlist().containsKey(itemA.getItemID())){ 		
				//if (checkItemOnServerWishlist(id))
				String link ="http://vogueable.heroku.com/users/"+you.getID()+"/wishlists/add?item="+itemA.getItemID();
				Log.d(TAG, "You have: " + link);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				try {
					request.setURI(new URI(link));
					Log.d(TAG, "response made"); 
					//execute the GET request
					try {
						HttpResponse response = client.execute(request);
						Log.d((String) TAG, "Executed: " + link);
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
	
	/**
	 * removes an item from the wishlist
	 * @param itemR - removable item
	 */
	public void remove(Item itemR){
		if (wishlist.contains(itemR)){
			wishlist.remove(itemR);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Item> showWishlist(){
		return wishlist;
	}
	
	public void setWish(){
		ArrayList<Item> itemsOnServer = getWishlistFromServer();
		
		for(Item item: itemsOnServer){
			if (!wishlist.contains(item)) 
			mergeWishlist(item);
		}
	}
	
	  public ArrayList<Item> getWishlistFromServer() {

	    	ArrayList<Integer>  itemsOnServer = new ArrayList<Integer>(checkItemOnServerWishlist().keySet());
	    	Resty r = new Resty();
	    	XMLResource usr1 = null;
	    	NodeList nList=null;

	    	ArrayList<Item> itemsInWishlistOnServer= new ArrayList<Item>();
	    	//Log.i(TAG, "User id " + user.getID());
	    	//create items from server to populate local wishlist
	    	//if (!itemsOnServer.isEmpty()){
	    		for(int itemIdOnServer:itemsOnServer){

	    			try {
	    				usr1 = r.xml("http://vogueable.heroku.com/items/"+itemIdOnServer+".xml");

	    				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    				DocumentBuilder dBuilder;

	    				dBuilder = dbFactory.newDocumentBuilder();
	    				String st = ""+usr1;
	    				InputStream is = new ByteArrayInputStream(st.getBytes());
	    				Document doc;
	    				doc = dBuilder.parse(is);

	    				doc.getDocumentElement().normalize();

	    				nList = doc.getElementsByTagName("item");

	    			}
	    			catch (ParserConfigurationException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			catch (IOException e) {
	    				Log.e(TAG, e.toString());
	    			}catch (SAXException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			for(int k =0; k<nList.getLength(); k++){
	    				Node nNode = nList.item(k);
	    				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    					Item it = new Item("it");
	    					Element eElement = (Element) nNode;
	    					it.setName(getTagValue("name", eElement));
	    					Log.d(TAG,"name" + it.getName());
	    					it.setImageFileString(getTagValue("img-url", eElement));
	    					it.setDescription(getTagValue("features", eElement));
	    					it.setLink(getTagValue("link-to-buy", eElement));
	    					it.setPrice(getTagValue("item-price", eElement));
	    					it.setBrand(getTagValue("brand", eElement));
	    					it.setID(getTagValue("id", eElement));
	    					itemsInWishlistOnServer.add(it);
	    				}
	    			}
	    		}
	    		return itemsInWishlistOnServer;

	    	//}
	    	//else return new ArrayList<Item>();


	    }
	    
	    /**
	     * returns all items on a wishlist as map of int->user
	     * important because so far I can only retrieve item id which is an integer from wishlist, if I could get an Item Object
	     * we wouldnt need this
	     * @return
	     */
	    private HashMap<Integer, String> checkItemOnServerWishlist(){
	    	//Log.d(TAG, "checking if this item exists " + );
	    	Resty r = new Resty();
	    	XMLResource usr1 = null;
	    	NodeList wishNList = null;
	    	HashMap<Integer, String> items = new HashMap<Integer, String>();

	    	try {
	    		usr1 = r.xml("http://vogueable.heroku.com/users/"+you.getID()+"/wishlists.xml");
	    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    		String st = ""+usr1;
	    		InputStream is = new ByteArrayInputStream(st.getBytes());

	    		Document doc = dBuilder.parse(is);
	    		doc.getDocumentElement().normalize();
	    		wishNList = doc.getElementsByTagName("wishlist");

	    	} catch (IOException e) {
	    		e.printStackTrace();
	    		Log.e("gaspar", "exception on r.xml");
	    	} catch (ParserConfigurationException e) {
	    		e.printStackTrace();
	    	} catch (SAXException e) {
	    		e.printStackTrace();
	    	}


	    	//Parses through all the taken users from the database
	    	//looks at e-mails solely.  Create a Map connecting the email to
	    	// their ID.
	    	//user=new User("");
	    	for (int temp = 0; temp < wishNList.getLength(); temp++) {
	    		Node nNode = wishNList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element eElement = (Element) nNode;
	    			items.put(Integer.parseInt(getTagValue("item-id", eElement)),getTagValue("user-id", eElement));
	    			Log.d(TAG,"item" + items.get(temp));
	    		}
	    	}
	    	return items;

	    }

	    /** 
	     * Used to get a tag value of the xml for Users
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
