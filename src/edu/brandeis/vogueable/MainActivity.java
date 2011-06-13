package edu.brandeis.vogueable;


import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This is the main activity for the app.
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
	      
	      // Set up Gallery
	      Gallery g = (Gallery) findViewById(R.id.gallery);
	      g.setAdapter(new ImageAdapter(this));

	      g.setOnItemClickListener(new OnItemClickListener() {
	          public void onItemClick(AdapterView parent, View v, int position, long id) {
	              Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	          }
	      });
	     
	   }
	   
	   /**
	    * Image Adapter Class, used with Gallery
	    * Used in Android SDK on Gallery
	    * 
	    * @author Jackie
	    *
	    */
	     public class ImageAdapter extends BaseAdapter {
	         int mGalleryItemBackground;
	         private Context mContext;

	         private Integer[] mImageIds = {
	                 R.drawable.colorquizdress,
	                 R.drawable.bedofroses
	                // R.drawable.sample_3,
	                // R.drawable.sample_4,
	                // R.drawable.sample_5,
	                // R.drawable.sample_6,
	                // R.drawable.sample_7
	         };

	         public ImageAdapter(Context c) {
	             mContext = c;
	         }

	         public int getCount() {
	             return mImageIds.length;
	         }

	         public Object getItem(int position) {
	             return position;
	         }

	         public long getItemId(int position) {
	             return position;
	         }

	         public View getView(int position, View convertView, ViewGroup parent) {
	             ImageView i = new ImageView(mContext);

	             i.setImageResource(mImageIds[position]);
	             i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	             i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	             

	             return i;
	         }
	    }
	
	  
	    /**
	     * 
	      * @param v view 
	      */
	     public void onClick(View v) {
		   
		
	    	switch (v.getId()) { 
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
}


