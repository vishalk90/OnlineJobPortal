package com.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.explorer.FileChooser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class JobseekerRegistration extends Activity{

	Button JobSeekerRegistrationButton;
	JSONObject ProfileJson;
	URLConnection connection;
	EditText FilePathName;
	int result;
	private static final int REQUEST_PATH = 1;
	String curFileName;
	String curFilePath;
	private int k;
	StringBuilder FileDetails;
	File UploadingFile;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jobseeker_registration);
		
		Intent JobproviderIntent=getIntent();
		k=0;
		try {
			ProfileJson=new JSONObject(JobproviderIntent.getStringExtra("profile"));
			Log.w("json String", ProfileJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------------finding the components from Jobprovider_registration.xml---------------
		
		JobSeekerRegistrationButton=(Button) findViewById(R.id.job_provider_register_button);
		FilePathName= (EditText) findViewById(R.id.filePathName);
		
		//-------------button click listener----------------------------------------------------
	//JobSeekerRegistrationButton.setOnClickListener(this);
		
	}
	
	
	//-------this method get called after clicking on Upload Button of this activity
	 public void getfile(View view){ 
	    	Intent intent1 = new Intent(this, FileChooser.class);
	        startActivityForResult(intent1,REQUEST_PATH);
	    }
	 
	 // Listen for results.
	    protected void onActivityResult(int requestCode, int resultCode, Intent data){
	        // See which child activity is calling us back.
	    	if (requestCode == REQUEST_PATH){
	    		if (resultCode == RESULT_OK) { 
	    			curFileName = data.getStringExtra("GetFileName"); 
	    			FilePathName.setText(curFileName);
	    			curFilePath=data.getStringExtra("GetPath");
	    		}
	    	 }
	    }
	  //-------this method get called after clicking on Register Button of this activity
	    public void registerJobSeeker(View view){ 
	    	//resume uploading code comes here-------------------
			try {
			Log.w("button", "register button got clicked!");
			Log.w("file path", curFilePath+"/"+curFileName);
			//UploadingFile=new File(/*curFilePath+"/"+*/"sdcard/"+curFileName);
			
			
			FileInputStream fis=new FileInputStream(UploadingFile);
			
			
			 // Read till the end of file
			
			Log.w("file details", readFromFile());
			
			
			
			
			
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	    
	    private String readFromFile() {

	        String ret = "";

	        try {
	            InputStream inputStream = openFileInput("sdcard/"+curFileName);

	            if ( inputStream != null ) {
	                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	                String receiveString = "";
	                StringBuilder stringBuilder = new StringBuilder();

	                while ( (receiveString = bufferedReader.readLine()) != null ) {
	                    stringBuilder.append(receiveString);
	                }

	                inputStream.close();
	                ret = stringBuilder.toString();
	            }
	        }
	        catch (FileNotFoundException e) {
	            Log.e("login activity", "File not found: " + e.toString());
	        } catch (IOException e) {
	            Log.e("login activity", "Can not read file: " + e.toString());
	        }

	        return ret;
	    }
	


}
