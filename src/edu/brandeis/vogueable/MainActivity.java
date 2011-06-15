package edu.brandeis.vogueable;


import android.app.Activity;
import android.content.Context;
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
	         private Context mContext;

	         private Integer[] mImageIds = {
	                 R.drawable.item1,
	                 R.drawable.item2,
	                 R.drawable.item3,
	                 R.drawable.item4,
	                 R.drawable.item5,
	                 R.drawable.item6,
	                 R.drawable.item7,
	                 R.drawable.item8,
	                 R.drawable.item9,
	                 R.drawable.item10,
	                 R.drawable.item11,
	                 R.drawable.item12
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


