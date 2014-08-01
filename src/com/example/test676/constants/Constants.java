package com.example.test676.constants;

/**Keeps track of all constants used w/in app
 * 
 * @author Steven Wancewicz
 *
 */
public class Constants {

	//website url
	public static final String REA_JSON_URL = "http://www.ebby.com/realtor_list?format=ios";
	
	//these are keys used when starting OneAgentActivity in listAdapter
	public static final String AGENT_ID = "agentID";
	public static final String AGENT_NAME = "agentName";
	public static final String AGENT_PHONE_NUMBER = "agentPhoneNumber";
	public static final String AGENT_PHOTO_URL = "agentPhotoURL";
	public static final String AGENT_REBRAND = "agentRebrad";
	public static final String AGENT_OFFICE = "agentOffice";
	public static final String AGENT_IS_TEAM = "agentIsTeam";
	
	//For real estate agents w/o office
	public static final String NO_OFFICE = "No Office";
	
	//widths of images, large is for one agent, small is for icons
	public static final int LARGE_WIDTH = 500;
	public static final int SMALL_WIDTH = 100;
	
	//these are JSON constant names
	public static final String JSON_REALTOR = "realtors";
	public static final String JSON_TEAM = "teams";
	public static final String JSON_REA_NAME = "name";
	public static final String JSON_REA_ID = "id";
	public static final String JSON_REA_REBRAND = "rebrand";
	public static final String JSON_REA_OFFICE = "office";
	public static final String JSON_REA_PHONE_NUMBER = "phone_number";
	public static final String JSON_REA_PHOTO_URL = "photo";
	
}
