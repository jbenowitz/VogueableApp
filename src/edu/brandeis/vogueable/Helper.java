package edu.brandeis.vogueable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;


public class Helper extends BaseAdapter {
    private Context mContext;
    private Provider pro;

    public Helper(Context c,Provider pro) {
        mContext = c;
        this.pro=pro;
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

    public View getView(int position, View convertView, ViewGroup parent) {
   
    	View view;
    	TextView name_txt, price_txt;
    	ImageView image;
    	Button buy_button, remove_button;

    	if (convertView == null) {  // if it's not recycled
    		
    		view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.single,parent,false);
    		
    		name_txt = (TextView) view.findViewById(R.id.tryname);
    		price_txt = (TextView) view.findViewById(R.id.tryprice);
    		
    		image = (ImageView) view.findViewById(R.id.tryitem);
    		
    		buy_button = (Button) view.findViewById(R.id.buy_buy);
    		remove_button = (Button) view.findViewById(R.id.remove);
    		
        	name_txt.setText(pro.getCurUser().wishlist.showWishlist().get(position).getName());
        	price_txt.setText(pro.getCurUser().wishlist.showWishlist().get(position).getPrice());
        	
        	try {
                Log.e("src",pro.getCurUser().wishlist.showWishlist().get(position).getImageFileString());
                URL url = new URL(pro.getCurUser().wishlist.showWishlist().get(position).getImageFileString());
                URLConnection connection = (URLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(input);
                Bitmap myBitmap = BitmapFactory.decodeStream(bis);
                image.setImageBitmap(myBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception",e.getMessage());
                image.setImageResource(R.drawable.icon);
            }
        	
            buy_button.setTag(position);
            remove_button.setTag(position);
            
            buy_button.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                	switch (v.getId()) {
           	     
    	      	      case R.id.buy_buy :
    	      	    	if (pro.getCurItem().getLink() != null) {
    	      	    		int tag = (Integer) v.getTag();
    	    		    	v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pro.getCurUser().wishlist.showWishlist().get(tag).getLink())));
    	    		    } else {
    	    		    	v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/l/1036592/ref=nb_sb_noss")));
    	    		    }
    	    			break;
        			
    		      	  case R.id.remove :
    		      	    int tag = (Integer) v.getTag();
    		      	    pro.getCurUser().wishlist.showWishlist().remove(tag);
    		      	    notifyDataSetChanged();
    		      	    notifyDataSetInvalidated();
    		    		break;
                	}
                }});
            
            remove_button.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                	switch (v.getId()) {
           	     
    		      	    case R.id.remove :
    		      	    	int tag = (Integer) v.getTag();
    		      	    	pro.getCurUser().wishlist.showWishlist().remove(tag);
    		      	    	//only good for now
    		      	    	//v.getContext().startActivity(new Intent(mContext,WishAct.class));
    		      	    	//notifyDataSetChanged();
    		      	    	//notifyDataSetInvalidated();
    		    			break;
                	}
                }});
    		
    	} else {
    		
    		view = (View)convertView;
    		name_txt = (TextView) convertView;
    		price_txt = (TextView) convertView;
    		image = (ImageView) convertView;
    		buy_button = (Button) convertView;
    		remove_button = (Button) convertView;
    	}
    	
    	return view;
    }
}