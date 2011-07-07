package edu.brandeis.vogueable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.more);
	    
	    RealProxy proxy = new RealProxy();
		Context context = this; 
	    prov = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
	    
	    Button buy = new Button(this);
	    Button add = new Button(this);
	    //buy.findViewById(R.id.info_buy);
	    //add.findViewById(R.id.info_wish);
	    buy.setText("Buy!");
	    add.setText("Add to Wishlist!");
	    //buy.setId(BIND_AUTO_CREATE);
	    //add.setId(BIND_AUTO_CREATE);
	    //add.setTextSize((float)20);
	    //buy.setTextSize((float)20);
	    //buy.setPadding(10,10,10,10);
	    //add.setPadding(10,10,10,10);
    	
    	TextView tv = new TextView(context); 
    	tv.setTextSize((float)20);
        tv.setText(prov.getCurItem().getName());
    	//tv.setPadding(0, 0, 0, 10);
        
        TextView tv2 = new TextView(context);
        tv2.setTextSize((float)25);
        tv2.setText(prov.getCurItem().getPrice());
        //tv2.setPadding(130, 0, 0, 20);
        
        LinearLayout l3 = new LinearLayout(context);
        l3.setPadding(60,20,60,0);
        l3.addView(add,new LayoutParams(-2, -1));
        l3.addView(buy,new LayoutParams(-2, -1));
        
        LinearLayout l2 = new LinearLayout(context);
        l2.setHorizontalGravity(17);
        l2.setOrientation(1);
        l2.addView(tv);
        l2.addView(tv2);
        l2.setPadding(20, 20, 20, 20);
		l2.setBackgroundColor(Color.argb(50, 255, 255, 255));
		l2.addView(l3,new LayoutParams(-1, -1));
	    
        setContentView(l2,new LayoutParams(-2, -1));
	    
	    //buy.setOnClickListener(this);
		//add.setOnClickListener(this);
		
		//buy.setClickable(true);
		//add.setClickable(true);
	}

	/**
	 * on click listeners for Info pop-up
	 */
	//@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.info_buy :
			 if (prov.getCurItem().getLink() != null) {
			    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(prov.getCurItem().getLink())));
			    } else {
			    	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/l/1036592/ref=nb_sb_noss")));
			    }
			this.finish();
			break;
		
		case R.id.info_wish :
			prov.getCurUser().addWishlist(prov.getCurItem());
       	 	Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show();
       	 	this.finish();
       	 	break;
		}	
	}	
}
