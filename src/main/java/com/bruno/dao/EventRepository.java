package com.bruno.dao;

import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;

import com.bruno.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

	Event findEventByNameAndStartDateAndEndDate(String name, Timestamp startDate, Timestamp endDate);
	
}
