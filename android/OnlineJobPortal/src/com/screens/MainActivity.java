package com.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {

	
	
	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ActionBar actionbar = getSupportActionBar();
    actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    actionbar.setTitle("PROFILE");
    Tab frag1tab = actionbar.newTab().setText("LOGIN");
    Tab frag2tab = actionbar.newTab().setText("REGISTER");

    Fragment fragment1 = new LoginFragment();
    Fragment fragment2 = new RegistrationFragment();

    frag1tab.setTabListener(new MyTabListener(fragment1));
    frag2tab.setTabListener(new MyTabListener(fragment2));

    actionbar.addTab(frag1tab);
    actionbar.addTab(frag2tab);
   
}


/*@Override
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
	// TODO Auto-generated method stub
	
	 getSupportMenuInflater().inflate(R.menu.main,  menu);
	return super.onCreateOptionsMenu(menu);
}
*/

class MyTabListener implements TabListener {
    public Fragment fragment;

    public MyTabListener(Fragment fragment) {
        this.fragment = fragment;
        
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTabSelected(Tab arg0, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    	ft.replace(R.id.fragment_container, fragment);
    	
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }
    

	}

 }
