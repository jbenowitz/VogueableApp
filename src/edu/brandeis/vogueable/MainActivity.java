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
	TasteManager taste_manager=new TasteManager();
	ItemCursor item_cursor;
	Item currItem;
	ArrayList<Item> currents = new ArrayList<Item>();
	User user=new User("gaspar");
	

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
		currItem =new Item("NAME","http://ecx.images-amazon.com/images/I/813w6mGs3oL._SL1500_.jpg","description",100.00,"describe", tags,"link", "clothing");
		//user.getTasteManager().itemsNotUsed.add(currItem);
 		
 		
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
	 * method to return pop up with details about an item being viewed,
	 * Use this before we figure out how to get info the right way
	 * @author gasparobimba
	 * @param pos -image position in the viewer, used to get info about the image
	 * @return details string of detailed image information
	 */
	
	
	
	
	public void onClick(View v) {
		switch (v.getId()) {

		
		case R.id.like_button :
//			for(String tags: currItem.getTagList()){
//					if (!user.getTasteManager().tagCount.containsKey(tags)){

						//user.mytaste.likeFlavor(currItem.getTagList());
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Are you sure you want to like?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 Toast.makeText(MainActivity.this, "Liked!", Toast.LENGTH_SHORT).show();
	    	            	 }
	    	         	})
	    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                  dialog.cancel();
	    	             }
	    	         }).show();
	    	  //finish();*/
	    	break;
	    	
		case R.id.dislike_button:
			Toast.makeText(MainActivity.this, "disliked!", Toast.LENGTH_SHORT).show();
			break;
		
		case R.id.wishlist_button:
			AlertDialog.Builder wishquest = new AlertDialog.Builder(this);
	    	wishquest.setMessage("Added to your wishlist!  Would you like to view your wishlist?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 Toast.makeText(MainActivity.this, "Go to wishlist, when implemented", Toast.LENGTH_SHORT).show();
	    	            	 }
	    	         	})
	    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                  dialog.cancel();
	    	             }
	    	         }).show();
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
	/*
	 public class ImageAdapter extends BaseAdapter {
	       
	        private Context myContext;
	 
	        
	        private String[] myRemoteImages = {
	        		"http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41Hkj9aBsAL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/41JMUzALgpL._AA300_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/51Iykkkx5LL._SX342_.jpg",
	        		 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
	        };
	       
	        
	        public ImageAdapter(Context c) { this.myContext = c; }
	 
	        
	        public int getCount() { return this.myRemoteImages.length; }
	 
	        
	        public Object getItem(int position) { return position; }
	        public long getItemId(int position) { return position; }
	 
	       
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView i = new ImageView(this.myContext);
	 
	            try {
	                               
	                                URL aURL = new URL(myRemoteImages[position]);
	                                URLConnection conn = aURL.openConnection();
	                                conn.connect();
	                                InputStream is = conn.getInputStream();
	                               
	                                BufferedInputStream bis = new BufferedInputStream(is);
	                               
	                                Bitmap bm = BitmapFactory.decodeStream(bis);
	                                bis.close();
	                                is.close();
	                                
	                                i.setImageBitmap(bm);
	                        } catch (IOException e) {
	                                //i.setImageResource(R.drawable.error);
	                                Log.e("DEBUGTAG", "Remtoe Image Exception", e);
	                        }
	           
	                        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
	                        
	                        i.setLayoutParams(new Gallery.LayoutParams(150, 150));
	                        return i;}
	 
	        
	        public float getScale(boolean focused, int offset) {
                
            return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
        }
	        
	        
	    }
	
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

		case R.id.wishlist_label:   
			Toast.makeText(this, "Yulia will finish the wish list soon", Toast.LENGTH_LONG).show();
			// startActivity(new Intent(this, Settings.class));
			//break;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
			}).show();

		case R.id.return_categories: 
			startActivity(new Intent(this, CategoryChooser.class));
			break;

		}
		return true;
	}

}





