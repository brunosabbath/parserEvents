package com.bruno.modelporra;



import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Feedback {

	@Id
	private ObjectId id;
	private String name;
	private String email;
	private String feedback;
	private Date dateFeedback;
	
	public Feedback(){}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getDateFeedback() {
		return dateFeedback;
	}

	public void setDateFeedback(Date dateFeedback) {
		this.dateFeedback = dateFeedback;
	}
	
}
