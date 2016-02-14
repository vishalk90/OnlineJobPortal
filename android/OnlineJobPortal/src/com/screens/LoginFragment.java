package com.screens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;

public class LoginFragment extends SherlockFragment implements OnClickListener{
	URLConnection url;
	JSONObject j1;
	View v;
	EditText UserName;
	EditText Password;
	int result;
	Button btn1;
	StringBuffer br;
	Handler h;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 v=inflater.inflate(R.layout.login_fragment, container,false);
		Log.d("Fragment_1", "MIKEL - onCreateView");
		h=new Handler();
		
		UserName=(EditText) v.findViewById(R.id.username);
		 Password=(EditText) v.findViewById(R.id.password);
		 
		 
		btn1=(Button) v.findViewById(R.id.loginButton);
			

		 btn1.setOnClickListener(this);
		 

         // currentContext.startActivity(activityChangeIntent);
		
        
		 return v;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(UserName.getText().toString().isEmpty() && Password.getText().toString().isEmpty())
		{
			
			UserName.setError(getString(R.string.username_error));
			Password.setError(getString(R.string.password_error));
		}
		else if(UserName.getText().toString().isEmpty())
		{
			Password.setError("Please Enter Username First!");
		}else if(Password.getText().toString().isEmpty())
		{
			Password.setError("Please Enter Password First!");
		}else
		{
	
		   Log.d("username",UserName.getText().toString());
		new Thread(new Runnable() {
			public void run() {
				
				try{
					 
					j1=new JSONObject();
				
						j1.put("user_name", UserName.getText());
						j1.put("password", Password.getText());
						
						
						
						 
					
				 UrlConnectivity urlconnect=new UrlConnectivity();
				 url=urlconnect.connection("LoginValidation");
                  
					url.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(url.getOutputStream());
                    out.write(j1.toString());
                    out.close();
                    
                    
                    
                    
                    
                    

	                            BufferedReader in = new BufferedReader(new InputStreamReader(url.getInputStream()));
	 
	                            String returnString="";
	                            result =0;
	                           br=new StringBuffer();
	 
	                            while ((returnString = in.readLine()) != null) 
	                            {
	                                br.append(returnString);
	                            }
	                            in.close();
	                            
                  

                    }catch(Exception e)
                    {
                        Log.d("Exception",e.toString());
                    }
				
		if(br.toString().equals("seeker"))
        	
        {
			Intent activityChangeIntent = new Intent(getActivity(), JobSeekerProfile.class);
			startActivity(activityChangeIntent);
			LoginFragment.this.startActivity(activityChangeIntent);
        	        	
        }else if(br.toString().equals("provider"))
        	
        {
        	Intent activityChangeIntent = new Intent(getActivity(), JobProviderProfile.class);
			startActivity(activityChangeIntent);
			LoginFragment.this.startActivity(activityChangeIntent);
        }
        else
        {
        	h.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					AlertDialog.Builder login_failed= new AlertDialog.Builder(getActivity());
		        	login_failed.setMessage(getString(R.string.Username_or_password_error));
		        	login_failed.setTitle(getString(R.string.login_failed));
		        	login_failed.show();
				}
			});
        	Log.w("vishals log", "wronge id password");
        }
				
}}).start();
		
		}
		
	}
}