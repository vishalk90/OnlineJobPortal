package com.screens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JobproviderRegistration extends Activity implements OnClickListener{
	EditText CompanyName;
	EditText CompanyLocation;
	EditText CompanyRegistrationNumber;
	EditText CompanyDetails;
	Button JobProviderRegistrationButton;
	JSONObject ProfileJson;
	URLConnection connection;
	int result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobprovider_registration);
		
		Intent JobproviderIntent=getIntent();
		try {
			ProfileJson=new JSONObject(JobproviderIntent.getStringExtra("profile"));
			Log.w("json String", ProfileJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------------finding the components from Jobprovider_registration.xml---------------
		CompanyName=(EditText) findViewById(R.id.company_name);
		CompanyLocation=(EditText) findViewById(R.id.company_location);
		CompanyRegistrationNumber=(EditText) findViewById(R.id.company_registration_number);
		CompanyDetails=(EditText) findViewById(R.id.company_details);
		JobProviderRegistrationButton=(Button) findViewById(R.id.job_provider_register_button);
		
		//-------------button click listener----------------------------------------------------
		JobProviderRegistrationButton.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		//------------validations for EditText fields--------------------------------------
		if(CompanyName.getText().toString().isEmpty() && CompanyLocation.getText().toString().isEmpty() && CompanyRegistrationNumber.getText().toString().isEmpty() && CompanyDetails.getText().toString().isEmpty())
		{
			CompanyName.setError("You must enter the Company Name!");
			CompanyLocation.setError("You must enter the Company Location!");
			CompanyRegistrationNumber.setError("You must enter the Company Registration Number!");
			CompanyDetails.setError("You must enter the Company Details!");
			
		}
		else if(CompanyLocation.getText().toString().isEmpty())
		{
			CompanyLocation.setError("You must enter the Company Location!");
		}
		else if(CompanyRegistrationNumber.getText().toString().isEmpty())
		{
			CompanyRegistrationNumber.setError("You must enter the Company Registration Number!");
		}
		else if(CompanyDetails.getText().toString().isEmpty())
		{
			CompanyDetails.setError("You must enter the Company Details!");
		}
		else
		{
			//---------putting new values of this activity to ProfileJson object------------------
			try {
				ProfileJson.put("CompanyName", CompanyName.getText().toString());
				ProfileJson.put("CompanyLocation", CompanyLocation.getText().toString());
				ProfileJson.put("CompanyRegistrationNumber", CompanyRegistrationNumber.getText().toString());
				ProfileJson.put("CompanyDetails", CompanyDetails.getText().toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			Log.w("profile String of provider", ProfileJson.toString());
			
			//------code for uploading data to database--------------------------------------
			
			new Thread(new Runnable() {
				public void run() {

					try {
						// creating object of UrlConnectivity class and calling
						// connection method
						UrlConnectivity urlconnect = new UrlConnectivity();
						connection = urlconnect.connection("Registration");
						// ----------------------------------------------------------------------
						Log.d("inputString1", ProfileJson.toString());

						connection.setDoOutput(true);

						// seding json string over outputstream by using
						// outputStreamWritter
						OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
						out.write(ProfileJson.toString());
						out.close();
						// ------------------------------------------------------------------

						// featching values sent by server from inputstream by
						// using inputStreamReader
						BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

						String returnString = "";
						result = 0;

						while ((returnString = in.readLine()) != null) {
							result = Integer.parseInt(returnString);
						}
						in.close();
						// ---------------------------------------------------------------------------

						// displaying the result on android screen via toast
						// meassge
						runOnUiThread(new Runnable() {
							public void run() {
							try {
								if(result!=0)
								{
							Toast.makeText(getApplicationContext(),"You have successfully registered",Toast.LENGTH_SHORT).show();
							
								if(ProfileJson.getString("User_Type").toString().equals("provider"))
								{
									Intent JobProviderIntent=new Intent(getApplicationContext(), JobProviderProfile.class);
									startActivity(JobProviderIntent);
								}else if(ProfileJson.getString("User_Type").toString().equals("seeker"))
								{
									Intent JobSeekerIntent=new Intent(getApplicationContext(), JobSeekerProfile.class);
									startActivity(JobSeekerIntent);
								}
							
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
						});

					} catch (Exception e) {
						Log.d("Exception", e.toString());
					}

				}
			}).start();
			
			
			
		}
	}
	
	
	

}
