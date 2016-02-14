package com.screens;

import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class RegistrationFragment extends SherlockFragment implements OnClickListener{
	View v;
	EditText username;
	EditText password;
	EditText repassword;
	EditText lastname;
	EditText email;
	EditText address;
	EditText phone;
	EditText city;
	EditText postalcode;
	Button create;
	JSONObject profile;
	URLConnection connection;
	int result;
	String passWord;
	String repassWord;
	RadioGroup jobtype;
	int jobType;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("Fragment_2", "MIKEL - onCreateView");
		
		
		

		v=inflater.inflate(R.layout.registration_fragment, container,false);
		username=(EditText) v.findViewById(R.id.rusername);
		password=(EditText) v.findViewById(R.id.rpass);
		repassword=(EditText) v.findViewById(R.id.rrpass);
		lastname=(EditText) v.findViewById(R.id.lastname);
		email=(EditText) v.findViewById(R.id.remail);
		address=(EditText) v.findViewById(R.id.raddress);
		phone=(EditText) v.findViewById(R.id.rphonenumber);
		city=(EditText) v.findViewById(R.id.city);
		postalcode=(EditText) v.findViewById(R.id.pcode);
		create=(Button) v.findViewById(R.id.registerButton);
		jobtype=(RadioGroup) v.findViewById(R.id.job_type);
		create.setOnClickListener(this);
		return v;

	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		profile=new JSONObject();
		try {
			profile.put("UserName", username.getText().toString());
			profile.put("Password", password.getText().toString());
			profile.put("LastName", lastname.getText().toString());
			profile.put("Email", email.getText().toString());
			profile.put("Address", address.getText().toString());
			profile.put("Phone", phone.getText().toString());
			profile.put("City", city.getText().toString());
			profile.put("PostalCode", postalcode.getText().toString());
				
			
		Log.d("inputString", profile.toString());
		CharSequence cs1 = "@";
		CharSequence cs2=".com";
		passWord=password.getText().toString();
		repassWord=repassword.getText().toString();
		
		jobType=jobtype.getCheckedRadioButtonId();
		
		if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty() && repassword.getText().toString().isEmpty() && email.getText().toString().isEmpty() && phone.getText().toString().isEmpty() && postalcode.getText().toString().isEmpty() && address.getText().toString().isEmpty() )
		{
			username.setError("You must enter User Name");
			password.setError("password did not match");
			repassword.setError("password did not match");
			email.setError("You must enter Email Address");
			phone.setError("You must enter Phone Number!");
			postalcode.setError("You must enter Postal code!");
			address.setError("You must enter your Address!");
		}
		else /*if(username.getText().toString().isEmpty())
		{
			username.setError("You must enter User Name");
		}
		else*/ if(password.getText().toString().isEmpty())
			{
			password.setError("password did not match");
			
			}
		else if(repassword.getText().toString().isEmpty())
			{
			repassword.setError("password did not match");
			}
		else  if(email.getText().toString().isEmpty())
			{
				email.setError("You must enter Email Address");
			}
		else if(!email.getText().toString().contains(cs1))
		{
			email.setError("@ or .com is not present");
		}else if(!email.getText().toString().contains(cs2))
				{
			email.setError("@ or .com is not present");
				}
		else if(!passWord.equals(repassWord))
				{
			password.setError("password didn't match!");
			repassword.setError("password didn't match with above field!");
		}else if(address.getText().toString().isEmpty())
				{
			address.setError("You must enter your Address!");
		}else if(phone.getText().toString().isEmpty())
				{
			phone.setError("You must enter Phone Number!");
		}else if(postalcode.getText().toString().isEmpty())
				{
			postalcode.setError("You must enter Postal code!");
		}else
		{
			switch (jobType) {
			case R.id.job_seeker:
				Intent JobSeekerRegistration=new Intent(getActivity(),JobseekerRegistration.class);
				profile.put("User_Type", "seeker");
				JobSeekerRegistration.putExtra("profile", profile.toString());
				startActivity(JobSeekerRegistration);
				break;
				
			case R.id.job_provider:
				Intent JobProviderRegistration=new Intent(getActivity(),JobproviderRegistration.class);
				profile.put("User_Type", "provider");
				JobProviderRegistration.putExtra("profile", profile.toString());
				startActivity(JobProviderRegistration);
				break;
			default:
				
				break;
			}
		}
			
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
