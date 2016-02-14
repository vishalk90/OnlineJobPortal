package com.screens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostAjob extends Activity implements OnClickListener {

	EditText job_title;
	EditText job_details;
	EditText designation;
	EditText salary;
	EditText vacancies;
	EditText catagory;
	EditText dead_line;
	JSONObject job;
	int result;

	URLConnection connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.post_job);

		job_title = (EditText) findViewById(R.id.jobtitle);
		job_details = (EditText) findViewById(R.id.jobdetails);
		designation = (EditText) findViewById(R.id.designation);
		salary = (EditText) findViewById(R.id.salary);
		vacancies = (EditText) findViewById(R.id.vacancies);
		catagory = (EditText) findViewById(R.id.catagory);
		dead_line = (EditText) findViewById(R.id.deadline);
		Button upload = (Button) findViewById(R.id.uploadbutton);
		upload.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		job = new JSONObject();
		try {
			job.put("JOB_TITLE", job_title.getText());
			job.put("JOB_DETAILS", job_details.getText());
			job.put("JOB_POST", designation.getText());
			job.put("JOB_SALARY", salary.getText());
			job.put("JOB_VACANCIES", vacancies.getText());
			job.put("JOB_CATOGORY", catagory.getText());
			job.put("JOB_DEADLINE", dead_line.getText());

			Log.d("inputString", job.toString());
			new Thread(new Runnable() {
				public void run() {

					try {
						// creating object of UrlConnectivity class and calling
						// connection method
						UrlConnectivity urlconnect = new UrlConnectivity();
						connection = urlconnect.connection("PostAJob");
						// ----------------------------------------------------------------------
						Log.d("inputString1", job.toString());

						connection.setDoOutput(true);

						// seding json string over outputstream by using
						// outputStreamWritter
						OutputStreamWriter out = new OutputStreamWriter(
								connection.getOutputStream());
						out.write(job.toString());
						out.close();
						// ------------------------------------------------------------------

						// featching values sent by server from inputstream by
						// using inputStreamReader
						BufferedReader in = new BufferedReader(
								new InputStreamReader(connection
										.getInputStream()));

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

								Toast.makeText(getApplicationContext(),
										result + " Job got Posted",
										Toast.LENGTH_SHORT).show();

							}
						});

					} catch (Exception e) {
						Log.d("Exception", e.toString());
					}

				}
			}).start();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
