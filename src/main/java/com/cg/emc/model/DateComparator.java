package com.cg.emc.model;

import java.time.LocalDate;
import java.util.Comparator;

public class DateComparator implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		LocalDate firstDate = o1.getDateScheduled();
		LocalDate otherDate = o2.getDateScheduled();
		return firstDate.compareTo(otherDate);
	}

}