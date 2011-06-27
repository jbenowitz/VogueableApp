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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.PopupWindow;

/**
 * This is the main activity for the app.
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements  android.view.View.OnClickListener{

	protected static final String id = null;
	//instantiate objects
	ItemDB itemDB;
	
	ItemCursor item_cursor;
	Item currItem;

	ArrayList<Item> currents = new ArrayList<Item>();
	//Resources rr = this.getResources();
	//Context myContext = getApplicationContext();
	User user=new User("gaspar",this); 
	

	
	
	
	
	boolean itemliked=false, itemdisliked=false; //used to tell whether like or dislike button are pressed


	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Toast.makeText(MainActivity.this, "Tap for details! Slide for next item!", Toast.LENGTH_LONG).show();

		View likeButton = findViewById(R.id.like_button);
		likeButton.setOnClickListener(this);
		View dislikeButton = findViewById(R.id.dislike_button);
		dislikeButton.setOnClickListener(this);
		View closetButton = findViewById(R.id.wishlist_button);
		closetButton.setOnClickListener(this);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("heeey");
		currItem =new Item("NAME","http://ecx.images-amazon.com/images/I/813w6mGs3oL._SL1500_.jpg","description","100.00","describe", tags,"link", "clothing");
		
		user.getTasteManager().itemsNotUsed.add(currItem);
		
 		
 		
		//user.getTasteManager().itemsNotUsed.add(new Item("NAME","price", "1000", R.drawable.item2,"description", tags,"link", "clothing"));
 		//user.getTasteManager().itemsNotUsed.add(new Item("NAME","price", "1000", R.drawable.item3,"description", tags,"link", "clothing"));
		
		
		

		// Set up Gallery
		Gallery g = (Gallery) findViewById(R.id.gallery);
				
		g.setAdapter(new ImageAdapter(this));
		//g.setOnClickListener(this);

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				//Toast.makeText(MainActivity.this, imageDetails(position) , Toast.LENGTH_LONG).show();
			
			}				
		});
		
	}
	
	
	
	/**
	 * Like the current item (add counts to hashtag list in taste manager)
	 * 
	 * @author Jackie
	 */
	
	
	
	
	/**
	 * Deals with figuring out what buttons are pressed and what actions to take.
	 * 
	 */
	public void onClick(View v) {
		switch (v.getId()) {

		
		case R.id.like_button :
			
			if(itemliked){
				//dislike();
				itemliked=false;
			}
			else{
				//like();
				itemliked=true;
			}
			
						
			
	    	break;
	    	
	    	
		case R.id.dislike_button:
			
			if(itemdisliked){
				//like();
				itemdisliked=false;
			}
			else{
				//dislike();
				itemdisliked=true;
			}

			break;
			
			
		
		case R.id.wishlist_button:
			//Intent j = new Intent(this, WishAct.class);
	         //startActivity(j);
	         //break;

			
	        AlertDialog.Builder wishquest = new AlertDialog.Builder(this);
	    	//wishquest.setMessage("Added to your wishlist!  Would you like to view your wishlist?")
	    	         //wishquest.setCancelable(false)
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

		    
		    
		case R.id.info_button:
			Intent k = new Intent(this, Info.class);
	         startActivity(k);
	         break;

		
		}
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
   
	         private String[] myImages = {
	        		 
	                 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41Hkj9aBsAL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41JMUzALgpL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/51Iykkkx5LL._SX342_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	         };

	         public ImageAdapter(Context c) {
	             mContext = c;
	             TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
	             mGalleryItemBackground = a.getResourceId(
	                     R.styleable.HelloGallery_android_galleryItemBackground, 0);
	             a.recycle();
	         }

	         public int getCount() {
	             return myImages.length;
	         }

	         public Object getItem(int position) {
	             return position;
	         }

	         public long getItemId(int position) {
	             return position;
	         }
	         
	         public  Bitmap getBitmapFromURL(String src) {
	             try {
	                 Log.e("src",src);
	                 URL url = new URL(src);
	                 URLConnection connection = (URLConnection) url.openConnection();
	                 ((HttpURLConnection) connection).setRequestMethod("GET");
	                 connection.setDoInput(true);
	                 connection.connect();
	                 InputStream input = connection.getInputStream();
	                 Bitmap myBitmap = BitmapFactory.decodeStream(input);
	                 Log.e("Bitmap","returned");
	                 return myBitmap;
	             } catch (IOException e) {
	                 e.printStackTrace();
	                 Log.e("Exception",e.getMessage());
	                 return null;
	             }
	         }
             
	         public View getView(int position, View convertView, ViewGroup parent) {
	        	 System.out.print(position);
	             ImageView i = new ImageView(mContext);
	             Bitmap bimage=  getBitmapFromURL(myImages[position]);
	             
	            
	            
	            
	             i.setImageBitmap(bimage);
	             i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

	             i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);	
	             //myImages[position]= user.getTasteManager().getNextItem(currItem, null).getImageFileString();

	             i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);	 
	             i.setBackgroundResource(mGalleryItemBackground);

	             return i;
	         }
	    }
	
	  
	    /**
	     * 
	      * @param v view 
	      */
	    
	     
	     @Override
	     public boolean onCreateOptionsMenu(Menu menu) {
	         MenuInflater inflater = getMenuInflater();
	         inflater.inflate(R.menu.menu, menu);
	         return true;
	     }	

	/**
	 * 
	 * @param v view 
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.wishlist_menu:   
			//Toast.makeText(this, "Yulia will finish the wish list soon", Toast.LENGTH_LONG).show();
			// startActivity(new Intent(this, Settings.class));
			//break;
			/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you want to really see your wishlist?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {		
					// startActivity(new Intent(this, Wishlist.class));

				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			}).show();*/
			startActivity(new Intent(this, WishAct.class));
			break;
			

		case R.id.return_categories: 
			startActivity(new Intent(this, CategoryChooser.class));
			break;

		}
		return true;
	}

}





