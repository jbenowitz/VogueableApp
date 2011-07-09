package edu.brandeis.vogueable;




import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This is the main activity for the Men app.
 * Asks the user to either log in or head into browsing
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
	    	  final AccountManager manager = AccountManager.get(this);
		      final Account[] accounts = manager.getAccounts();
		      if (accounts.length >=1){
		    	  User user = new User(accounts[0].name);
		    	  
		    	  AlertDialog.Builder welcome = new AlertDialog.Builder(this);
		    	  	welcome.setIcon(R.drawable.logobright);
		    	  	welcome.setTitle(" ");
		    	  	welcome.setMessage("Welcome, "+user.getName());
		    	  	welcome.setPositiveButton("Go!",new DialogInterface.OnClickListener() {
			    	             public void onClick(DialogInterface dialog, int id) {
			    			         startActivity(new Intent(Women.this, CategoryChooser.class));
			    	            	 dialog.cancel();
			    	             }
			    	         	})
			    	         .show();
		    	  	//Need to implement method to update server with user info
		    	  	
		      } else {
		    	  Toast.makeText(Women.this, "No account registered", Toast.LENGTH_SHORT).show();
		      } 
	    	 
		      	break; 
	      }
	   }
}
