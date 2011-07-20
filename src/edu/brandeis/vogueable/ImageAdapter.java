package edu.brandeis.vogueable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Image Adapter Class, used with Gallery
 * Used in Android SDK on Gallery
 * 
 * @author Jackie
 *
 */
 public class ImageAdapter extends BaseAdapter {
      private static final String TAG = "ImageAdapter";
      private Context mContext;
      int mGalleryItemBackground;
      TextView namelandtext, pricelandtext;
      Provider provide;
      private ImageButton likeb, dislikeb;
      private static final int MAXPICS = 100; //the max amount of pics pulled to your phone (can't go farther back than this)

      /**
       * Constructor that sets up the Image Adapter (what is inside the Gallery)
       * @param c
       * @param provide
       * @param namelandtext
       * @param pricelandtext
       * @param lb
       * @param db
       */
      public ImageAdapter(Context c, Provider provide, TextView namelandtext, TextView pricelandtext, ImageButton lb, ImageButton db) {
          Log.i(TAG, "Constructor called");
    	  mContext = c;
          this.provide = provide;
          this.namelandtext = namelandtext;
          this.pricelandtext = pricelandtext;
          this.likeb = lb;
          this.dislikeb = db;
          
          provide.fillItemCache();
          
          TypedArray a = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
          mGalleryItemBackground = a.getResourceId(
                  R.styleable.HelloGallery_android_galleryItemBackground, 0);
          a.recycle();
          
      }

      /**
       * Returns count of images array
       */
      public int getCount() {
    	  Log.i(TAG, "getCount() called");
          return MAXPICS;
      }

      /**
       * returns the item in a certain position
       */
      public Object getItem(int position) {
    	  Log.i(TAG, "getItem() called position = " + position);
    	  return provide.getItemCache().getItem(position);
      }

      /**
       * gets the itemID of a certain position
       */
      public long getItemId(int position) {
    	  Log.i(TAG, "getItemId() position = " + position);
          return provide.getItemCache().getItemId(position);
      }
      
      /**
       * creates a bitmap of the images from given URL
       * @param src
       * @return
       */
      public  Bitmap getBitmapFromURL(String src) {
          try {
              Log.i(TAG,"GetbitmapfromURL");
              URL url = new URL(src);
              URLConnection connection = (URLConnection) url.openConnection();
              connection.connect();
              Log.i(TAG,"bitmap returned");
              return BitmapFactory.decodeStream(new BufferedInputStream(connection.getInputStream()));
          } catch (IOException e) {
              e.printStackTrace();
              Log.e(TAG,"exception thrown");
              return null;
          }
      }
      
      /**
       * Creates the view of the images.
       */
      public View getView(int position, View convertView, ViewGroup parent) {
    	  Log.i(TAG, "starting getView position = " + position );
      	 
           
           //Sets the current item to be referenced by other classes in the provider
    	  provide.setCurItem(position);
           
    	  //clears focus from like or dislike buttons
    	  dislikeb.setFocusableInTouchMode(false);
    	  likeb.setFocusableInTouchMode(false);
    	  dislikeb.clearFocus();
    	  likeb.clearFocus();
    	  
           //Sets up the landscape based on Current item
           setLandscapeName();
           setLandscapePrice();
       
           Log.i(TAG, "User name " + provide.getCurUser().getName());
           Log.i(TAG, "User id " + provide.getCurUser().getID());
           
           
           //Sets the view context image
           ImageView i = new ImageView(mContext);
           Bitmap bimage = getBitmapFromURL(provide.getCurItem().getImageFileString());
           i.setImageBitmap(bimage);
           i.setAdjustViewBounds(true);
           i.setMaxHeight(499);
           i.setMinimumHeight(499);

          //scales the images accordingly
           i.setPadding(50, 0, 50, 0);
           i.setScaleType(ImageView.ScaleType.FIT_XY);
           
           Log.e(TAG, ""+bimage.getWidth());
           
           
           //Set landscape or portrait gallery/image size
           Gallery.LayoutParams galayout;
           if(mContext.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
          	 galayout = new Gallery.LayoutParams(300,LayoutParams.MATCH_PARENT);
           }
           else{
          	 galayout = new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, 499);
           }
           i.setLayoutParams(galayout);

           //sets the border
           i.setBackgroundColor(R.color.white);

           return i;
      }
      
      
      
    /**
   	 * Changes the landscape layout more info "name" field to a given string
   	 */
   	private void setLandscapeName(){
   		Log.i(TAG, "setLandscapeName() called");
   		namelandtext.setText(provide.getCurItem().getName());
   	}
   	
   	/**
   	 * Changes the landscape layout more info "price" field to a given string
   	 */
   	private void setLandscapePrice(){
   		Log.i(TAG, "setLandscapePrice() called");
   		pricelandtext.setText(provide.getCurItem().getPrice());
   	}
  }
