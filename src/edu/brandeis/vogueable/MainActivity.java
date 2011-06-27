package edu.brandeis.vogueable;




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
	TasteManager taste_manager=new TasteManager();
	ItemCursor item_cursor;
	Item currItem;
	User user=new User("gaspar");
	boolean itemliked=false, itemdisliked=false; //used to tell whether like or dislike button are pressed

	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Toast.makeText(MainActivity.this, "Slide for next item!", Toast.LENGTH_LONG).show();

		View likeButton = findViewById(R.id.like_button);
		likeButton.setOnClickListener(this);
		View dislikeButton = findViewById(R.id.dislike_button);
		dislikeButton.setOnClickListener(this);
		View wishlistButton = findViewById(R.id.wishlist_button);
		wishlistButton.setOnClickListener(this);
		View infoButton = findViewById(R.id.info_button);
		infoButton.setOnClickListener(this);
		
		
		

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
	public void like(){
		user.mytaste.likeFlavor(currItem.getTagList());
	}
	
	/**
	 * dislike the current item (subtract counts to hashtag list in taste manager)
	 * 
	 * @author Jackie
	 */
	public void dislike(){
		user.mytaste.dislikeFlavor(currItem.getTagList());
	}
	
	
	
	
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
	             TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
	             mGalleryItemBackground = a.getResourceId(
	                     R.styleable.HelloGallery_android_galleryItemBackground, 0);
	             a.recycle();
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





