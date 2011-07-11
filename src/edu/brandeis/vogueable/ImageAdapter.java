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
      private Context mContext;
      int mGalleryItemBackground;
      String link="http://www.vogueable.heroku.com/";
      Item[] currents = {new Item("baby1"), new Item("baby1"), new Item("baby2"), new Item("baby3"), new Item("baby4")};
      TextView namelandtext, pricelandtext;
      Provider provide;

      //Array of image URLs to be used within gallery
      private String[] myImages = {
              "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
     		 "http://ecx.images-amazon.com/images/I/41Hkj9aBsAL._AA300_.jpg",
     		 "http://ecx.images-amazon.com/images/I/41JMUzALgpL._AA300_.jpg",
     		 "http://ecx.images-amazon.com/images/I/51Iykkkx5LL._SX342_.jpg",
     		 "http://ecx.images-amazon.com/images/I/410oAxun7dL._AA300_.jpg",
      };

      //Constructor that sets up the Image Adapter (what is inside the Gallery)
      public ImageAdapter(Context c, Provider provide, TextView namelandtext, TextView pricelandtext) {
          mContext = c;
          this.provide = provide;
          this.namelandtext = namelandtext;
          this.pricelandtext = pricelandtext;
          
          TypedArray a = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
          mGalleryItemBackground = a.getResourceId(
                  R.styleable.HelloGallery_android_galleryItemBackground, 0);
          a.recycle();
          
      }

      //Returns count of images array
      public int getCount() {
          return 1000000;
      }

      //returns the item in a certain position
      public Object getItem(int position) {
          return position;
      }

      //gets the itemID of a certain position
      public long getItemId(int position) {
          return position;
      }
      
      //creates a bitmap of the images from given URL
      public  Bitmap getBitmapFromURL(String src) {
          try {
              Log.e("src",src);
              URL url = new URL(src);
              URLConnection connection = (URLConnection) url.openConnection();
              connection.connect();
              Log.e("Bitmap","returned");
              return BitmapFactory.decodeStream(new BufferedInputStream(connection.getInputStream()));
          } catch (IOException e) {
              e.printStackTrace();
              Log.e("Exception",e.getMessage());
              return null;
          }
      }
      
      /**
       * Creates the view of the images.
       */
      public View getView(int position, View convertView, ViewGroup parent) {
     	 
     	 position = position % myImages.length;
     	 if (position < 0)
     	     position = position + myImages.length;
     	 
          
          //Sets the current item to be referenced by other classes in the provider
          provide.setCurItem(currents[position]);	
          link=provide.getCurItem().getLink();
          setLandscapeName();
          setLandscapePrice();
      
          
          //Gets the next item
          Item nextItem = provide.getCurTM().getNextItem(provide.getCurItem());
          myImages[position+(getCount()/2)%1000] = nextItem.getImageFileString();
          currents[position+(getCount()/2)%1000] = nextItem;
          
          
          //Sets the view context image
          ImageView i = new ImageView(mContext);
          Bitmap bimage=  getBitmapFromURL(myImages[position]);
          i.setImageBitmap(bimage);
          

         //scales the images accordingly
          i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
          
          
          
          //Set landscape or portrait gallery/image size
          Gallery.LayoutParams galayout;
          if(mContext.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
         	 galayout = new Gallery.LayoutParams(200,200);
          }
          else{
         	 galayout = new Gallery.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
          }
          i.setLayoutParams(galayout);

          //sets the border
          i.setBackgroundResource(mGalleryItemBackground);

          return i;
      }
      
      /**
   	 * Changes the landscape layout more info "name" field to a given string
   	 */
   	public void setLandscapeName(){
   		namelandtext.setText(provide.getCurItem().getName());
   	}
   	
   	/**
   	 * Changes the landscape layout more info "price" field to a given string
   	 */
   	public void setLandscapePrice(){
   		pricelandtext.setText(provide.getCurItem().getPrice());
   	}
  }