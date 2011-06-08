package edu.brandeis.vogueable;


import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

/**
 * This is the main activity for the app.
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements  android.view.View.OnClickListener{

	//instantiate objects
	 ItemDB itemDB = new ItemDB();
	 TasteManager taste_manager =new TasteManager(itemDB.getAllItem());
	 ItemCursor item_cursor = new ItemCursor(itemDB);
	
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main);

	      // Set up click listeners for the next and prev buttons
	      View nextButton = findViewById(R.id.next_button);
	      nextButton.setOnClickListener(this);

	      View prevButton = findViewById(R.id.prev_button);
	      prevButton.setOnClickListener(this);
	     
	   }
	   
	  
	  
/**
 * 
 * @param v view 
 */
	public void onClick(View v) {
		   
		Item currItem = item_cursor.getCurrentItem();
		switch (v.getId()) { 
		case R.id.next_button:
	    	 taste_manager.getNextItem(currItem);
	         break;
	      
		case R.id.prev_button:
			item_cursor.getPrevItem();
			break;
	      
		case R.id.like_button:
			//adds the current item to the LikeList and also updates your flavors to predict what you have next 
			taste_manager.likeFlavor(currItem.getTagList());
			break;	
	    	  
		case R.id.dislike_button:
			//adds the current item to the DisLikeList and also updates your flavors to predict what you have next 
			taste_manager.dislikeFlavor(currItem.getTagList());
			break;
		    	  
		    	  
		}	
	}//end onClick view
	
	/**
	 * Fetch image from the DB and display
	 * @param item
	 * @return item's name from the DB
	 */
	public String displayImage(Item item){
		return item.getName();
	}

}
