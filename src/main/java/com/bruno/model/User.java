package com.bruno.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String email;

	private String name;

	private String password;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;
	
	//bi-directional many-to-many association to Event
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="user_has_event"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="event_id")
			}
		)
	private List<Event> events;

	public User() {
	}
	
	public User(Long id){
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	/*public void setId(Long id) {
		this.id = id;
	}*/

	public String getEmail() {
		return this.email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public Role getRole() {
		return this.role;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event event){
		this.events.add(event);
	}
	
	@Override
	public String toString() {
		return "name: " + name + "\tpermission: " + role.getRole();
	}

}