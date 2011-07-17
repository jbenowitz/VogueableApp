package edu.brandeis.vogueable;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the main activity for the app.
 * @author gaspar obimba
 *
 */
public class MainActivity extends Activity implements android.view.View.OnClickListener{

	protected static final String id = null;
	private static final String TAG = "MainActivity";
	ImageButton likebutton;
	ImageButton dislikebutton;
	TextView namelandtext, pricelandtext;
	
    Provider provide; 
    Bundle currCat;
    LikeManager likeMan;


	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		provide = Provider.instance(null, "AndroidUserName", this, "item from pref");
		
		setContentView(R.layout.main);
		//Toast.makeText(MainActivity.this, "Slide for next item!", Toast.LENGTH_LONG).show();
		
		//LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		//View layout = inflater.inflate(R.layout.help,(ViewGroup) findViewById(R.id.group));

		AlertDialog.Builder builder;
		AlertDialog alertDialog;

		LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.help,
		                               (ViewGroup) findViewById(R.id.group));
		layout.setBackgroundColor(new Color().argb(75, 255, 255, 255));
		builder = new AlertDialog.Builder(this);
		builder.setView(layout);
		alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();
		
		/*Toast t = new Toast(this);
		View tv = LayoutInflater.from(this).inflate(R.layout.help,(ViewGroup) findViewById(R.id.group));
		t.setView(tv);
		t.setDuration(Toast.LENGTH_LONG);
		t.show();*/
		
		/*
		 * Set up liking and disliking an item (with LikeManager)
		 */
		View likeButton = findViewById(R.id.like_button);
		likeButton.setOnClickListener(this);
		likebutton = (ImageButton) likeButton;
		
		View dislikeButton = findViewById(R.id.dislike_button);
		dislikeButton.setOnClickListener(this);
		dislikebutton = (ImageButton) findViewById(R.id.dislike_button);
		
		likeMan = new LikeManager(likebutton, dislikebutton, this, provide);

		
		/*
		 * Buttons onClick Listeners and backgrounds
		 */
		View closetButton = findViewById(R.id.wishlist_button);
		closetButton.setOnClickListener(this);
		
		View infoButton = findViewById(R.id.info_button);
		infoButton.setOnClickListener(this);
	
		//Sets landscape text
		namelandtext = (TextView) findViewById(R.id.name);
		pricelandtext = (TextView) findViewById(R.id.price);
		namelandtext.setText(provide.getCurItem().getName());
		pricelandtext.setText(provide.getCurItem().getPrice());
		
		View buyButton = findViewById(R.id.buy_button);
		buyButton.setOnClickListener(this);
		

		provide.getCurTM().itemsUsed.add(provide.getCurItem()); 
		
		// Set up Gallery
		Gallery g = (Gallery) findViewById(R.id.gallery);		
		g.setAdapter(new ImageAdapter(this, provide, namelandtext, pricelandtext, likebutton, dislikebutton));	
		
	}
	
	
	/**
	 * Deals with figuring out what buttons are pressed and what actions to take.
	 * 
	 */
	public void onClick(View v) {
		switch (v.getId()) {

		//Like Button onClick
		case R.id.like_button :
			Log.e(TAG, "clicked like_button");
			likeMan.like();			
	    	break;
	    	
	    //Dislike button onClick
		case R.id.dislike_button:
			likeMan.dislike();
			break;
			
		//Wishlist Button on click	
		case R.id.wishlist_button:

	        AlertDialog.Builder wishquest = new AlertDialog.Builder(this);
	    	         wishquest
	    	         .setPositiveButton("Add to wishlist", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 provide.getCurUser().addWishlist(provide.getCurItem());
	    	            	 Toast.makeText(MainActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
	    	            	 dialog.cancel();
	    	             }
	    	         	})
	    	         .setNegativeButton("View wishlist", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 startActivity(new Intent(MainActivity.this, WishAct.class));
	    	             }
	    	         }).show().setCanceledOnTouchOutside(true);
	    	         
		    break;
		    
		//Info button on click    
		case R.id.info_button:
			
			AlertDialog.Builder info = new AlertDialog.Builder(this);
			info
			.setMessage(provide.getCurItem().getName()+" "+provide.getCurItem().getPrice()+" "+provide.getCurItem().getCategoryTag())
			.setPositiveButton("Add to wishlist", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					provide.getCurUser().addWishlist(provide.getCurItem());
					Toast.makeText(MainActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
					dialog.cancel();
				}
			})
			.setNegativeButton("Buy", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					if (provide.getCurItem().getLink() != null) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(provide.getCurItem().getLink())));
					} else {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/l/1036592/ref=nb_sb_noss")));
					}
				}
			})
			.show().setCanceledOnTouchOutside(true);
			break;

		case R.id.buy_button:
	
		    if (provide.getCurItem().getLink() != null) {
		    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(provide.getCurItem().getLink())));
		    } else {
		    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/l/1036592/ref=nb_sb_noss")));
		    }
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
	   

}





