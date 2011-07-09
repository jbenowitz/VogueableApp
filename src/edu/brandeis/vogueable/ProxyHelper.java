package edu.brandeis.vogueable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import us.monoid.web.Resty;
import us.monoid.web.XMLResource;
import android.content.Context;
import android.util.Log;

/**
 * helper class for the Proxy
 * loads in xml format: Items, Users, Wishlists
 * @author gasparobimba
 *
 */
public class ProxyHelper {

	Resty 	userResty = new Resty(),	//users resty
			itemResty = new Resty(),	//items resty
			wishlistResty=new Resty();	//wishlists resty
	
	XMLResource serverItems = null,	//items from the server
				serverUsers = null,	//users from the server
				serverWishlists=null;//wishlists from server
	
	User curruser;					// user currently using app
	NodeList nListItems,nListUsers,nListWishlists;
	Context con;
 
	private final static String TAG = "VogueableRealProxy";


	public ProxyHelper(){

		/*LOAD ITEMS FROM SERVER in xml format*/
		try {
			serverItems = itemResty.xml("http://vogueable.heroku.com/items.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			String st = ""+serverItems;
			InputStream is = new ByteArrayInputStream(st.getBytes());

			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			nListItems = doc.getElementsByTagName("item");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "exception on itemResty.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*LOAD USERS FROM SERVER in xml format*/
		try {
			serverUsers = userResty.xml("http://vogueable.heroku.com/users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			String st = ""+serverUsers;
			InputStream is = new ByteArrayInputStream(st.getBytes());

			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			nListUsers = doc.getElementsByTagName("user");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "exception on userResty.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	/*LOAD WISHLISTS FROM SERVER in xml format*/
	try {
		serverWishlists = wishlistResty.xml("http://vogueable.heroku.com/wishlists.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		String st = ""+serverWishlists;
		InputStream is = new ByteArrayInputStream(st.getBytes());

		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		nListWishlists = doc.getElementsByTagName("wishlist");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Log.e(TAG, "exception on wishResty.xml");
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
