package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class CategoryChooser extends Activity implements OnClickListener {
	Provider prov;
	
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
		    
		    View jacketsview = findViewById(R.id.jackets_check);
		    jacketsview.setOnClickListener(this);
		      
		    View blousesview = findViewById(R.id.blouses_check);
		    blousesview.setOnClickListener(this);
		      
		    View hoodiesview = findViewById(R.id.hoodies_check);
		    hoodiesview.setOnClickListener(this);
		      
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
	    	CheckBox dresses = (CheckBox) findViewById(R.id.dresses_check);  
	    	CheckBox shoes = (CheckBox) findViewById(R.id.shoes_check);
	    	CheckBox bottoms = (CheckBox) findViewById(R.id.bottoms_check); 
	    	CheckBox accesories = (CheckBox) findViewById(R.id.accesories_check);  
	    	CheckBox jackets = (CheckBox) findViewById(R.id.jackets_check);  
	    	CheckBox blouses = (CheckBox) findViewById(R.id.blouses_check);
	    	CheckBox hoodies = (CheckBox) findViewById(R.id.hoodies_check); 
	    	CheckBox intimate = (CheckBox) findViewById(R.id.intimates_check);  
	    	CheckBox activewear = (CheckBox) findViewById(R.id.activewear_check);
	    	CheckBox rompers = (CheckBox) findViewById(R.id.rompers_check);  
	    	CheckBox suits = (CheckBox) findViewById(R.id.suits_check);
	    	CheckBox tops = (CheckBox) findViewById(R.id.tops_check); 
	    	
	    	
		      switch (v.getId()) {
		     
		      case R.id.go_button :
		    	  RealProxy proxy = new RealProxy();
			        Context context = this; 
			       
			 		prov = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
		    	  
		    	  Intent i = new Intent(this, MainActivity.class);
			         
		    	  prov.clearCatList();
		    	  
		    	  	if (dresses.isChecked()) {
			             //currCat.add("dresses");
			             prov.setAcat("dresses");
			            
			        	 //currCat+=" "+"dresses";
			         }   
			         if (shoes.isChecked()) {
			        	//currCat.add("shoes");
			        	prov.setAcat("shoes");
			        	prov.setAcat("boots");
			        	prov.setAcat("flats");
			        	prov.setAcat("pumps");
			        	prov.setAcat("sandals");
			        	prov.setAcat("rompers");
			        	prov.setAcat("oxfords");
			        	prov.setAcat("espadrilles");
			        	
			        	 //currCat+="|"+"shoes";
			         }  
			         if (bottoms.isChecked()) {
			        	 //currCat.add("bottoms");
			        	 prov.setAcat("bottoms");
			        	 prov.setAcat("pants");
			        	 prov.setAcat("capris");
			        	 prov.setAcat("shorts");
			        	 prov.setAcat("skirts");
			        	 prov.setAcat("cargo");
			        	 prov.setAcat("cargos");
			        	 prov.setAcat("chinos");
			        	 prov.setAcat("corduroys");
			        	 prov.setAcat("cropped");
			        	 prov.setAcat("jeans");
			        	 prov.setAcat("leggings");
			        	 prov.setAcat("knits");
			        	 prov.setAcat("slacks");
			        	 prov.setAcat("carpenters");
			        	 prov.setAcat("cuffed");
			        	 prov.setAcat("pleated");
			        	 prov.setAcat("bermudas");
			        	 prov.setAcat("culottes");
			        	 prov.setAcat("cutoffs");
			        	 prov.setAcat("khaki");
			        	 prov.setAcat("skorts");
			        	 
			        	
			        	 //currCat+="|"+"dresses";
			         } 
			         if (accesories.isChecked()) {
			        	 //currCat.add("accesories");
			        	 prov.setAcat("accessories");
			        	 prov.setAcat("belts");
			        	 prov.setAcat("handbags");
			        	 prov.setAcat("hats");
			        	 prov.setAcat("scarves");
			        	 prov.setAcat("wallets");
			        	 prov.setAcat("sunglasses");
			        	 prov.setAcat("headbands");
			        	 prov.setAcat("fedoras");
			        	 prov.setAcat("berets");
			        	 prov.setAcat("wraps");
			        	 prov.setAcat("pashminas");
			        	 prov.setAcat("keyrings");
			        	 prov.setAcat("keychains");
			        	
			        	 //currCat+="|"+"shorts";
			         } 
			         if(jackets.isChecked()) {
			        	
			        	 //currCat.add("jackets");
			        	 prov.setAcat("jackets");
			        	 prov.setAcat("blazers");
			        	 prov.setAcat("vests");
			        	 //currCat+="|"+"clothing";
			         }
			         if (blouses.isChecked()) {
			             //currCat.add("blouses");
			             prov.setAcat("blouses");
			             prov.setAcat("long-sleeve");
			        	 prov.setAcat("short_sleeve");
			        	 prov.setAcat("three-quarter-sleeve");
			        	 prov.setAcat("peasant");
			        	 prov.setAcat("sleeveless");
			        	 prov.setAcat("button-down");
			        	 prov.setAcat("oxfords");
			        	 prov.setAcat("camisoles");
			        	 prov.setAcat("halters");
			           
			        	 //currCat+=" "+"dresses";
			         }   
			         if (hoodies.isChecked()) {
			        	//currCat.add("hoodies");
			        	prov.setAcat("hoodies");
			        	
			        	//prov.setAcat("intimate");
			        	 //currCat+="|"+"shoes";
			         }  
			         if (intimate.isChecked()) {
			        	 //currCat.add("intimate");
			        	 prov.setAcat("intimates");
			        	 prov.setAcat("hosiery");
			        	 prov.setAcat("bras");
			        	 prov.setAcat("panties");
			        	 prov.setAcat("lingerie");
			        	 prov.setAcat("camisoles");
			        	 prov.setAcat("chemises");
			        	 prov.setAcat("teddies");
			        	 prov.setAcat("negligees");
			        	 prov.setAcat("corsets");
			        	 prov.setAcat("bustiers");
			        	 
			        	
			        	 //currCat+="|"+"dresses";
			         } 
			         if (activewear.isChecked()) {
			        	 //currCat.add("activewear");
			        	 prov.setAcat("active");
			        	 prov.setAcat("swimwear");
			        	 prov.setAcat("baselayers");
			        	 prov.setAcat("bikinis");
			        	 prov.setAcat("racerback");
			        	 prov.setAcat("scoop-back");
			        	 prov.setAcat("slimming");
			        	 prov.setAcat("skirted");
			        	 prov.setAcat("spaghetti-strap");
			        	 prov.setAcat("strapless");
			        	 prov.setAcat("coverups");
			        	 
			        
			        	 //currCat+="|"+"shorts";
			         } 
			         if(rompers.isChecked()) {
			        	 //currCat.add("rompers");
			        	 prov.setAcat("rompers");
			        	 prov.setAcat("jumpsuits");
			        	
			        	 //currCat+="|"+"clothing";
			         }
			         if (suits.isChecked()) {
			        	 //currCat.add("suits");
			        	 prov.setAcat("suits");
			        	 prov.setAcat("separates");
			        	 prov.setAcat("pantsuits");
			        	
			        	 //currCat+="|"+"shorts";
			         } 
			         if(tops.isChecked()) {
			        	 //currCat.add("tops");
			        	 prov.setAcat("tops");
			        	 prov.setAcat("tanks");
			        	 prov.setAcat("camis");
			        	 prov.setAcat("shirts");
			        	 prov.setAcat("sweaters");
			        	 prov.setAcat("sweatshirts");
			        	 prov.setAcat("racerback");
			        	 prov.setAcat("scoop-back");
			        	 prov.setAcat("slimming");
			        	 prov.setAcat("skirted");
			        	 prov.setAcat("spaghetti-strap");
			        	 prov.setAcat("strapless");
			        	 prov.setAcat("long-sleeve");
			        	 prov.setAcat("short_sleeve");
			        	 prov.setAcat("three-quarter-sleeve");
			        	 prov.setAcat("sleeveless");
			        	 prov.setAcat("polos");
			        	 prov.setAcat("crewnecks");
			        	 prov.setAcat("v-necks");
			        	 prov.setAcat("tunics");
			        	 prov.setAcat("turtlenecks");
			        	 prov.setAcat("mocks");
			        	 prov.setAcat("cardigans");
			        	 prov.setAcat("shells");
			        	 prov.setAcat("hooded");
			        	 prov.setAcat("ponchos");
			        	 prov.setAcat("pullovers");
			        	 prov.setAcat("shrugs");
			        	 prov.setAcat("zippered");
			        	 
			        	 //currCat+="|"+"clothing";
			         }
			        
			        
			         /*pass the selected categories to the main activity 
			          * so the items loaded reflect the categories the user selects
			          * */
			         
			        // i.putStringArrayListExtra("categories", currCat);
			         startActivity(i);//start the activity
			         
			         
			         break;    
			         
		      case R.id.select_all :
		    	 // Intent in = new Intent(this, MainActivity.class);
		    	  
		    	  dresses.setChecked(true);
		    	  shoes.setChecked(true);
		    	  bottoms.setChecked(true);
		    	  accesories.setChecked(true);
		    	  jackets.setChecked(true);
		    	  blouses.setChecked(true);
		    	  hoodies.setChecked(true);
		    	  intimate.setChecked(true);
		    	  activewear.setChecked(true);
		    	  rompers.setChecked(true);
		    	  suits.setChecked(true);
		    	  tops.setChecked(true);
		    	  
		    	  break;
		    	  
		      case R.id.deselect_all :
		    	//  Intent inten = new Intent(this, MainActivity.class);
		    	  
		    	  dresses.setChecked(false);
		    	  shoes.setChecked(false);
		    	  bottoms.setChecked(false);
		    	  accesories.setChecked(false);
		    	  jackets.setChecked(false);
		    	  blouses.setChecked(false);
		    	  hoodies.setChecked(false);
		    	  intimate.setChecked(false);
		    	  activewear.setChecked(false);
		    	  rompers.setChecked(false);
		    	  suits.setChecked(false);
		    	  tops.setChecked(false);
			         
		      }
	    }
	    
}
