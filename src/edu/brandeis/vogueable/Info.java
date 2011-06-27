package edu.brandeis.vogueable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
public class Info extends Activity implements OnClickListener{
	   
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.info);
	    
	    View all = findViewById(R.id.content);
		all.setOnClickListener(this);
		View buy = findViewById(R.id.getit_button);
		buy.setOnClickListener(this);
		View add = findViewById(R.id.wishlist_button);
		add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.content :
			this.finish();
		case R.id.getit_button :
			break;
		case R.id.wishlist_button:
			AlertDialog.Builder wishquest = new AlertDialog.Builder(this);
	    	wishquest.setMessage("Added to your wishlist!  Would you like to view your wishlist?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 startActivity(new Intent(Info.this, WishAct.class));
	    	            	 dialog.cancel();
	    	            	 finish();
	    	            	 }
	    	         	})
	    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                  dialog.cancel();
	    	                  finish();
	    	             }
	    	         }).show();
		    break;
		}
	}
	
	
}
