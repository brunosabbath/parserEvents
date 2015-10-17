package com.bruno.modelporra;


import javax.annotation.Nullable;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


//public class Venue extends BaseUser{
public class Venue implements PasswordInterf{

	@Id
	private ObjectId id;
	private String venueName;
	private String venueDescription;
	private String website;
	private String facebook;
	private String twitter;
	private String address;
	private String email;
	private String password;
	private String telephone;
	
	public Venue(String venueName, String venueDescription, String website, 
			String facebook, String twitter, String address, String email, String password, String telephone) {
		this.venueName = venueName;
		this.venueDescription = venueDescription;
		this.website = website;
		this.facebook = facebook;
		this.twitter = twitter;
		this.address = address;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
	}
	
	public Venue(){
		this.id = new ObjectId();
		this.venueName = "";
		this.venueDescription = "";
		this.website = "";
		this.facebook = "";
		this.twitter = "";
		this.address = "";
		this.email = "";
		this.password = "";
		this.telephone = "";
	}
	
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getId() {
		return id.toString();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVenueDescription() {
		return venueDescription;
	}

	public void setVenueDescription(String venueDescription) {
		this.venueDescription = venueDescription;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tDescription: " + venueDescription + "\twebsite: " + website
				+ "\nfacebook: " + facebook + "\ttwitter: " + twitter + "\taddress: " + address;
	}
	
	public void setPasswordWitEncrypt(String password){
        this.password = PasswordHelper.encrypt(password);
    }
	
	public void setPasswordWitEncrypt(){
        this.password = PasswordHelper.encrypt(this.password);
    }
	
}
