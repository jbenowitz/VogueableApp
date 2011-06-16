package edu.brandeis.vogueable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class CategoryChooser extends Activity implements OnClickListener {
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.category);
	        // Set up click listeners for the category buttons buttons
		      View nextButton = findViewById(R.id.browse_label);
		      nextButton.setOnClickListener(this);
		      
		      View prevButton = findViewById(R.id.dress_label);
		      prevButton.setOnClickListener(this);
		      
		      View shorts = findViewById(R.id.shorts_label);
		      shorts.setOnClickListener(this);
		      
		      View pants = findViewById(R.id.pants_label);
		      pants.setOnClickListener(this);
	    }

	    public void onClick(View v) {
		      switch (v.getId()) {
		     
		      case R.id.browse_label :
		    	  Intent i = new Intent(this, MainActivity.class);
			         startActivity(i);
			         break;
			         
		      }
	    }
	    
}