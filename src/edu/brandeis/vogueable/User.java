package edu.brandeis.vogueable;



/**
 * User class keeps track of a user's taste and wishlist
 * 
 * @author Yulia
 *
 */
public class User {
	private String name; 
	private Wishlist wishlist;
	private TasteManager mytaste;
	private String id;
	private String TAG="violeta";  


	/**
	 * Constructor to create a user
	 * 
	 * Initializes the name, 
	 * 
	 * @param myName- string name
	 * @param con- context to create a TasteManager
	 */
	public User(String myName){
		name = myName;
		wishlist = new Wishlist(this); //now initialized without a type
		//mytaste = new TasteManager(null, null);
		id = null;
	}


	/**
	 * Name getter
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}


	/**
	 * Wishlist getter
	 * 
	 * @return wishlist 
	 */
	public Wishlist getWishlists(){
		return wishlist;
	}


	/**
	 * Adds a wishlist item
	 * 
	 * @param itemA (an item for the wishlist)
	 */
	public void addWishlist(Item itemA){
		wishlist.addItem(itemA);
	}
	
	/** This method should add an item to the server once its been added to the local wishlist on the device
	 * TAKES IN: user -to extract his id and an Item, to extract the id too
	 */


	
	/**
	 * removes an item from the wishlist
	 * 
	 * @param itemR (an item to be removed)
	 */
	public void removeWishlist(Item itemR){
		wishlist.remove(itemR);
	}

	/**
	 * returns the tasteManager
	 * 
	 * @return mytaste TasteMangager type
	 */
	public TasteManager getTasteManager(){
		return mytaste;
	}


	public void setTasteManager(TasteManager taste){
		mytaste = taste; 
	}


	/**
	 * sets the UserID
	 * @param id
	 */
	public void setID(String id){
		this.id=id;
	}


	/**
	 * gets the user ID
	 * @return id
	 */
	public String getID(){
		return id;
	}


	public void setWishlist() {
		// TODO Auto-generated method stub
		if (wishlist.showWishlist().isEmpty()){
			wishlist.setWish();
		}
	}
}
