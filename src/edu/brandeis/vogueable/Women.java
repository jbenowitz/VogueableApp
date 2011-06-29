package edu.brandeis.vogueable;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This is the activity for the Women app.
 * @author gaspar obimba
 *
 */
public class Women extends Activity implements  android.view.View.OnClickListener{
	
	
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.intro);
	   
	      // Set up click listeners for the category buttons buttons
	      View nextButton = findViewById(R.id.browse_label);
	      nextButton.setOnClickListener(this);
	      View prevButton = findViewById(R.id.login_label);
	      prevButton.setOnClickListener(this);

//	     
	   }
	  
	  
	   /**
	    * @author gasparobimba
	    * 
	    *initial screen that asks you to choose whether you want to login or go straight ahead and browse
	    *
	    * @param v view 
	    */
	    public void onClick(View v) {
	      switch (v.getId()) {
	     
	      case R.id.browse_label :
	    	  Intent i = new Intent(this, CategoryChooser.class);
		         startActivity(i);
		         break;
		         
	      
	      case R.id.login_label:
	    	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	  builder.setMessage("Are you sure you want to login?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                // Men.this.finish();
	    	            	
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
}
