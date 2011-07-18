package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class CategoryChooser extends Activity implements OnClickListener {
	
	private static final String TAG = "CategoryChooser";
	
	private Provider prov;
	private User user;
	
	//public String currCat = "";
	public ArrayList<String> currCat =new ArrayList<String>();
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.category);
	        
	        // Set up click listeners for physical buttons (Select, Deselect, go)
	        View selectall = findViewById(R.id.select_all);
		    selectall.setOnClickListener(this);
		    
		    View deselectall = findViewById(R.id.deselect_all);
		    deselectall.setOnClickListener(this);
	        
		    View goButton = findViewById(R.id.go_button);
		    goButton.setOnClickListener(this);
		      
		    
		    //Create hardwired click listeners for each category
		    View dressview = findViewById(R.id.dresses_check);
		    dressview.setOnClickListener(this);
		      
		    View bottomsview = findViewById(R.id.bottoms_check);
		    bottomsview.setOnClickListener(this);
		      
		    View topsview = findViewById(R.id.tops_check);
		    topsview.setOnClickListener(this);
		      
		    View shoesview = findViewById(R.id.shoes_check);
		    shoesview.setOnClickListener(this);
		      
		    View accesoriesview = findViewById(R.id.accesories_check);
		    accesoriesview.setOnClickListener(this);
		    
		    View jacketsview = findViewById(R.id.outerwear_check);
		    jacketsview.setOnClickListener(this);
		      
		    View intimateview = findViewById(R.id.intimates_check);
		    intimateview.setOnClickListener(this);
		      
		    View activeview = findViewById(R.id.activewear_check);
		    activeview.setOnClickListener(this);
		    
		    View suitsview = findViewById(R.id.suits_check);
		    suitsview.setOnClickListener(this);
		    
		    View rompersview = findViewById(R.id.rompers_check);
		    rompersview.setOnClickListener(this);
		      
	    }
	  public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {

			case R.id.wishlist_menu2:   
				startActivity(new Intent(this, WishAct.class));
				break;
			}
			return true;
		}
	  public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu2, menu);
			return true;
		}	
	    public void onClick(View v) {
	    	
	    	
	    	//Create a CheckBox for all the checkboxes given:
	    	final CheckBox dresses = (CheckBox) findViewById(R.id.dresses_check);  
	    	final CheckBox shoes = (CheckBox) findViewById(R.id.shoes_check);
	    	final CheckBox bottoms = (CheckBox) findViewById(R.id.bottoms_check); 
	    	final CheckBox accesories = (CheckBox) findViewById(R.id.accesories_check);  
	    	final CheckBox outerwear = (CheckBox) findViewById(R.id.outerwear_check);  
	    	final CheckBox intimate = (CheckBox) findViewById(R.id.intimates_check);  
	    	final CheckBox activewear = (CheckBox) findViewById(R.id.activewear_check);
	    	final CheckBox rompers = (CheckBox) findViewById(R.id.rompers_check);  
	    	final CheckBox suits = (CheckBox) findViewById(R.id.suits_check);
	    	final CheckBox tops = (CheckBox) findViewById(R.id.tops_check); 
	    	
	    	
		      switch (v.getId()) {
		     
		      case R.id.go_button :
			      
			      final ProgressDialog dialog = ProgressDialog.show(CategoryChooser.this,"", "Loading. Please wait...",true);
			      	dialog.show();
			         Handler handler = new Handler();
			         handler.postDelayed(new Runnable() {
			         public void run() {
		    	  
				      Context context = CategoryChooser.this; 
	
				      //Get user's information from last intent
				      Bundle extras = getIntent().getExtras(); 
				      String id = null;
				      String name = null;
				      if(extras !=null) {
				          id = extras.getString("currUserID");
				          name = extras.getString("currUserName");
				      }
				      user = new User(name);
	
				      prov = Provider.instance(user.getName(), context, null);//set provider with current user
				     
				      
				      prov.getCurUser().setID(user.getID());//update current user's id
				      Log.i(TAG, "provider current user name: " + prov.getCurUser().getName());
				      Log.i(TAG, "provider current user ID: " + prov.getCurUser().getName());
	
			    	  prov.clearCatList();
	
			    	  	if (dresses.isChecked()) {
				             currCat.add("dresses");
				             prov.setAcat("dresses");
	
				        	 //currCat+=" "+"dresses";
				         }   
				         if (shoes.isChecked()) {
				        	currCat.add("shoes");
				        	prov.setAcat("shoes");
	
				        	 //currCat+="|"+"shoes";
				         }  
				         if (bottoms.isChecked()) {
				        	 currCat.add("bottoms");
				        	 prov.setAcat("bottoms");
				        	 prov.setAcat("pants");
				        	 prov.setAcat("skirts");
	
				        	 //currCat+="|"+"dresses";
				         } 
				         if (accesories.isChecked()) {
				        	 currCat.add("accesories");
				        	 prov.setAcat("accessories");
	
				        	 //currCat+="|"+"shorts";
				         } 
				         if(outerwear.isChecked()) {
	
				        	 currCat.add("jackets");
				        	 prov.setAcat("jackets");
				        	 prov.setAcat("blazers");
				        	 prov.setAcat("hoodies");
				        	 //currCat+="|"+"clothing";
				         }   
				        
				         if (intimate.isChecked()) {
				        	 currCat.add("intimate");
				        	 prov.setAcat("intimates");
				        	 prov.setAcat("hosiery");
	
				        	 //currCat+="|"+"dresses";
				         } 
				         if (activewear.isChecked()) {
				        	 currCat.add("activewear");
				        	 prov.setAcat("active");
	
				        	 //currCat+="|"+"shorts";
				         } 
				         if(rompers.isChecked()) {
				        	 currCat.add("rompers");
				        	 prov.setAcat("rompers");
	
				        	 //currCat+="|"+"clothing";
				         }
				         if (suits.isChecked()) {
				        	 currCat.add("suits");
				        	 prov.setAcat("suits");
	
				        	 //currCat+="|"+"shorts";
				         } 
				         if(tops.isChecked()) {
				        	 currCat.add("tops");
				        	 prov.setAcat("tops");
				        	 prov.setAcat("blouses");
	
				        	 //currCat+="|"+"clothing";
				         }


			         /*pass the selected categories to the main activity 
			          * so the items loaded reflect the categories the user selects
			          * */

			        // i.putStringArrayListExtra("categories", currCat);
			         //startActivity(i);//start the activity

			        	 Intent i = new Intent(CategoryChooser.this, MainActivity.class);
			        	 startActivity(i);//start the activity
			                 
			       	 dialog.dismiss();
			            }}, 80000); { // 3000 milliseconds
			            }
			         
		    	  
		    	  
		    	  	
			        
			        
			         /*pass the selected categories to the main activity 
			          * so the items loaded reflect the categories the user selects
			          * */
			         
			        // i.putStringArrayListExtra("categories", currCat);
			        
			         

			   
			         
			         
			  break;    
			         
			         
			         
			         
		      case R.id.select_all :
		    	  
		    	  dresses.setChecked(true);
		    	  shoes.setChecked(true);
		    	  bottoms.setChecked(true);
		    	  accesories.setChecked(true);
		    	  outerwear.setChecked(true);
		    	  intimate.setChecked(true);
		    	  activewear.setChecked(true);
		    	  rompers.setChecked(true);
		    	  suits.setChecked(true);
		    	  tops.setChecked(true);
		    	  
		    	  break;
		    	  
		      case R.id.deselect_all :
		    	  
		    	  dresses.setChecked(false);
		    	  shoes.setChecked(false);
		    	  bottoms.setChecked(false);
		    	  accesories.setChecked(false);
		    	  outerwear.setChecked(false);
		    	  intimate.setChecked(false);
		    	  activewear.setChecked(false);
		    	  rompers.setChecked(false);
		    	  suits.setChecked(false);
		    	  tops.setChecked(false);
			         
		      }
	    }
	    
}
