package com.bruno.modelporra;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Event implements PasswordInterf{

	@Id
	private ObjectId id;
	private String eventName;
	private String address;
	private String eventDescription;
	private Date startDate;
	private Date endDate;
	private List<Tag> tags;
	private City city;
	private String password;
	private Venue venue;
	@JsonIgnore
	private double jaccard;
	
	public Event(){
		this.eventName = "";
		this.address = "";
		this.eventDescription = "";
		this.startDate = new Date();
		this.endDate = new Date();
		this.city = new City("Lincoln, Nebraska");
		this.password = "";
		this.tags = new ArrayList<Tag>();
		this.venue = new Venue();
		this.jaccard = 0;
	}

	
	
	public double getJaccard() {
		return jaccard;
	}

	public void setJaccard(double jaccard) {
		this.jaccard = jaccard;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id.toString();
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Tag> getTags() {
		return tags;
	}
	
	@JsonIgnore
	public int getTotalTags(){
		return tags.size();
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	public void setPasswordWitEncrypt(String password){
        this.password = PasswordHelper.encrypt(password);
    }
	
	public void setPasswordWitEncrypt(){
        this.password = PasswordHelper.encrypt(this.password);
    }
	
}
