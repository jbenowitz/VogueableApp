package edu.brandeis.vogueable;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		    View dress = findViewById(R.id.dress_check);
		    dress.setOnClickListener(this);
		      
		    View shorts = findViewById(R.id.shorts_check);
		    shorts.setOnClickListener(this);
		      
		    View pants = findViewById(R.id.pants_check);
		    pants.setOnClickListener(this);
		      
		    View shoes = findViewById(R.id.shoes_check);
		    shoes.setOnClickListener(this);
		      
		    View clothing = findViewById(R.id.clothing_check);
		    clothing.setOnClickListener(this);
		      
	    }

	    public void onClick(View v) {
	    	
	    	
	    	//Create a CheckBox for all the checkboxes given:
	    	CheckBox dress = (CheckBox) findViewById(R.id.dress_check);  
	    	CheckBox shoes = (CheckBox) findViewById(R.id.shoes_check);
	    	CheckBox pants = (CheckBox) findViewById(R.id.pants_check); 
	    	CheckBox shorts = (CheckBox) findViewById(R.id.shorts_check);  
	    	CheckBox clothing = (CheckBox) findViewById(R.id.clothing_check);
	    	
	    	
	    	
		      switch (v.getId()) {
		     
		      case R.id.go_button :
		    	  Intent i = new Intent(this, MainActivity.class);
			         if (dress.isChecked()) {
			             currCat.add("dress");
			        	 //currCat+=" "+"dresses";
			         }   
			         if (shoes.isChecked()) {
			        	currCat.add("shoes");
			        	 //currCat+="|"+"shoes";
			         }  
			         if (pants.isChecked()) {
			        	 currCat.add("pants");
			        	 //currCat+="|"+"dresses";
			         } 
			         if (shorts.isChecked()) {
			        	 currCat.add("shorts");
			        	 //currCat+="|"+"shorts";
			         } 
			         if(clothing.isChecked()) {
			        	 currCat.add("clothing");
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
		    	  
		    	  dress.setChecked(true);
		    	  shoes.setChecked(true);
		    	  pants.setChecked(true);
		    	  shorts.setChecked(true);
		    	  clothing.setChecked(true);
		    	 
		    	  break;
		    	  
		      case R.id.deselect_all :
		    	//  Intent inten = new Intent(this, MainActivity.class);
		    	  
		    	  dress.setChecked(false);
		    	  shoes.setChecked(false);
		    	  pants.setChecked(false);
		    	  shorts.setChecked(false);
		    	  clothing.setChecked(false);
			         
		      }
	    }
	    
}
