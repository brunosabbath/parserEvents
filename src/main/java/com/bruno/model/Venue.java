package com.bruno.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the venue database table.
 * 
 */
@Entity
public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String address;

	@Column(name="address_complement")
	private String addressComplement;

	private String city;

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

	public Venue() {
	}

	public Venue(long id){
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public Venue setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getAddressComplement() {
		return this.addressComplement;
	}

	public Venue setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
		return this;
	}

	public String getCity() {
		return this.city;
	}

	public Venue setCity(String city) {
		this.city = city;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public Venue setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public Venue setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public Venue setFacebook(String facebook) {
		this.facebook = facebook;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Venue setName(String name) {
		this.name = name;
		return this;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public Venue setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public Venue setTwitter(String twitter) {
		this.twitter = twitter;
		return this;
	}

	public String getWebsite() {
		return this.website;
	}

	public Venue setWebsite(String website) {
		this.website = website;
		return this;
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

}