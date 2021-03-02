package com.cg.emc.dao;

public interface IQueryMapper {
	public static final String ADD_EVENT_QRY = 
			"INSERT INTO events(id,  title, cost,location, pdate) VALUES(?,?,?,?,?)";
	public static final String DEL_EVENT_QRY = 
			"DELETE FROM events WHERE id=?";
	public static final String GET_ALL_EVENTS_QRY = 
			"SELECT * FROM events";
	public static final String GET_EVENTS_QRY = 
			"SELECT * FROM books WHERE location=?";
	public static final String GET_DATE_EVENTS_QRY = 
			"SELECT * FROM books WHERE pdate=?";

}