
package edu.brandeis.vogueable;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class Settings extends PreferenceActivity  {
    
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.xml.settings);
      
   }
}
