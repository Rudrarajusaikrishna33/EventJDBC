package com.cg.emc.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.emc.exception.EventManagementException;
import com.cg.emc.model.Event;
import com.cg.emc.util.ConnectionProvider;

public class EventDaoJDBCImpl implements IEventDao{

	ConnectionProvider conProvider;

	public EventDaoJDBCImpl() throws EventManagementException {

		try {
			conProvider = ConnectionProvider.getInstance();
		} catch (ClassNotFoundException | IOException exp) {
			throw new EventManagementException("Database is not reachable");
		}
	}
	@Override
	public String add(Event event) throws EventManagementException {
		String id = null;
		if (event != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pInsert = con
							.prepareStatement(IQueryMapper.ADD_EVENT_QRY)) {

				pInsert.setString(1, event.getId());
				pInsert.setString(2, event.getTitle());
				pInsert.setDouble(3, event.getCost());
				pInsert.setString(4, event.getLocation());
				pInsert.setDate(5, Date.valueOf(event.getDateScheduled()));
				
				

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					id = event.getId();
				}
			} catch (SQLException exp) {
				throw new EventManagementException("Event is not inserted");
			}
		}
		return id;
	}

	@Override
	public Boolean remove(String id) throws EventManagementException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con
						.prepareStatement(IQueryMapper.DEL_EVENT_QRY)) {

			pDelete.setString(1, id);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			throw new EventManagementException("Event is not inserted");
		}

		return isDone;
	}

	@Override
	public List<Event> getAll() throws EventManagementException  {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_ALL_EVENTS_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setLocation(rs.getString("location"));
				event.setDateScheduled(rs.getDate("pdate").toLocalDate());
				events.add(event);
			}
			
		} catch (SQLException exp) {
			throw new EventManagementException("No Events are available.");
		}		
		return events;	
	}

	@Override
	public List<Event> listAllEvents(String location) throws EventManagementException  {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_EVENTS_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setLocation(rs.getString("location"));
				event.setDateScheduled(rs.getDate("pdate").toLocalDate());
				events.add(event);
			}
			
		} catch (SQLException exp) {
			throw new EventManagementException("No events are available.");
		}		
		return events;	
	}
	@Override
	public List<Event> listAllEvents(LocalDate dateScheduled) throws EventManagementException {
		List<Event> events=null;
		try (Connection con = conProvider.getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_DATE_EVENTS_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			events = new ArrayList<Event>();
			
			while(rs.next()){
				Event event = new Event();
				event.setId(rs.getString("id"));
				event.setTitle(rs.getString("title"));
				event.setCost(rs.getDouble("cost"));
				event.setLocation(rs.getString("location"));
				event.setDateScheduled(rs.getDate("pdate").toLocalDate());
				events.add(event);
			}
			
		} catch (SQLException exp) {
			throw new EventManagementException("No events are available.");
		}		
		return events;	
	}

	@Override
	public void persist() throws EventManagementException {
		/* no implementation  */
	}

}