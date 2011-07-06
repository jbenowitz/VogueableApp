package edu.brandeis.vogueable;




import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This is the main activity for the app.
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements  android.view.View.OnClickListener{

	protected static final String id = null;

	ImageButton likebutton;
	ImageButton dislikebutton;
	
	Item[] currents = {new Item("baby1"), new Item("baby1"), new Item("baby2"), new Item("baby3"), new Item("baby4")};
	boolean itemliked=false, itemdisliked=false; //used to tell whether like or dislike button are pressed
    Provider provide; 
    String link="http://www.vogueable.heroku.com/";


	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RealProxy proxy = new RealProxy();
		Context context = this; 
		provide = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
		
		setContentView(R.layout.main);
		Toast.makeText(MainActivity.this, "Tap for details! Slide for next item!", Toast.LENGTH_LONG).show();

		/*
		 * Buttons onClick Listeners and backgrounds
		 */
		View likeButton = findViewById(R.id.like_button);
		likeButton.setOnClickListener(this);
		likebutton = (ImageButton) likeButton;
		
		View dislikeButton = findViewById(R.id.dislike_button);
		dislikeButton.setOnClickListener(this);
		dislikebutton = (ImageButton) findViewById(R.id.dislike_button);
		
		View closetButton = findViewById(R.id.wishlist_button);
		closetButton.setOnClickListener(this);
		
		View infoButton = findViewById(R.id.info_button);
		infoButton.setOnClickListener(this);
		
		View buyButton = findViewById(R.id.buy_button);
		buyButton.setOnClickListener(this);
		

		provide.getCurUser().getTasteManager().itemsNotUsed.add(provide.getCurItem()); 
		
		// Set up Gallery
		Gallery g = (Gallery) findViewById(R.id.gallery);		
		g.setAdapter(new ImageAdapter(this));	
	}
	
	
	
	/**
	 * Like the current item (add counts to hashtag list in taste manager)
	 * 
	 * @author Jackie
	 */
    public void like(){
	provide.getCurTM().likeFlavor(provide.getCurItem().getTagList());
    }

    
    
    /**
	 * Dislike the current item (subtract counts to hashtag list in taste manager)
	 * 
	 * @author Jackie
	 */
    public void dislike(){
	provide.getCurTM().dislikeFlavor(provide.getCurItem().getTagList());
    }

	
    /**
     * Greys the 'like' button
     * 
     * @author Jackie
     */
    public void greyLike(){
    	likebutton.setImageDrawable(getResources().getDrawable(R.drawable.approvegrey));
    }
    
    
    /**
     * Greens the 'like' button
     * 
     * @author Jackie
     */
    private void greenLike() {
		likebutton.setImageDrawable(getResources().getDrawable(R.drawable.approvegreen));
	}
    
    
    /**
     * Greys the 'dislike' button
     * 
     * @author Jackie
     */
    private void greyDislike() {
		dislikebutton.setImageDrawable(getResources().getDrawable(R.drawable.disapprovegrey));
	}
    
    
    /**
     * Reds the 'dislike' button
     * 
     * @author Jackie
     */
    private void redDislike() {
		dislikebutton.setImageDrawable(getResources().getDrawable(R.drawable.disapprovered));
	}
	
	/**
	 * Deals with figuring out what buttons are pressed and what actions to take.
	 * 
	 */
	public void onClick(View v) {
		switch (v.getId()) {

		//Like Button onClick
		case R.id.like_button : 
			
			if(itemliked){
				//dislike();
				itemliked=false;
				greyLike();
			}
			else{
				//like();
				itemliked=true;
				greenLike();
			}
						
	    	break;
	    	
	    //Dislike button onClick
		case R.id.dislike_button:
			
			if(itemdisliked){
				//like();
				itemdisliked=false;
				greyDislike();
				
			}
			else{
				//dislike();
				itemdisliked=true;
				redDislike();
			}

			break;
			
		//Wishlist Button on click	
		case R.id.wishlist_button:

	        AlertDialog.Builder wishquest = new AlertDialog.Builder(this);
	    	         wishquest
	    	         .setPositiveButton("Add to wishlist", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 Toast.makeText(MainActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
	    	            	 dialog.cancel();
	    	             }
	    	         	})
	    	         .setNegativeButton("View wishlist", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 startActivity(new Intent(MainActivity.this, WishAct.class));
	    	             }
	    	         }).show();
	    	         
		    break;
		    
		//Info button on click    
		case R.id.info_button:
			Intent k = new Intent(this, Info.class);
	         startActivity(k);
	         
	         break;
	         
		case R.id.buy_button:
			Intent purchase = new Intent(this, PurchaseItem.class);
			Log.d(provide.getCurItem().getLink(), "is this right");
			//pass curr item attributes to the new intent
			purchase.putExtra("URL", link/*provide.getCurItem().getImageFileString()*/);
			startActivity(purchase);

			break;
		}
	}


	
	/**
	 *   When clicking the physical menu button, inflates the two options 
	 *   within the menu.xml
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}	

	/**
	 * Used with inflator above(?)
	 * 
	 * @param v view 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.wishlist_menu:   
			startActivity(new Intent(this, WishAct.class));
			break;
			

		case R.id.return_categories: //Return to categories
			startActivity(new Intent(this, CategoryChooser.class));
			break;

		}
		return true;
	}
	    
	   
	   /**
	    * Image Adapter Class, used with Gallery
	    * Used in Android SDK on Gallery
	    * 
	    * @author Jackie
	    *
	    */
	     public class ImageAdapter extends BaseAdapter {
	         private Context mContext;
	         int mGalleryItemBackground;
   
	         //Array of image URLs to be used within gallery
	         private String[] myImages = {
	                 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41Hkj9aBsAL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41JMUzALgpL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/51Iykkkx5LL._SX342_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	         };

	         //Constructor that sets up the Image Adapter (what is inside the Gallery)
	         public ImageAdapter(Context c) {
	             mContext = c;
	             TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
	             mGalleryItemBackground = a.getResourceId(
	                     R.styleable.HelloGallery_android_galleryItemBackground, 0);
	             a.recycle();
	             
	         }

	         //Returns count of images array
	         public int getCount() {
	             return 1000000;
	         }

	         //returns the item in a certain position
	         public Object getItem(int position) {
	             return position;
	         }

	         //gets the itemID of a certain position
	         public long getItemId(int position) {
	             return position;
	         }
	         
	         //creates a bitmap of the images from given URL
	         public  Bitmap getBitmapFromURL(String src) {
	             try {
	                 Log.e("src",src);
	                 URL url = new URL(src);
	                 URLConnection connection = (URLConnection) url.openConnection();
	                 connection.connect();
	                 InputStream input = connection.getInputStream();
	                 BufferedInputStream bis = new BufferedInputStream(input);
	                 Bitmap myBitmap = BitmapFactory.decodeStream(bis);
	                 Log.e("Bitmap","returned");
	                 return myBitmap;
	             } catch (IOException e) {
	                 e.printStackTrace();
	                 Log.e("Exception",e.getMessage());
	                 return null;
	             }
	         }
             
	         /**
	          * Creates the view of the images.
	          */
	         public View getView(int position, View convertView, ViewGroup parent) {
	        	 //Finds the next position of items
	        	 position = position % myImages.length;
	        	 if (position < 0)
	        	     position = position + myImages.length;
	        	 System.out.print(position); //TODO CAN WE DELETE THIS!?
	        	 
	        	 //Sets the view context image
	             ImageView i = new ImageView(mContext);
	             Bitmap bimage=  getBitmapFromURL(myImages[position]);
	             i.setImageBitmap(bimage);
	             
	             //Sets the current item to be referenced by other classes in the provider
	             provide.setCurItem(currents[position]);	
	             link=provide.getCurItem().getLink();

	            //scales the images accordingly
	             i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);	
	             myImages[position]= provide.getCurTM().getNextItem(provide.getCurItem(), null).getImageFileString();
	             currents[position]=provide.getCurTM().getNextItem(provide.getCurItem(), null);
	             //link=currents[position].getLink();
	             i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);	 
	             
	             
	             //Set landscape or portrait gallery/image size
	             Gallery.LayoutParams galayout;
	             if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
	            	 galayout = new Gallery.LayoutParams(200,200);
	             }
	             else{
	            	 galayout = new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	             }
	             i.setLayoutParams(galayout);

	             //sets the border
	             i.setBackgroundResource(mGalleryItemBackground);

	             return i;
	         }
	     }
}





