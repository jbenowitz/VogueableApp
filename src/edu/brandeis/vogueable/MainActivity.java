package edu.brandeis.vogueable;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * This is the men's category activity for the app.
 * The class holds info about stylish apparel 
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements  android.view.View.OnClickListener{

	//instantiate objects
	 ItemDB itemDB;
	 TasteManager taste_manager;
	 ItemCursor item_cursor;
	 Item currItem;
	
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main);
	      
	      /*
	      //Set up objects
	      itemDB = new ItemDB();
	      taste_manager = new TasteManager(itemDB.getAllItem());
	      item_cursor = new ItemCursor(itemDB);
	      Item currItem = item_cursor.getCurrentItem();
	      */

	      // Set up click listeners for the next and prev buttons
	      View nextButton = findViewById(R.id.next_button);
	      nextButton.setOnClickListener(this);

	      View prevButton = findViewById(R.id.prev_button);
	      prevButton.setOnClickListener(this);
	      
	      
	      //Display image
	      //this.displayImage(currItem);
	      this.displayImage();
	      
	      //imgView = (ImageView)findViewById(R.id.imageviewer);
	      //imgView = setImageResource
	     
	   }
	   
	  
	  
/**
 * 
 * @param v view 
 */
	public void onClick(View v) {
		   
		
		switch (v.getId()) { 
		case R.id.next_button:
	    	 //taste_manager.getNextItem(currItem)
			displayNextImage();
	         break;
	      
		case R.id.prev_button:
			//item_cursor.getPrevItem();
			displayImage();
			break;
	   /*   
		case R.id.like_button:
			//adds the current item to the LikeList and also updates your flavors to predict what you have next 
			taste_manager.likeFlavor(currItem.getTagList());
			break;	
	    	  
		case R.id.dislike_button:
			//adds the current item to the DisLikeList and also updates your flavors to predict what you have next 
			taste_manager.dislikeFlavor(currItem.getTagList());
			break;
	 */  	  
		    	  
		}	
	}//end onClick view
	
	/**
	 * Fetch image from the DB and display
	 * @param item
	 * @return item's name from the DB
	 */
	//public void displayImage(Item item)
	public void displayImage(){
		//Item it = new Item ("/Users/Jackie/Documents/2010-2011/Summer_2011/JBS/VogueableApp/res/drawable-ldpi/itemdb/bedofroses.jpg","res/drawable/itemdb/bedofroses.jpg", "hi", 20, "hi", null, "hi" );
		//item_cursor.setCurrentItem(it);
		//Bitmap bits = BitmapFactory.decodeFile(it.getName());
		//Bitmap bits = BitmapFactory.decodeFile(item.getName());
		ImageView imag = (ImageView)findViewById(R.id.imageviewer);
		imag.setImageResource(R.drawable.bedofroses);	
	}
	public void displayNextImage(){
		ImageView imag = (ImageView)findViewById(R.id.imageviewer);
		imag.setImageResource(R.drawable.colorquizdress);	
	}

}
