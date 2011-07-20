package edu.brandeis.vogueable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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

public class Helper extends BaseAdapter {
    private Context mContext;
    private Provider pro;
    

    public Helper(Context c,Provider pro) {
        mContext = c;
        //this.pro=pro;
        //RealProxy proxy = new RealProxy();
    	//Context context = this;
        this.pro = pro;
        //pro = Provider.instance("AndroidUserName",mContext, "item from pref");
        Log.d("constructor","created a a Helper");
    }

    public int getCount() {
    	Log.d("getCount","returns"+ pro.getCurUser().getWishlists().showWishlist().size());
        return pro.getCurUser().getWishlists().showWishlist().size();
    }

    public Object getItem(int position) {
    	Log.d("getItem","returns"+ pro.getCurUser().getWishlists().showWishlist().get(position));
        return pro.getCurUser().getWishlists().showWishlist().get(position);
    }

    public long getItemId(int position) {
    	Log.d("getItemId","returns position "+position);
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
   
    	View view;
    	TextView name_txt, price_txt;
    	ImageView image;
    	Button buy_button, remove_button;

    	//if (convertView == null) {  // if it's not recycled
    		//Log.d("getView","created a view");
    		
    		view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.single,parent,false);
    		
    		name_txt = (TextView) view.findViewById(R.id.tryname);
    		price_txt = (TextView) view.findViewById(R.id.tryprice);
    		
    		image = (ImageView) view.findViewById(R.id.tryitem);
    		
    		buy_button = (Button) view.findViewById(R.id.buy_buy);
    		remove_button = (Button) view.findViewById(R.id.remove);
    		
        	name_txt.setText(pro.getCurUser().getWishlists().showWishlist().get(position).getName());
        	price_txt.setText(pro.getCurUser().getWishlists().showWishlist().get(position).getPrice());
        	
        	try {
                Log.e("src",pro.getCurUser().getWishlists().showWishlist().get(position).getImageFileString());
                URL url = new URL(pro.getCurUser().getWishlists().showWishlist().get(position).getImageFileString());
                URLConnection connection = (URLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(input);
                Bitmap myBitmap = BitmapFactory.decodeStream(bis);
                Log.e("helper", ""+myBitmap.getWidth());
                image.setImageBitmap(myBitmap);   
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception",e.getMessage());
                image.setImageResource(R.drawable.icon);
            }
        	
            buy_button.setTag(position);
            remove_button.setTag(position);
            Log.d("getView","button tags set to "+position);
            
            buy_button.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                	switch (v.getId()) {
           	     
    	      	      case R.id.buy_buy :
    	      	    	if (pro.getCurItem().getLink() != null) {
    	      	    		int tag = (Integer) v.getTag();
    	    		    	v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pro.getCurUser().getWishlists().showWishlist().get(tag).getLink())));
    	    		    } else {
    	    		    	v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/l/1036592/ref=nb_sb_noss")));
    	    		    }
    	    			break;
        			
    		      	  case R.id.remove :
    		      	    int tag = (Integer) v.getTag();
    		      	    pro.getCurUser().getWishlists().showWishlist().remove(tag);
    		      	    notifyDataSetChanged();
    		      	    notifyDataSetInvalidated();
    		    		break;
                	}
                }});
            
            remove_button.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                	switch (v.getId()) {
                	
    		      	    case R.id.remove :
    		      	    	final int tag = (Integer) v.getTag(); 
    		      	    	AlertDialog.Builder sure = new AlertDialog.Builder(v.getContext());
    		      	    	
    		    	         sure.setMessage("Are you sure you want to remove this item from your wishlist? There is no undo for this action.");
    		    	         sure.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
    		    	        	 
    		    	             public void onClick(DialogInterface dialog, int id) {
    		    	            	 pro.getCurUser().getWishlists().showWishlist().remove(tag);
    		    	            	 //notifyDataSetChanged();
    		    	            	 notifyDataSetInvalidated();
    		    	            	 dialog.cancel();
    		    	             }
    		    	         	})
    		    	         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		    	             public void onClick(DialogInterface dialog, int id) {
    		    	            	 dialog.cancel();
    		    	             }
    		    	         }).show();
    		    	         
    			    break;
    		      	    	
    		      	    	//only good for now
    		      	    	//v.getContext().startActivity(new Intent(mContext,WishAct.class));
    		      	    	//notifyDataSetChanged();
    		      	    	//notifyDataSetInvalidated();
                	}
                }});
    		
    	/*} else {
    		
    		Log.d("getView","recycled a view");
    		view = (View)convertView;
    		name_txt = (TextView) convertView;
    		price_txt = (TextView) convertView;
    		image = (ImageView) convertView;
    		buy_button = (Button) convertView;
    		remove_button = (Button) convertView;
    	}
    	Log.d("getView","a view is returned");*/
    	return view;
    }
}