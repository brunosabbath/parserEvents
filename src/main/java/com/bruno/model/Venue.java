package com.bruno.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the venue database table.
 * 
 */
@Entity
@NamedQuery(name="Venue.findAll", query="SELECT v FROM Venue v")
public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Column(name="address_complement")
	private String addressComplement;

	private String description;

	private String email;

	private String facebook;

	private String name;

	private String telephone;

	private String twitter;

	private String website;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="venue")
	private List<Event> events;

	//bi-directional many-to-one association to Hour
	@OneToMany(mappedBy="venue")
	private List<Hour> hours;

	public Venue() {
	}
	
	public Venue(String name){
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressComplement() {
		return this.addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setVenue(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setVenue(null);

		return event;
	}

	public List<Hour> getHours() {
		return this.hours;
	}

	public void setHours(List<Hour> hours) {
		this.hours = hours;
	}

	public Hour addHour(Hour hour) {
		getHours().add(hour);
		hour.setVenue(this);

		return hour;
	}

	public Hour removeHour(Hour hour) {
		getHours().remove(hour);
		hour.setVenue(null);

		return hour;
	}

}