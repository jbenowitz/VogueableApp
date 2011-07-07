package edu.brandeis.vogueable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public class Helper extends BaseAdapter {
    private Context mContext;
    private Provider pro;

    public Helper(Context c,Provider pro) {
        mContext = c;
        this.pro=pro;

        // get ref to view for the whole row in the list
        // get ref to the view within that for the image
        // get ref for the view within that for the title
    }

    public int getCount() {
        return pro.getCurUser().wishlist.showWishlist().size();
    }

    public Object getItem(int position) {
        return pro.getCurUser().wishlist.showWishlist().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        
    	LinearLayout l;
    	LinearLayout l2;
    	LinearLayout l3;
    	LinearLayout l4;
    	LinearLayout l5;
    	
    	Button b;
        Button b2;
    	
        ImageView iv;
    	
        TextView tv;
        TextView tv2;
        
        //View v;
        // set image corresponding to item 'position' at the stored image view instance var
        // set the text corresonding to item 'position' into the stored title view
        // return the view for the whole row
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	
        	l = new LinearLayout(mContext);
        	l2 = new LinearLayout(mContext);
        	l3 = new LinearLayout(mContext);
        	l4 = new LinearLayout(mContext);
        	l5 = new LinearLayout(mContext);
        	
        	b = new Button(mContext);
        	b2 = new Button(mContext);
            
        	iv = new ImageView(mContext);
        	
        	tv = new TextView(mContext);
        	tv2 = new TextView(mContext);
        	//v = new View(mContext);
        
        } else {
        	
        	l = (LinearLayout) convertView;
        	l2 = (LinearLayout) convertView;
        	l3 = (LinearLayout) convertView;
        	l4 = (LinearLayout) convertView;
        	l5 = (LinearLayout) convertView;
        	
        	b = (Button) convertView;
        	b2 = (Button) convertView;
        	
        	iv = (ImageView) convertView;
        	
        	tv = (TextView) convertView;
        	tv2 = (TextView) convertView;
        	//v = (View)convertView;
        }
        //iv.setImageResource(R.drawable.icon);
        
        try {
            Log.e("src",pro.getCurUser().wishlist.showWishlist().get(position).getImageFileString());
            URL url = new URL(pro.getCurUser().wishlist.showWishlist().get(position).getImageFileString());
            URLConnection connection = (URLConnection) url.openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(input);
            Bitmap myBitmap = BitmapFactory.decodeStream(bis);
            iv.setImageBitmap(myBitmap);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            iv.setImageResource(R.drawable.icon);
        }
        
        iv.setAdjustViewBounds(true);
        iv.setMaxWidth(160);
        iv.setMinimumWidth(160);
        
        tv.setText(pro.getCurUser().wishlist.showWishlist().get(position).getName());
        tv2.setText(pro.getCurUser().wishlist.showWishlist().get(position).getPrice());
        
        b.setText("Buy!");
        b2.setText("Remove");
        
        l2.setOrientation(1);
        l2.setPadding(6, 0, 6, 0);
        l2.addView(tv);
        l2.addView(tv2);
        
        l5.setHorizontalGravity(5);
        l5.addView(b,new LayoutParams(-2,-2));
        l5.addView(b2,new LayoutParams(-2,-2));
        
        l4.setOrientation(1);
        l4.addView(l2,new LayoutParams(-2, -2));
        l4.addView(l5,new LayoutParams(-1,-2));
        
        l3.setVerticalGravity(16);
        l3.addView(iv,new LayoutParams(-2, -2));
        l3.addView(l4,new LayoutParams(-2, -2));
        
        l.setPadding(5, 5, 0, 5);
        l.addView(l3,new LayoutParams(-2, -1));
        
        return l;

        
    }
}