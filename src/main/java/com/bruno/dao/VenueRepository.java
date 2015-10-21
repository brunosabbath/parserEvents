package com.bruno.dao;

import org.springframework.data.repository.CrudRepository;

import com.bruno.model.Venue;

public interface VenueRepository extends CrudRepository<Venue, Long>{
	
}
