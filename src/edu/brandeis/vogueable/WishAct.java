package edu.brandeis.vogueable;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
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
}
