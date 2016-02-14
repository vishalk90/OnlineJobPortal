package com.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JobProviderProfile extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.job_provider_profile);
		Button post =(Button) findViewById(R.id.postbutton);
		post.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent post_job=new Intent(getApplicationContext(), PostAjob.class);
		startActivity(post_job);
	}

	
	
}
