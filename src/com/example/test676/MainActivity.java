package com.example.test676;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.example.test676.R;
import com.example.test676.classes.RealEstateAgent;
import com.example.test676.constants.Constants;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ArrayList<RealEstateAgent> realtors;
	
	ArrayAdapter<String> reaAdapter;
	ListView reaListView;
	ListAdapter reaListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    URL url;
		try {
			url = new URL(Constants.REA_JSON_URL);
		    DownloadRealtors dl = new DownloadRealtors();
		    dl.execute(url);
		    //TODO add loading progress
		    
		    reaListView = (ListView) findViewById(R.id.reaListView);
		    
		    //TODO run loading while running synchronously
		    while (!dl.isFinished()) {
		    	try {
		    		dl.get(10,TimeUnit.SECONDS);
		    	} catch (TimeoutException e) {
		        		e.printStackTrace();
		        }
		    }
		  
		    Log.v(this.toString(), "add");
		    realtors = dl.getRealtorList();
		    reaListAdapter = new ListAdapter(this,realtors);
		    reaListView.setAdapter(reaListAdapter);
		      
		} catch (MalformedURLException e) {
			// TODO add popup blaming the website
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
    		//run the loop again
    		e.printStackTrace();
    		Log.v(this.toString(), "dl failed");
    	} catch (Exception e) {
			e.printStackTrace();
		}
		
	}  

}


