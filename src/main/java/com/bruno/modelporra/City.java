package com.bruno.modelporra;



import javax.annotation.Nullable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class City {

	@Id @Nullable
	private ObjectId id;
	private String name;
	public static final City lincoln = new City(new ObjectId("55afe8020364b4b91b9db2c4"),"Lincoln, Nebraska");
	
	public City(String name){
		this.name = name;
	}
	
	public City(ObjectId id, String name){
		this.id = id;
		this.name = name;
	}
	
	public City(){}
	
	public String getId() {
		return id.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "city: " + name;
	}
	
}
