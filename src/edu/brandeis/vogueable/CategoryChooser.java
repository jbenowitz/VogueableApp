package edu.brandeis.vogueable;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CategoryChooser extends Activity implements OnClickListener {
	
	public ArrayList<String> currCat = new ArrayList<String>();
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.category);
	        // Set up click listeners for the category buttons buttons
		      View goButton = findViewById(R.id.go_label);
		      goButton.setOnClickListener(this);
		      
		      View dressButton = findViewById(R.id.dress_label);
		      dressButton.setOnClickListener(this);
		      View dress = findViewById(R.id.dress_label);
		      dress.setOnClickListener(this);
		      
		      View shorts = findViewById(R.id.shorts_label);
		      shorts.setOnClickListener(this);
		      
		      View pants = findViewById(R.id.pants_label);
		      pants.setOnClickListener(this);
		      
		      View shoes = findViewById(R.id.shoes_label);
		      shoes.setOnClickListener(this);
		      
		      View checkall = findViewById(R.id.check_all);
		      checkall.setOnClickListener(this);
		     
		     /* CheckBox all = (CheckBox) findViewById(R.id.check_all);  
		         if (all.isChecked()) {
		        	 CheckBox shrts = (CheckBox) findViewById(R.id.shorts_label); 
		        	 shrts.setChecked(true);
		        	 CheckBox pnts = (CheckBox) findViewById(R.id.pants_label);
		        	 pnts.setChecked(false);
		        	 CheckBox drss = (CheckBox) findViewById(R.id.dress_label); 
		        	 drss.setChecked(true);
		        	 CheckBox shs = (CheckBox) findViewById(R.id.shoes_label); 
		        	 shs.setChecked(false);
		         }
		         */
		        
		      
	    }

	    public void onClick(View v) {
		      switch (v.getId()) {
		     
		      case R.id.go_label :
		    	  Intent i = new Intent(this, MainActivity.class);
			         //startActivity(i);
			         CheckBox dress = (CheckBox) findViewById(R.id.dress_label);  
			         if (dress.isChecked()) {
			             currCat.add("dress");
			         } 
			         CheckBox shoes = (CheckBox) findViewById(R.id.shoes_label);  
			         if (shoes.isChecked()) {
			        	 currCat.add("shoes");
			         } 
			         CheckBox pants = (CheckBox) findViewById(R.id.pants_label);  
			         if (pants.isChecked()) {
			        	 currCat.add("pants");
			         } 
			         CheckBox shorts = (CheckBox) findViewById(R.id.shorts_label);  
			         if (shorts.isChecked()) {
			        	 currCat.add("shorts");
			         } 
			         
			         startActivity(i);
			         break;
			         
		      }
	    }
	    
}
