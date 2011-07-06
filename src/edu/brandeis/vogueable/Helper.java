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
    private View cellView;
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
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Button b;
        Button b2;
    	ImageView iv;
    	LinearLayout l;
    	LinearLayout l2;
    	LinearLayout l3;
    	LinearLayout l4;
    	LinearLayout l5;
        TextView tv;
        TextView tv2;
        //View v;
        // set image corresponding to item 'position' at the stored image view instance var
        // set the text corresonding to item 'position' into the stored title view
        // return the view for the whole row
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	b = new Button(mContext);
        	b2 = new Button(mContext);
           iv = new ImageView(mContext);
        	l = new LinearLayout(mContext);
        	l2 = new LinearLayout(mContext);
        	l3 = new LinearLayout(mContext);
        	l4 = new LinearLayout(mContext);
        	l5 = new LinearLayout(mContext);
        	 tv = new TextView(mContext);
        	 tv2 = new TextView(mContext);
        	//v = new View(mContext);
        } else {
        	b = (Button) convertView;
        	b2 = (Button) convertView;
        	iv = (ImageView) convertView;
           l = (LinearLayout) convertView;
           l2 = (LinearLayout) convertView;
           l3 = (LinearLayout) convertView;
           l4 = (LinearLayout) convertView;
           l5 = (LinearLayout) convertView;
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
        //iv.setMaxHeight(160);
        //iv.setMaxWidth(160);
        iv.setMaxWidth(160);
        iv.setMinimumWidth(160);
        iv.setLayoutParams(new LayoutParams(-2, -2));
        //iv.
        //iv.findViewById(R.id.item1);
        	//tv.findViewById(R.id.descript);
            //l.addView(tv.findViewById(R.id.descript));
//       l.addView((R.layout.item).findViewById(R.id.description));
        //tv = (TextView) findViewById(R.id.description);
//        l.addView(l.findViewById(R.id.descript));
        //tv.findViewById(R.id.description);
        //tv.inflate(mContext, R.layout.item, l)
        tv.setText(pro.getCurUser().wishlist.showWishlist().get(position).getName());
        tv2.setText(pro.getCurUser().wishlist.showWishlist().get(position).getPrice());
        
        //b.setId(position);
        b.setText("Buy!");
        b2.setText("Remove");
        b2.setLayoutParams(new LayoutParams(-2,-2));
        b.setLayoutParams(new LayoutParams(-2,-2));
        //b.setGravity(right);
        //l.addView(iv);
        l2.setOrientation(1);
        l2.addView(tv);
        l2.addView(tv2);
        l2.setLayoutParams(new LayoutParams(-2, -2));
        l2.setPadding(6, 0, 6, 0);
        l4.setLayoutParams(new LayoutParams(-2, -2));
        l4.setOrientation(1);
        l4.addView(l2);
        l5.setLayoutParams(new LayoutParams(-1,-2));
        l5.setHorizontalGravity(5);
        l5.addView(b);
        l5.addView(b2);
        l4.addView(l5);
        l3.setLayoutParams(new LayoutParams(-2, -1));
        l3.addView(iv);
        l3.addView(l4);
        //l.addView(b);
        l.setPadding(5, 5, 0, 5);
        l.addView(l3);
        //l.addView(b);
        //return textView;
        //v.findViewById(R.id.itemit);
        //return v;
        //return l;
        return l;
    }

    // references to our images
}