package edu.brandeis.vogueable;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

public class WishAct extends Activity {
	
	Provider prov; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allwishes);
        RealProxy proxy = new RealProxy();
		Context context = this;
        prov = Provider.instance(proxy, "AndroidUserName",context, "item from pref");
        
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new Helper(this,prov));
    }
    
    /*public void onClick(View v) {
		switch (v.getId()) {

		//Like Button onClick
		case R.id.like_button :  
	         break;
		}
    }*/
}
