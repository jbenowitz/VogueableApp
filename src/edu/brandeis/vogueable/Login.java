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
	

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login);
	    
	    View goButton = findViewById(R.id.go_button);
	      goButton.setOnClickListener(this);
	      goButton.getBackground().setColorFilter(new LightingColorFilter(0x000000, 0x000000));
	      
	 /* 
	  User violeta = new User("vsoued");  
	  
	  final AccountManager manager = AccountManager.get(this);
	  
	  Account violeta = new Account("vsoued","com.google");  
	    
	  try{
		  
		  //Bundle userdata = null;
		  //manager.addAccountExplicitly(violeta, "jbs", userdata);
		  //final Account[] accounts = manager.getAccounts();
	  
	  }finally{
		  
		  Toast.makeText(Login.this, "no account", Toast.LENGTH_LONG).show();
	  }
	  /*if (accounts.length>0){
		  
	  	String names = accounts[0].name;
	  	Toast.makeText(Login.this, names, Toast.LENGTH_LONG).show();
	  } else {
		Toast.makeText(Login.this, "no account", Toast.LENGTH_LONG).show();
	  }
	  */
	}
	
	public void authenticate(User user){
		
		
		  
	  }
	 

	 
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
	
	
	/*
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.login);
	      
	      AccountManager manager = AccountManager.get(this);
	      //Account[] account = manager.getAccounts();
	      //String username = manager.getUserData(account[0], "name");
	      //String username2 = account[0].name;
	      //Account[] accountx = manager.getAccountsByType("com.google");
	      //String username3 = accountx[0].name;
	      //Toast.makeText(Login.this, username+"1", Toast.LENGTH_LONG).show();
	      //Toast.makeText(Login.this, username2+"2", Toast.LENGTH_LONG).show();
	      //Toast.makeText(Login.this, username3+"3", Toast.LENGTH_LONG).show();
	   
	   
	      // Set up click listeners for the category buttons buttons
	      View goButton = findViewById(R.id.go_button);
	      goButton.setOnClickListener(this);
//	     
	   }
	
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
	*/
}
