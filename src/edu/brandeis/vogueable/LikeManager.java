package edu.brandeis.vogueable;

import android.content.Context;
import android.util.Log;
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
    private void addTagItem(){
    	provide.getCurTM().likeFlavor(provide.getCurItem().getTagList());
    }

    
    
    /**
	 * Dislike the current item (subtract counts to hashtag list in taste manager)
	 * 
	 */
    private void rmTagItem(){
	provide.getCurTM().dislikeFlavor(provide.getCurItem().getTagList());
    }

	
    /**
     * Greys the 'like' button
     * 
     */
    private void greyLike(){
    	likeb.setImageDrawable(context.getResources().getDrawable(R.drawable.approvegrey));
    }
    
    
    /**
     * Greens the 'like' button
     * 
     */
    private void greenLike() {
		likeb.setImageDrawable(context.getResources().getDrawable(R.drawable.approvegreen));
	}
    
    
    /**
     * Greys the 'dislike' button
     * 
     */
    private void greyDislike() {
		dislikeb.setImageDrawable(context.getResources().getDrawable(R.drawable.disapprovegrey));
	}
    
    
    /**
     * Reds the 'dislike' button
     * 
     */
    private void redDislike() {
		dislikeb.setImageDrawable(context.getResources().getDrawable(R.drawable.disapprovered));
	}
	
   
    /**
     * preform action on click of liking an item 
     * (e.g. if item was previously liked, remove tags from users Taste manager,
     *  toggle the boolean of if it was previously liked, grey the button)
     */
    public void like(){
    	Log.i(TAG, "Like Item Boolean: " + provide.getCurItem().getItemLiked());
    	if(provide.getCurItem().getItemLiked()){
			//rmTagItem();
			provide.getCurItem().toggleItemLiked();
			greyLike();
		}else{
			//addTagItem();
			provide.getCurItem().toggleItemLiked();
			greenLike();
		}
    }
    
    /**
     * perform action on click of disliking an item
     *  (eg if item was not previously liked, remove tags from the users taste manager, 
     *    toggle the boolean of if it was previously disliked, red the button)
     */
    public void dislike(){
    	if(provide.getCurItem().getItemDisliked()){
			//addTagItem();
			provide.getCurItem().toggleItemDisliked();
			greyDislike();
		}else{
			//rmTagItem();
			provide.getCurItem().toggleItemDisliked();
			redDislike();
		}
    }

}
