package edu.brandeis.vogueable;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

/**
 * class that updates the server when a user logs into the app
 * cases: if the user is a new to the app: creates his online account profile with just his email, and initialises 
 * his wishlist and taste manager.
 * else if the user is already on the server, we set server ready to be updated when user changes his wishlist or taste manager
 * @author gaspar obimba
 *
 */
public class UpdateServer {

	private static final String TAG = "Vogueable";
	User user;
	//constructor- takes in a user object
	public UpdateServer(User user){
		this.user=user;   
	}
	/**
	 * attempts to login the current user onto the server
	 * @return true when user is registered already
	 */
	public boolean attemptLogin (){

		return false;

	}

	/**
	 * creates the current user's profile onto the server
	 */
	public void createProfile (){
		//This method  for HttpConnection  
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://vogueable.heroku.com/users");

		try {
			// Add user  data
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair(user.getName(), "new user"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			Log .d(TAG, "error on POSTing");

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			Log.d(TAG,response.toString());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}


	}
}
