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
		

		// Set up Gallery
		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		//g.setOnClickListener(this);

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				Toast.makeText(MainActivity.this, imageDetails(position) , Toast.LENGTH_LONG).show();
			
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
	public String imageDetails(int pos){
		String details="";
		switch(pos){
		case 0:details="Casual Chic Dress \n $72.79 \nJust for You & Retro Too! \nhttp://www.modcloth.com/Womens/Dresses/-Casual-Chic-Dress";
			break;
		case 1:details="Color Quiz Dress \n62.99 \nUnknown \nhttp://www.modcloth.com/Womens/Dresses/-Color-Quiz-Dress";break;
		case 2:details="Belle of the Book Fair Dress \n24.99 \nunknown \nhttp://www.modcloth.com/Womens/Dresses/-Belle-of-the-Book-Fair-Dress";break;
		case 11:details="Wild Stride Wedge\n$193.99\nhttp://www.modcloth.com/Womens/Shoes/Wedges/-Wild-Stride-Wedge\nUnknown";break;
		case 4:details="Amelie Tunic \n$98.40 \nhttp://www.shopbop.com/amelie-tunic-elie-tahari/vp/v=1/845524441882644.htm?folderID=2534374302060562&fm=other-shopbysize&colorId=12867 \nElie Tahari";break;
		case 5:details="Bed of Roses \n$82.00 \nhttp://www.helianthusny.com/Mink_Pink_Bed_of_Roses_Dress_p/mindre00001.htm \nMinkpink";break;
		case 6:details="D&G Floral Jeans \n$267.00 \nhttp://store.dolcegabbana.com/item/store/DG/tskay/9BEC955A/rr/1/cod10/42193267LI/ /nDolce and Gabbana";break;
		case 7:details="Medallion Pendant Necklace \n$55.00 \nhttp://www.shopbop.com/turquoise-brass-pendant-necklace-jadetribe/vp/v=1/845524441903665.htm \nJADETribe";break;
		case 8:details="Mini Bessie Dress \n$375.00 \nhttp://www.shopbop.com/mini-bessie-dress-temperley-london/vp/v=1/845524441872501.htm?folderID=2534374302029887&fm=sale-shopbysize&colorId=12192 /nTemperley London";break;
		case 9:details="Thakoon Sock Boots \n$388.50 \nhttp://www.shopbop.com/open-toe-high-heel-platform/vp/v=1/845524441878754.htm?folderID=2534374302029887&fm=sale-shopbysize&colorId=12054 /nGiuseppe Zanotti";break;
		case 10:details="True Grid Heel \n$234.99 \nhttp://www.modcloth.com/Womens/Shoes/Sandals/-True-Grid-Heel \nUnknown";break;
		}
		return details;
		
	}
	public void onClick(View v) {
		switch (v.getId()) {

		
		case R.id.browse_label :
	    	  Intent i = new Intent(this, MainActivity.class);
		       startActivity(i);
		break;
		case R.id.like_button :
			Toast.makeText(MainActivity.this, "Liked!", Toast.LENGTH_SHORT).show();
//			for(String tags: currItem.getTagList()){
//					if (!user.getTasteManager().tagCount.containsKey(tags)){

						//user.mytaste.likeFlavor(currItem.getTagList());
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Are you sure you want to like?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                // Men.this.finish();
	    	     			//Toast.makeText(this, "Yulia will finish the wish list soon", Toast.LENGTH_LONG).show();
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
		case R.id.wish_button:
		    Toast.makeText(MainActivity.this, "Added to Wishlist!", Toast.LENGTH_SHORT).show();
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

		case R.id.browse_title: 
			startActivity(new Intent(this, CategoryChooser.class));
			break;

		}
		return true;
	}

}





