package edu.brandeis.vogueable;




import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import us.monoid.web.Resty;
import us.monoid.web.XMLResource;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * This is the main activity for the Men app.
 * Asks the user to either log in or head into browsing
 * @author gaspar obimba
 *
 */
public class Men extends Activity implements  android.view.View.OnClickListener{
	private static String TAG = "Men Starting Activity";
	User user = new User(null);

	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.intro);

	      // Set up click listeners for the category buttons buttons
	      View nextButton = findViewById(R.id.browse_label);
	      nextButton.setOnClickListener(this);
	      View prevButton = findViewById(R.id.login_label);
	      prevButton.setOnClickListener(this);  
	   }


	   /**
	    * @author gasparobimba
	    * 
	    *initial screen that asks you to choose whether you want to login or go straight ahead and browse
	    *
	    * @param v view 
	    */
	   public void onClick(View v) {
	      switch (v.getId()) {

	      case R.id.browse_label :
	    	  Intent i = new Intent(this, CategoryChooser.class);
	    	  i.putExtra("currUserID", user.getID());
	    	  i.putExtra("currUserName", user.getName());
		      startActivity(i);
		      break;

	      case R.id.login_label:
	    	  Log.d(TAG, "Login button pressed");
	    	  

	    	  final AccountManager manager = AccountManager.get(Men.this);
		      final Account[] accounts = manager.getAccounts();
		      if (accounts.length >=1){
		    	  user = new User(accounts[0].name);

		    	  //Check to see if user in database
		    	  //if in database, get information from user
		    	  String temp = checkUser(user.getName());
		    	  if(!temp.equals(null)){
		    		  Log.d(TAG, "user exists in database " + user.getName());
		    		  user.setID(temp);
		    		  //TODO Pull TasteManager and WishList
		    	  }
		    	  //else add to database
		    	  else{
		    		  Log.d(TAG, "user being added to database");
		    		  addUser(user);
		    		  user.setID(checkUser(user.getName()));
		    	  }

		    	  AlertDialog.Builder welcome = new AlertDialog.Builder(Men.this);
		    	  	welcome.setIcon(R.drawable.logobright);
		    	  	welcome.setTitle(" ");
		    	  	welcome.setMessage("Welcome, " + user.getName());
		    	  	welcome.setPositiveButton("Go!",new DialogInterface.OnClickListener() {
			    	             public void onClick(DialogInterface dialog, int id) {
			    	            	 Intent i = new Intent(Men.this, CategoryChooser.class);
			    	            	 i.putExtra("currUserID", user.getID());
			    	            	 i.putExtra("currUserName", user.getName());
			    			         startActivity(i);
			    	            	 dialog.cancel();
			    	             }
			    	         	})
			    	         .show();
		      } else {
		    	  Toast.makeText(Men.this, "No account registered", Toast.LENGTH_SHORT).show();
		      } 

		      	break; 
	      }
	   }


	   /**
	    * Adds a given user to the database
	    * @param user
	    */
	    private void addUser(User user) {
	    	HttpPost httppost = new HttpPost("http://vogueable.heroku.com/users");

	    	try {
	    	  List <NameValuePair> nvp = new ArrayList<NameValuePair>();
			  nvp.add(new BasicNameValuePair("user[user_name]", user.getName()));
			  nvp.add(new BasicNameValuePair("user[email]", user.getName()));
			  //Add the e-mail address
			  httppost.setEntity(new UrlEncodedFormEntity(nvp));

			  //Execute HTTP Post Request
			  HttpClient httpclient = new DefaultHttpClient();
			  HttpResponse response = httpclient.execute(httppost);
			  Log.d(TAG, "response made");
	    	}catch (ClientProtocolException e){
	    		Log.e(TAG, "Client Protocol Exception");
	    	}catch(IOException e){
	    		Log.e(TAG, "IO Exception");
	    	}
	    }


	    /**
	     * Checks to see if a user is already in the database
	     * For now, pulls all the users on the database and checks to see if phone user is on there
	     * @param name
	     * @return
	     */
	    private String checkUser(String name){
	    	Log.d(TAG, "checking if this user exists " + name);
	    	Resty r = new Resty();
			XMLResource usr1 = null;
			NodeList nList = null;
			HashMap<String, String> users = new HashMap<String, String>();

			try {
				usr1 = r.xml("http://vogueable.heroku.com/users.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		        String st = ""+usr1;
		        InputStream is = new ByteArrayInputStream(st.getBytes());

		        Document doc = dBuilder.parse(is);
		        doc.getDocumentElement().normalize();
		        nList = doc.getElementsByTagName("user");

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
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					users.put(getTagValue("email", eElement), getTagValue("id", eElement));
					Log.d(TAG,"name" + users.get(temp));
				}
			}

			//returns their ID if they exist, otherwise null
	    	if(users.keySet().contains(name)){
	    		return users.get(name);
	    	}
	    	else{
	    		return null;
	    	}
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