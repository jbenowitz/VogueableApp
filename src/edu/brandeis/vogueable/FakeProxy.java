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
import android.content.Context;
import android.content.res.Resources;
import android.nfc.Tag;
import android.util.Log;

public class FakeProxy extends AbstractProxy {
	
	User curruser;// user currently using app
	NodeList nList;
	Context con;
	private final static String TAG = "FakeProxy";
	public FakeProxy(){
		
		
	}
	/**
	 * connects to web service
	 */
	@SuppressWarnings("deprecation")
	public void connect(Context context){
		con = context;
		
		
		try {
			
			
			Resources rr = context.getResources();
			
			InputStream stream = rr.openRawResource(R.raw.ouritems);
			//StringBufferInputStream stream = new StringBufferInputStream (st);
			//byte[] bits = st.getBytes();
			//ByteArrayInputStream bais = new ByteArrayInputStream(stream);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("item");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/*helper method*/
	private String getTagValue(String sTag, Element eElement) {
		NodeList list = eElement.getElementsByTagName(sTag);
		Node el = list.item(0);
		NodeList nlList = el.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue != null){
			return nValue.getNodeValue();
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
	 * Returns user object
	 * @param email - user's email- hopefully provided by Android; 
	 * @return Returns user object instantiated with data from webservice 
	 */
	public User getUser(String email){
		User user = new User("yoooser@mail.ru",con);
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
				it.setImageFileString(getTagValue("image_url", eElement));
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
	
	
	
	

}
