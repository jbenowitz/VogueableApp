package edu.brandeis.vogueable;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Like manager 
 * 
 * @author Jackie
 *
 */
public class LikeManager {
	
	private ImageButton likeb, dislikeb;
	private Context context;
	private Provider provide;
	private static String TAG = "Vogueable LikeManager";
	
	/**
	 * Constructor
	 * 
	 * @param likeb ImageButton that represents 'liking' an item
	 * @param dislikeb ImageButton that represents 'disliking' an item
	 * @param c Context of Activty in which these actions take
	 */
	public LikeManager(ImageButton likeb, ImageButton dislikeb, Context c, Provider provide){
		this.likeb = likeb;
		this.dislikeb = dislikeb;
		this.context = c;
		this.provide = provide;
	}
	
	
	
	/**
	 * Like the current item (add counts to hashtag list in taste manager)
	 * 
	 */
    public void addTagItem(){
    	provide.getCurTM().likeFlavor(provide.getCurItem().getTagList());
    }

    
    
    /**
	 * Dislike the current item (subtract counts to hashtag list in taste manager)
	 * 
	 */
    public void rmTagItem(){
    	provide.getCurTM().dislikeFlavor(provide.getCurItem().getTagList());
    }

	
    /**
     * Greys the 'like' button (remove focus)
     * 
     */
    public void greyLike(){
    	likeb.setFocusableInTouchMode(false);
		likeb.clearFocus();	
    }
    
    
    /**
     * Greens the 'like' button (focus)
     * 
     */
    public void greenLike() {
    	likeb.setFocusableInTouchMode(true);
		likeb.requestFocus();
    }
    
    
    /**
     * Greys the 'dislike' button (remove focus)
     * 
     */
    public void greyDislike() {
    	dislikeb.setFocusableInTouchMode(false);
		dislikeb.clearFocus();
	}
    
    
    /**
     * Reds the 'dislike' button (focus)
     * 
     */
    public void redDislike() {
    	dislikeb.setFocusableInTouchMode(true);
		dislikeb.requestFocus();
	}
	
   
    /**
     * perform action on click of liking an item 
     * (e.g. adds or removes tags depending on the current focus of the buttons
     * and changes colors respectively)
     */
    public void like(){
    	Log.i(TAG, "Like Item Boolean: " + provide.getCurItem().getItemLiked());
    	
    	provide.getCurItem().toggleItemLiked(); 
		
		if(dislikeb.isFocused()){
			
			provide.getCurItem().toggleItemDisliked();
			greyDislike();
			addTagItem();
			greenLike();
			addTagItem();
			
		} else if (likeb.isFocused()){
			greyLike();
			rmTagItem();
			
		} else {
			greenLike();
			addTagItem();
		}	
    }
    
    /**
     * perform action on click of disliking an item
     *  (e.g. adds or removes tags depending on the current focus of the buttons
     * and changes colors respectively)
     */
    public void dislike(){
    	provide.getCurItem().toggleItemDisliked();
		
		if(likeb.isFocused()){
			
			provide.getCurItem().toggleItemLiked();
			greyLike();
			rmTagItem();
			redDislike();
			rmTagItem();
			
		} else if (dislikeb.isFocused()){
			greyDislike();
			addTagItem();
			
		} else {
			redDislike();
			rmTagItem();
		}	
    }

}
