package edu.brandeis.vogueable;



//import org.example.sudoku.Info;
//import org.example.sudoku.i;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This is the men's category activity for the app.
 * The class holds info about stylish apparel 
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements  android.view.View.OnClickListener
{

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
          Toast.makeText(MainActivity.this, "Tap for details! Slide for next item!", Toast.LENGTH_LONG).show();
          //Intent x = new Intent(this, Info.class);
	      //	startActivity(x);
	      
	    
//	      View nextButton = findViewById(R.id.browse_label);
//	      nextButton.setOnClickListener(this);
//	      View prevButton = findViewById(R.id.dress_label);
//	      prevButton.setOnClickListener(this);
//	      View shorts = findViewById(R.id.shorts_label);
//	      shorts.setOnClickListener(this);
//	      View pants = findViewById(R.id.pants_label);
//	      pants.setOnClickListener(this);
	      
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
	   public void onClick(View v) {
			switch (v.getId()) {
		     
		      case R.id.browse_label :
		    	  Intent i = new Intent(this, MainActivity.class);
			         startActivity(i);
			         break;
		      //case R.id.gallery:
			  //    Intent x = new Intent(this, Info.class);
			  //    	startActivity(x);
			  //   	break;
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
	    
	     
	     @Override
	     public boolean onCreateOptionsMenu(Menu menu) {
	         MenuInflater inflater = getMenuInflater();
	         inflater.inflate(R.menu.menu, menu);
	         return true;
	     }
	     @Override
	     public boolean onOptionsItemSelected(MenuItem item) {
	     	switch (item.getItemId()) {
	     	
	     	case R.id.wishlist_label:   
	     		Toast.makeText(this, "Yulia will finish the wish list soon", Toast.LENGTH_LONG).show();
	     		 //startActivity(new Intent(this, Wishlist.class));
	     		break;

	     	case R.id.browse_title: 
		    /*
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
	      	
	     		*/

	     	case R.id.browse_title: 
	     		Intent x = new Intent(this, Info.class);
	     		startActivity(x);
 
	     		
	     		//startActivity(new Intent(this, CategoryChooser.class));
		     	//	break;


	     		
	     	}
	     	return true;
		   }
		
		}
		




