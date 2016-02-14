package com.screens;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FrontPage extends Activity implements OnItemClickListener, OnClickListener {
	JSONObject Jobs;
	ListView lv;
	@SuppressWarnings("rawtypes")
	Vector job;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front_page);
		ActionBar actionBar = getActionBar();
		
		//actionBar.setBackgroundDrawable();
		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#818285")));
		actionBar.show();
		lv = (ListView) findViewById(R.id.front_page_list);
		
		
		Jobs = new JSONObject();

		new Thread(new Runnable() {
								@SuppressWarnings("unchecked")
			public void run() {

				GetJson g1 = new GetJson();
				Jobs = g1.getJsonObject("GettingJobs");

				job = new Vector<String>();
				try {
					int count = Integer.parseInt(Jobs.getString("COUNT"));
					for (int i = 1; i <= count; i++) {
						job.add(Jobs.getString("JOB_CATEGORY" + i).toString()+ " [" + Jobs.getString("COUNT" + i).toString()+ "]");
						Log.d("json in while",Jobs.getString("JOB_CATEGORY" + i).toString()+ " ["+ Jobs.getString("COUNT" + i).toString() + "]");
					}
	
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					runOnUiThread(new Runnable() {
					public void run() {

						lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, job));

					}
				});

			}
		}).start();
		
		
		lv.setOnItemClickListener(this);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String t= (String) arg0.getItemAtPosition(arg2);
		Toast.makeText(getApplicationContext(), t,Toast.LENGTH_LONG).show();
	}
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.profile:
	    	Intent profile=new Intent( getApplicationContext(), MainActivity.class);
			startActivity(profile);
	      break;
	    case R.id.applied_jobs:
		      Toast.makeText(this, "applied job", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    case R.id.saved_jobs:
		      Toast.makeText(this, "saved job", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    case R.id.recommanded_jobs:
		      Toast.makeText(this, "recommanded job", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    case R.id.settings:
	      Toast.makeText(this, "settings", Toast.LENGTH_SHORT)
	          .show();
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
	}

}
