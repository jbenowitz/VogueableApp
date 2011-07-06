package edu.brandeis.vogueable;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
/**
 * This activity allows the user to confirm that he wants to exit the app and purchase an item from an external URL
 * 
 * @author gasparobimba
 *
 */
public class PurchaseItem extends Activity implements OnClickListener {

	String link="";
    Bundle extras;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);

		/*warn the user that they will be redirected to a different page*/
		Toast.makeText(this, "You shall be redirected to an external page", Toast.LENGTH_LONG).show();

		/*
		 * Buttons onClick Listener for confirming that you are buying an item
		 * 
		 */
		View buyButton = findViewById(R.id.getit_button);
		buyButton.setOnClickListener(this);

		//View laterButton = findViewById(R.id.notnow_button);
		//laterButton.setOnClickListener(this);

		/*get parameter from the Main Activity's current item attributes*/ 
		extras = getIntent().getExtras();
		if(extras !=null){
			link= extras.getString("URL");

		}



	}


	@Override
	//assign actions to listened activity
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.getit_button:
			final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://vogueable.heroku.com/"));//a link from current item will replace this url
			startActivity(intent);

			break;

		//case R.id.notnow_button:
		//	startActivity(new Intent(this, MainActivity.class));
		//	break;
		}
	}

}