package com.example.test676;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.test676.classes.RealEstateAgent;
import com.example.test676.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import android.os.AsyncTask;
import android.util.Log;

/**This class is responsible for downloading and parsing the json file
 * 
 * @author Steven Wancewicz
 *
 */
public class DownloadRealtors extends AsyncTask<URL, Integer, Long> {

	ArrayList<RealEstateAgent> realtorResults = new ArrayList<RealEstateAgent>();
	boolean taskComplete = false;
	
	@Override
	protected Long doInBackground(URL... params) {

		Log.v(this.getClass().toString(), params[0].toString());
		InputStream source = retrieveStream(params[0].toString());
		try {
		    Gson gson = new GsonBuilder().serializeNulls().create();
		    Reader reader = new InputStreamReader(source);
		    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
		    
		    JsonArray realtorArray = jsonObject.get(Constants.JSON_REALTOR).getAsJsonArray();       // get the 'realtors' JsonElement
	        for (int i = 0; i < realtorArray.size(); i++ ) { 
	           	convertJSONtoREA(realtorArray.get(i).getAsJsonObject(), false);        	
	         }
	        
	        JsonArray teamsArray = jsonObject.get(Constants.JSON_TEAM).getAsJsonArray();
	        for (int i = 0; i < teamsArray.size(); i++) {
	           	convertJSONtoREA(teamsArray.get(i).getAsJsonObject(), true); 
	        }
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			taskComplete = true;
		}
	    
		Log.v(this.toString(), "download finished");
		
		return null;
	}
	
	public ArrayList<RealEstateAgent> getRealtorList() {
		return realtorResults;
	}
	
	public boolean isFinished() {
		return taskComplete;
	}
	
	//TODO should be using URLConnection
	//http://developer.android.com/reference/java/net/HttpURLConnection.html
    private InputStream retrieveStream(String url) {

  	    DefaultHttpClient client = new DefaultHttpClient();
  	    HttpGet getRequest = new HttpGet(url);
  	    try {
  	       HttpResponse getResponse = client.execute(getRequest);
  	       final int statusCode = getResponse.getStatusLine().getStatusCode();
  	       if (statusCode != HttpStatus.SC_OK) {
  	    return null;
  	       }
  	       HttpEntity getResponseEntity = getResponse.getEntity();
  	       return getResponseEntity.getContent();
  	    }
  	    catch (IOException e) {
  	    	e.printStackTrace();
  	       getRequest.abort();
  	    }
  	    return null;
    }

    /**converts JSON object to real estate agent object and adds to arraylist of rea's
     * 
     * @param realtorJSON
     */
    private void convertJSONtoREA(JsonObject realtorJSON, boolean isTeam) {
//TODO make this method be synchronously so main activity can load a bit faster
    	RealEstateAgent rea = new RealEstateAgent();
    	
        String name = realtorJSON.get(Constants.JSON_REA_NAME).getAsString();
        rea.setName(name);
        
        String id = realtorJSON.get(Constants.JSON_REA_ID).getAsString();
        rea.setId(id);
        
        String rebrand = realtorJSON.get(Constants.JSON_REA_REBRAND).getAsString();
        rea.setRebrand(rebrand);
        
        rea.setIsTeam(isTeam);
        
        if (!isTeam) {
        	try {
	        	String office = realtorJSON.get(Constants.JSON_REA_OFFICE).getAsString();
	            rea.setOffice(office);
        	} catch (UnsupportedOperationException uoe) {
        		rea.setOffice(Constants.NO_OFFICE);
        	}
        } 
        
        String phoneNumber = realtorJSON.get(Constants.JSON_REA_PHONE_NUMBER).getAsString();
        rea.setPhoneNumber(phoneNumber);
    	try {
    		String photoURL = realtorJSON.get(Constants.JSON_REA_PHOTO_URL).getAsString(); 
    		rea.setPhoto(photoURL);
    	} catch (UnsupportedOperationException e) {
    		e.printStackTrace();
    	}
    	
    	realtorResults.add(rea);   
    }

}
