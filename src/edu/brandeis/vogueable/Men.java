package edu.brandeis.vogueable;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * This is the main activity for the app.
 * @author gaspar obimba
 *
 */
public class Men extends Activity implements  android.view.View.OnClickListener{
	
	
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.initial);
	   
	      // Set up click listeners for the category buttons buttons
	      View nextButton = findViewById(R.id.browse_label);
	      nextButton.setOnClickListener(this);

	      View prevButton = findViewById(R.id.dress_label);
	      prevButton.setOnClickListener(this);
	      View shorts = findViewById(R.id.shorts_label);
	      shorts.setOnClickListener(this);
	      View pants = findViewById(R.id.pants_label);
	      pants.setOnClickListener(this);
	   }
	  // MainActivity m;
	  
	  
/**
 * On click of a button, a new intent is launched and the user can now access a browse page depending on the category she chose.
 * @param v view 
 */
	   //OnClickListener has one method in it called onClick( ), 
	   //so we have to add that method to our class as well
	   public void onClick(View v) {
	      switch (v.getId()) {
	     
	      case R.id.browse_label :
	    	  Intent i = new Intent(this, MainActivity.class);
		         startActivity(i);
		         break;
	      // do this for all buttons
//	      case R.id.shorts_label:
//	    	  
//	    	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
//	    	  builder.setMessage("Are you sure you want to chose the women?")
//	    	         .setCancelable(false)
//	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//	    	             public void onClick(DialogInterface dialog, int id) {
//	    	                 Men.this.finish();
//	    	            	
//	    	             }
//	    	         })
//	    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
//	    	             public void onClick(DialogInterface dialog, int id) {
//	    	                  dialog.cancel();
//	    	             }
//	    	         }).show();
//	    	  //finish();
//	         break;

	      }
	   }
}
