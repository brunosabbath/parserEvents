package com.bruno.modelporra;


import java.util.List;



public class TagsUtils {

	public static StringBuilder printTags(List<Tag> tags){
		StringBuilder s = new StringBuilder();
		for(Tag t : tags)
			s.append(t);
		
		return s;
	}
	
}
