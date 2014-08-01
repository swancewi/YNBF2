package com.example.test676;

import com.example.test676.classes.RealEstateAgent;
import com.example.test676.constants.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OneAgentActivity extends Activity {

	RealEstateAgent rea;
	
	private ImageView agentPhoto;
	private TextView agentName;
	private TextView agentPhoneNumber;
	private TextView agentOffice;
	
	private static ImageLoader imageLoader = ImageLoader.getInstance();

	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_agent);
		
		agentPhoto = (ImageView)  findViewById(R.id.imageREA);
		agentName = (TextView) findViewById(R.id.nameREA);
		agentPhoneNumber = (TextView) findViewById(R.id.phoneNumberREA);
		agentOffice = (TextView) findViewById(R.id.officeREA);
		
		//grab real estate agent or team info:
		rea = new RealEstateAgent();
		try {
			Bundle data = getIntent().getExtras();
			rea.setName(data.getString(Constants.AGENT_NAME));
			rea.setPhoneNumber(data.getString(Constants.AGENT_PHONE_NUMBER));
			rea.setPhoto(data.getString(Constants.AGENT_PHOTO_URL));
			rea.setOffice(data.getString(Constants.AGENT_OFFICE));
			rea.setIsTeam(data.getBoolean(Constants.AGENT_IS_TEAM));
			
			imageLoader.displayImage(rea.getPhotoURLwithSize(Constants.LARGE_WIDTH), agentPhoto, null, null); 
			agentName.setText(rea.getName());
			agentPhoneNumber.setText(rea.getPhoneNumber());
			if (rea.getIsTeam()) {
				agentOffice.setVisibility(View.GONE);
			}
			else { 
				agentOffice.setText(rea.getOffice());
			}
			
			displayToast();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	
	private void displayToast() {
		Toast.makeText(this, rea.getName(), Toast.LENGTH_SHORT).show();
	}
}
