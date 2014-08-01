package com.example.test676.classes;

/**Keeps track of teams and real estate agents
 * A team does not have an office
 * 
 * @author Steven Wance
 *
 */
public class RealEstateAgent {

   	private String id;
   	private String name;
   	private String phone_number;
   	private String photo;
   	private String rebrand;
	private String office;
	private boolean isTeam;
	
	//getters
	public String getOffice() {return this.office;}
 	public String getId(){return this.id;}
 	public String getName(){return this.name;}
 	public String getPhoneNumber() {return this.phone_number;}
 	public String getPhoto(){return this.photo;}
 	public String getRebrand() {return this.rebrand;}
 	public boolean getIsTeam() {return this.isTeam; }

	//setters
	public void setOffice(String office) {this.office = office;}
	public void setId(String id){this.id = id;}
	public void setName(String name) {this.name = name;	}
	public void setPhoneNumber(String phone_number){this.phone_number = phone_number;}
	public void setPhoto(String photo){	this.photo = photo;}
	public void setRebrand(String rebrand){this.rebrand = rebrand;}
	public void setIsTeam(boolean isTeam) {this.isTeam = isTeam; }
	
	/**This function returns a url with the specified width
	 * 
	 * @param width
	 * 	width of the image in pixels
	 * @return
	 */
	public String getPhotoURLwithSize(int width) {
		return this.photo + "/width/" + width;
	}	

}
