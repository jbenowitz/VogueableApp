package edu.brandeis.vogueable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.monoid.web.Resty;
import us.monoid.web.XMLResource;

import android.content.Context;
import android.util.Log;


public class RealProxy extends AbstractProxy {
	
	User curruser;// user currently using app
	NodeList nList;
	Context con;
	private final static String TAG = "RealProxy";
	Provider provide;
	
	public RealProxy(Provider provide){
		this.provide = provide;
	}

		
	/**
	 * Gets a Batch of Items from Server in Specific Department;
	 * @param BatchSize - number of items wanted in batch;
	 * @param dept - desired department
	 * @return - an ArrayList of Items from give department; 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ArrayList<Item> getBatchbyDept(int BatchSize, String  dept) throws ParserConfigurationException, SAXException, IOException{
		
		ArrayList<Item> batch = new ArrayList<Item>();
		Resty r = new Resty();
		XMLResource usr1 = null;
		if(provide.getCurUser().getName()==null){
			Log.i(TAG, "no user");
			try {
				usr1 = r.xml("http://vogueable.heroku.com/find.xml?dept="+1+"&batch="+BatchSize+".xml");
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		}
		else{
	
			Log.i(TAG, "User id " + provide.getCurUser().getID());
			try {
				usr1 = r.xml("http://vogueable.heroku.com/find.xml?user="+provide.getCurUser().getID()+"&dept="+dept+"&batch="+BatchSize+".xml");
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
		}
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        String st = ""+usr1;
	        InputStream is = new ByteArrayInputStream(st.getBytes());
	        Document doc = dBuilder.parse(is);
	        doc.getDocumentElement().normalize();
	        nList = doc.getElementsByTagName("item");
			for(int k =0; k<nList.getLength(); k++){
		        Node nNode = nList.item(k);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Item it = new Item("it");
					Element eElement = (Element) nNode;
					if(getTagValue("img-url", eElement)!=null){
						it.setName(getTagValue("name", eElement));
						Log.d(TAG,"name" + it.getName());
						it.setImageFileString(getTagValue("img-url", eElement));
						it.setDescription(getTagValue("features", eElement));
						it.setLink(getTagValue("link-to-buy", eElement));
						it.setPrice(getTagValue("item-price", eElement));
						it.setBrand(getTagValue("brand", eElement));
						it.setID(getTagValue("id", eElement));
						batch.add(it);
					}
				}
			}
		return batch; 
	}
	/**
	 * Gets a batch of items with unspecified department;
	 * @param BatchSize - number of items needed
	 * @return - ArrayList of Items from random departments; 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ArrayList<Item> getBatch(int BatchSize) throws ParserConfigurationException, SAXException, IOException{
		ArrayList<Item> batch = new ArrayList<Item>();
		Resty r = new Resty();
		XMLResource usr1 = null;
	
		Log.i(TAG, "User id " + provide.getCurUser().getID());
		try {
			usr1 = r.xml("http://vogueable.heroku.com/find.xml?user="+provide.getCurUser().getID()+"&batch="+BatchSize+".xml");
		} catch (IOException e) {
			Log.e(TAG, e.toString());
		}
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        String st = ""+usr1;
        InputStream is = new ByteArrayInputStream(st.getBytes());
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        nList = doc.getElementsByTagName("item");
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
				batch.add(it);
			}
		}
		return batch; 
	}
	
	
	
	
}

