package edu.brandeis.vogueable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	 * connects to web service
	 * @throws SAXException 
	 */
	public void connect(Context con) {
		
		Resty r = new Resty();
		XMLResource usr1 = null;
	
		try {
			usr1 = r.xml("http://vogueable.heroku.com/items.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        String st = ""+usr1;
	        InputStream is = new ByteArrayInputStream(st.getBytes());
	        
	        Document doc = dBuilder.parse(is);
	        doc.getDocumentElement().normalize();
	        nList = doc.getElementsByTagName("item");
			
		} catch (IOException e) {
			Log.e(TAG, "exception on r.xml");
		} catch (ParserConfigurationException e) {
			Log.e(TAG, e.toString());
		} catch (SAXException e) {
			Log.e(TAG, e.toString());
		}
		
		
		
	}
	
	
	/**
	 * populates the DeptItemCache datastructure
	 * 
	 */
//	public void populateDeptItemCache(){
//		if(!provide.getItemCache().hasData()){
//			for(String dept : provide.getCatList()){
//				//TODO itemcache.insertItems(getItems(BATCH_SIZE/departments.size(), dept));
//			}
//			provide.getItemCache().shuffle();
//		}
//	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Item it = new Item("it");
				Element eElement = (Element) nNode;
				it.setName(getTagValue("name", eElement));
				Log.d(TAG,"name" + it.getName());
				it.setImageFileString(getTagValue("img-url", eElement));
				it.setDescription(getTagValue("features", eElement));
				it.setLink(getTagValue("link-to-buy", eElement));
				it.setPrice(getTagValue("item-price", eElement));
				it.setCategory(getTagValue("category", eElement));
				it.setBrand(getTagValue("brand", eElement));
				it.addTag(getTagValue("fabric-type", eElement));

				items.add(it);
				
			}
		}
		return items;
	}
	
	public Item getNextItem(Item currentitem){
		Item nextit = new Item("next");
		int temp = new Random().nextInt(nList.getLength());
		//Node nNode = nList.item(temp);
	
		for (int k = 0; k < nList.getLength(); k++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
					nextit.setName(getTagValue("name", eElement));
					nextit.setImageFileString(getTagValue("img-url", eElement));
					nextit.setLink(getTagValue("link-to-buy", eElement));
					nextit.setPrice(getTagValue("item-price", eElement));
					nextit.setBrand(getTagValue("brand", eElement));
					nextit.addTag(getTagValue("fabric-type", eElement));
					nextit.setDescription(getTagValue("features", eElement));
					nextit.setCategory(getTagValue("category", eElement));
					nextit.addTag(getTagValue("brand", eElement));
					nextit.getTags();

				    break;
				
			}
		}
		return nextit; 
	}
	
	public ArrayList<Item> getBatchbyDept(int BatchSize, String  dept) throws ParserConfigurationException, SAXException, IOException{
		ArrayList<Item> batch = new ArrayList<Item>();
		Resty r = new Resty();
		XMLResource usr1 = null;
	
			Log.i(TAG, "User id " + provide.getCurUser().getID());
			try {
				usr1 = r.xml("http://vogueable.heroku.com/find.xml?user="+provide.getCurUser().getID()+"&dept="+dept+"&batch="+BatchSize+".xml");
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
	public ArrayList<Item> getBatch(int BatchSize) throws ParserConfigurationException, SAXException, IOException{
		//for now when things are deselected, will just pull dresses. TODO change this
		return getBatchbyDept(BatchSize,"1");
	}
	
}

