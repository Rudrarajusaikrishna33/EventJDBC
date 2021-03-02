package com.cg.emc.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.cg.emc.exception.EventManagementException;
import com.cg.emc.model.DateComparator;
import com.cg.emc.model.Event;
import com.cg.emc.model.EventManagementAppMenu;
import com.cg.emc.service.EventServiceImpl;
import com.cg.emc.service.IEventService;


public class EventManagementUi {
	private static IEventService eventService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;
	public static void main(String[] args) {

		try {
			eventService = new EventServiceImpl();
		} catch (EventManagementException e) {
			e.printStackTrace();
		}


		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		EventManagementAppMenu menu = null;

		while (menu != EventManagementAppMenu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (EventManagementAppMenu m : EventManagementAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= EventManagementAppMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = EventManagementAppMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case LISTALPHA:
					doList1();
					break;
				case LISTLOCATION:
					doSearch();
					break;
				case LISTASCN:
					doList2();
					break;
				case LISTDATE:
					doSearch2();
					break;
				case REMOVE:
					doRemove();
					break;
				case QUIT:
					System.out.println("Thank you  Come Again!");
					break;
				}
			}

		}

		scan.close();

	}
	private static void doAdd() {
		try {
			Event event = new Event();
			System.out.print("Id: ");
			event.setId(scan.next());
			System.out.print("Title: ");
			event.setTitle(scan.next());
			System.out.print("DateScheduled(dd-MM-yyyy): ");
			String dateScheduled = scan.next();

			try {
				event.setDateScheduled(LocalDate.parse(dateScheduled, dtFormater));
			} catch (DateTimeException exp) {
				throw new EventManagementException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Location: ");
			event.setLocation(scan.next());
			System.out.print("Cost: ");
			if (scan.hasNextDouble())
				event.setCost(scan.nextDouble());
			else {
				scan.next();
				throw new EventManagementException("Cost is a number");
			}

			String id = eventService.add(event);
			System.out.println("Event is Added with id: " + id);
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList1() {
		List<Event> events;
		
		try {
			events = eventService.getAll();
			if (events.isEmpty()) {
				System.out.println("No events To display");
			} else {
				Collections.sort(events);
				for (Event b : events)
					System.out.println(b);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	private static void doList2() {
		List<Event> events;
		
		try {
			events = eventService.getAll();
			if (events.isEmpty()) {
				System.out.println("No events To display");
			} else {
				Collections.sort(events,new DateComparator());
				for (Event b : events)
					System.out.println(b);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doSearch() {
		System.out.print("Location: ");
		String location = scan.next();
		List<Event> events;

		try {
			events =   eventService.listALLEvents(location);
			if (events != null) {
				System.out.println(events);
			} else {
				System.out.println("No Such Event");
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	private static void doSearch2() {
		System.out.print("DateScheduled: ");
		String dateScheduled = scan.next();
		List<Event> events;

		try {
			events =  eventService.listALLEvents(dateScheduled);
			if(events != null) {
				System.out.println(events);
			} else {
				System.out.println("No Such Event");
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	

	private static void doRemove() {
		System.out.print("ID: ");
		String id = scan.next();
		try {
			boolean isDone = eventService.remove(id);
			if (isDone) {
				System.out.println("Event is Deleted");
			} else {
				System.out.println("No Such Event");
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}

}