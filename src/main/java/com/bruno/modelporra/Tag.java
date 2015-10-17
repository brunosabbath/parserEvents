package com.bruno.modelporra;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Tag {

	private String nameTag;
	
	public Tag(String name){
		this.nameTag = name;
	}

	public Tag(){}
	

	public String getNameTag() {
		return nameTag;
	}

	public void setNameTag(String name) {
		this.nameTag = name;
	}
	
	@Override
	public String toString() {
		return "nameTag: " + nameTag;
	}
	
}
