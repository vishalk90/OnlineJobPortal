package com.screens;

import java.net.URLConnection;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FrontPageSearchList extends Fragment {
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		v = inflater.inflate(R.layout.frontpage_listview, container, false);

		ListView lv = (ListView) v.findViewById(R.id.front_page_list);
		JSONObject Jobs=new JSONObject();

		UrlConnectivity con = new UrlConnectivity();
		URLConnection connection = con.connection("GettingJobs");

		GetJson g1 = new GetJson();
		Jobs = g1.getJsonObject("GettingJobs");

		Vector job = new Vector<String>();
		try {
			int count = Integer.parseInt(Jobs.getString("COUNT"));
			for (int i = 1; i <= count; i++) {
				job.add(Jobs.getString("JOB_CATEGORY" + i) + "("+ Jobs.getString("COUNT" + i) + ")");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lv.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, job));

		return v;

	}

}
