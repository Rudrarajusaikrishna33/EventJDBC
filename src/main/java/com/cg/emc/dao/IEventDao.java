package com.cg.emc.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.emc.exception.EventManagementException;
import com.cg.emc.model.Event;

public interface IEventDao {
	String add(Event event) throws EventManagementException;
	Boolean remove(String id)throws EventManagementException;
	List<Event> getAll()throws EventManagementException;
	void persist()throws EventManagementException;
	List<Event> listAllEvents(String location) throws EventManagementException;
	List<Event> listAllEvents(LocalDate dateScheduled) throws EventManagementException;

}
