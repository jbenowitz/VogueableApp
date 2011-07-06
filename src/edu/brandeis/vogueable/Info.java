package edu.brandeis.vogueable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
public class Info extends Activity implements OnClickListener{
	private Provider prov;
	//private int toclick;
	//private int toclick2;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.more);
	    
	    RealProxy proxy = new RealProxy();
		Context context = this; 
	    prov = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
	    
	    Button buy = new Button(this);
	    Button add = new Button(this);
	    buy.setText("Buy!");
	    add.setText("Add to Wishlist!");
	    buy.setId(BIND_AUTO_CREATE);
	    add.setId(BIND_AUTO_CREATE);
	    //toclick = buy.getId();
	    //toclick2 = add.getId();
	    //add.setTextSize((float)20);
	    //buy.setTextSize((float)20);
	    buy.setPadding(10,10,10,10);
	    add.setPadding(10,10,10,10);
    	//ImageView iv;
    	//LinearLayout l;
    	LinearLayout l2 = new LinearLayout(context);
    	LinearLayout l3 = new LinearLayout(context);
    	//LinearLayout l4;
        
    	TextView tv = new TextView(context);
        
    	TextView tv2 = new TextView(context);
    	tv.setTextSize((float)15);
	    tv2.setTextSize((float)15);
        
    	//iv.setImageResource(R.drawable.icon);
        //iv.setAdjustViewBounds(true);
        //iv.setLayoutParams(new LayoutParams(-2, -1));
        
    	tv.setText(prov.getCurItem().getName());
        tv2.setText(prov.getCurItem().getPrice());
       
       // b.setId(position);
        //b.setText("Buy!");
        //b.setLayoutParams(new LayoutParams(-2,-2));
        l2.setOrientation(1);
        l2.addView(tv);
        l2.addView(tv2);
        l2.setLayoutParams(new LayoutParams(-2, -2));
        
        
       // l2.setVisibility(0);
       
        //l4.setLayoutParams(new LayoutParams(-2, -2));
        //l4.setOrientation(1);
        //l4.addView(l2);
        //l4.addView(b);
        l3.setLayoutParams(new LayoutParams(-2, -2));
        
        buy.setLayoutParams(new LayoutParams(-2, -2));
        
        add.setLayoutParams(new LayoutParams(-2, -2));
        l3.addView(add);
        l3.addView(buy);
        l2.setPadding(0, 0, 0, 20);
        l2.setPadding(50, 0, 40, 50);
        l2.addView(l3);
        //l.addView(b);
        //l.setPadding(5, 5, 0, 5);
        //l.addView(l3);
	     setContentView(l2);
	    
	    
	    
	    
	    /*Create click listeners for all buttons on page
	    View all = findViewById(R.id.content);
		all.setOnClickListener(this);
		View buy = findViewById(R.id.getit_button);
		buy.setOnClickListener(this);
		View add = findViewById(R.id.wishlist_button);
		add.setOnClickListener(this);*/
	}

	/**
	 * on click listeners for Info pop-up
	 */
	//@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*
		switch(v.getId()){
		
		case toclick :
			finish();
			break;
		
		case toclick2 :
			prov.getCurUser().addWishlist(prov.getCurItem());
       	 	Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show();
       	 	finish();
       	 	break;
		}
		
		case R.id.content :
			this.finish();
			break;
			
		case R.id.getit_button :
			break;
			
		case R.id.wishlist_button:

			AlertDialog.Builder wishquest = new AlertDialog.Builder(this);

			
			prov.getCurUser().addWishlist(prov.getCurItem());
       	 	Toast.makeText(Info.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
			/*AlertDialog.Builder wishquest = new AlertDialog.Builder(this);

	    	wishquest.setMessage("Added to your wishlist!  Would you like to view your wishlist?")
	    	         .setCancelable(false)
	    	         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	            	 startActivity(new Intent(Info.this, WishAct.class));
	    	            	 dialog.cancel();

	    	            	 finish();

	    	            	 finish();/*

	    	            	 }
	    	         	})
	    	         .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	             public void onClick(DialogInterface dialog, int id) {
	    	                  dialog.cancel();
	    	                  finish();
	    	             }
	    	         }).show();
		    break;
		}*/
		
	}
	
	
}
