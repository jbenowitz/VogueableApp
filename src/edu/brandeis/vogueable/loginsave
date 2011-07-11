package edu.brandeis.vogueable;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	

	/**
	 * onCreate override creates the layout and finds the go button
	 */
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    
	    View goButton = findViewById(R.id.go_button);
	    goButton.setOnClickListener(this);
	    goButton.getBackground().setColorFilter(new LightingColorFilter(0x000000, 0x000000));
	}
	 
	
	/**
	 * onClick override creates the go button, linking it to the category chooser
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.go_button :
	  	  	Intent i = new Intent(this, CategoryChooser.class);
		         startActivity(i);
		         break;
		}
	}
}
