package com.bruno.dao;

import org.springframework.data.repository.CrudRepository;

import com.bruno.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

}
