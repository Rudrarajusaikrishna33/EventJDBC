package com.cg.emc.model;

import java.io.Serializable;
import java.time.LocalDate;




public class Event implements Serializable, Comparable<Event> {
	private String id;
	private String title;
	private LocalDate dateScheduled;
	private String location;
	private double cost;
	
	public Event() {
		/*default constructor */
	}
	public Event(String id, String title, LocalDate dateScheduled, String location, double cost) {
		super();
		this.id=id;
		this.title=title;
		this.dateScheduled=dateScheduled;
		this.location=location;
		this.cost=cost;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDateScheduled() {
		return dateScheduled;
	}
	public void setDateScheduled(LocalDate dateScheduled) {
		this.dateScheduled = dateScheduled;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("ID: ");
		output.append(id);
		output.append(" Title: ");
		output.append(title);
		output.append(" DateScheduled: ");
		output.append(dateScheduled);
		output.append(" Location: ");
		output.append(location);
		output.append(" Cost: ");
		output.append(cost);
		return output.toString();
	}
	@Override
	public int compareTo(Event event) {
		String firstLocation = this.location;
		String secondLocation = event.location;
		return firstLocation.compareTo(secondLocation);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
}