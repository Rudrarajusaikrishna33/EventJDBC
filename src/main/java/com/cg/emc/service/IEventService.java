package com.cg.emc.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.emc.exception.EventManagementException;
import com.cg.emc.model.Event;

public interface IEventService {
	String add(Event event) throws EventManagementException;
	Boolean remove(String id)throws EventManagementException;
	List<Event> getAll()throws EventManagementException;
	void persist()throws EventManagementException;
	List<Event> listALLEvents(LocalDate dateScheduled) throws EventManagementException;
	List<Event> listALLEvents(String location) throws EventManagementException;
}