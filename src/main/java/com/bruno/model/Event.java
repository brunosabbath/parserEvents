package com.bruno.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
public class Event implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String address;

	private String description;

	@Column(name="end_date")
	private Timestamp endDate;

	private String name;

	private String price;

	@Column(name="start_date")
	private Timestamp startDate;

	//bi-directional many-to-one association to Venue
	@ManyToOne(fetch = FetchType.EAGER)
	private Venue venue;

	//bi-directional many-to-many association to Tag
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="event_has_tag"
		, joinColumns={
			@JoinColumn(name="event_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="tag_id")
			}
		)
	private List<Tag> tags;

	//bi-directional many-to-many association to User
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="events")
	private List<User> users;

	public Event() {
	}

	public Event(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	/*public void setId(Long id) {
		this.id = id;
	}*/

	public String getAddress() {
		return this.address;
	}

	public Event setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public Event setDescription(String description) {
		this.description = description;
		return this;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public Event setEndDate(Timestamp endDate) {
		this.endDate = endDate;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Event setName(String name) {
		this.name = name;
		return this;
	}

	public String getPrice() {
		return this.price;
	}

	public Event setPrice(String price) {
		this.price = price;
		return this;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public Event setStartDate(Timestamp startDate) {
		this.startDate = startDate;
		return this;
	}

	public Venue getVenue() {
		return this.venue;
	}

	public Event setVenue(Venue venue) {
		this.venue = venue;
		return this;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public Event setTags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}