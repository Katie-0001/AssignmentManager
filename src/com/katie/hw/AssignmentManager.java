package com.katie.hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AssignmentManager {

	private static List<Assignment> assignments = new ArrayList<Assignment>();
	private static Scanner inputReader = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy", Locale.ENGLISH);

	public Date getAssignedDate(int index) {
		Assignment b = assignments.get(index);
		return b.getAssignedDate();
	}

	public Date getDueDate(int index) {
		Assignment b = assignments.get(index);
		return b.getDueDate();
	}

	public static List<Assignment> addAssignment(Assignment e) {
		assignments.add(e);
		return assignments;
	}

	public static List<Assignment> removeAssignment(int index) {
		assignments.remove(index);
		return assignments;
	}

	public boolean markComplete(int index) {
		Assignment c = assignments.get(index);
		c.setCompleted(true);
		removeAssignment(index);
		return c.isCompleted();
	}

	public List<Assignment> listAssignment() {
		assignments.sort(new Comparator<Assignment>() {
			@Override
			public int compare(Assignment a, Assignment b) {
				return a.getAssignedDate().compareTo(b.getAssignedDate());
			}
		});
		return assignments;
	}

	public static Assignment findSoonestDue() {
		Assignment soonestAssignment = null;
		for (int i = 0; i < assignments.size(); i++) {
			Assignment a = assignments.get(i);
			if (soonestAssignment == null) {
				soonestAssignment = a;
			} else if (a.getDueDate().after(soonestAssignment.getDueDate())) {
				soonestAssignment = a;
			}
		}

		return soonestAssignment;
	}

	public static void main(String[] args) {
		menuMain();
	}

	public static void menuMain() {
		// MAIN Menu
		System.out.println("=====MAIN MENU=====");
		// 1. Add Assignment
		System.out.println("1) Add Assignment");
		// 2. Remove Assignment
		System.out.println("2) Remove Assignment");
		// 3. List Assignments
		System.out.println("3) List Assignments");
		// 4. Find Earliest
		System.out.println("4) Find soonest assignment");
//		System.out.println("Enter number for corresponding option:");
		System.out.println("=====MAIN MENU=====");

		int option;
		option = inputReader.nextInt();
		inputReader.nextLine();

		switch (option) {
		case 1:
			menuAdd();
			break;
		case 2:
			menuRemove();
			break;
		case 3:
			menuList();
			break;
		case 4:
			menuEarliest();
			break;
		default:
			System.out.println("INVALID OPTION");
		}
		menuMain();
	}

	public static void menuAdd() {
		inputReader.reset();
		System.out.println("Assignment dates must be entered in date format \"mm-dd-yyyy\" ex. 02-26-2023");
		String name, assignedDate, dueDate;

		System.out.println("Enter Assignment name:");
		name = inputReader.nextLine();

		System.out.println("Enter assigned date:");
		assignedDate = inputReader.nextLine();

		System.out.println("Enter due date:");
		dueDate = inputReader.nextLine();

		try {
			Assignment a = new Assignment(formatter.parse(dueDate), formatter.parse(assignedDate), name);
			addAssignment(a);
			System.out.println("Assignment " + a.getName() + " successfully added");
		} catch (ParseException e) {
			System.out.println("Invalid date entered" + e.getMessage());
		}
	}

	public static void menuRemove() {
		menuList();
		try {
			System.out.println("Enter index of number to be deleted");
			int index = inputReader.nextInt();
			inputReader.nextLine();
			String assignmentName = assignments.get(index).getName();
			removeAssignment(index);
			System.out.println(assignmentName + " successfully removed");
		} catch (Exception e) {
			System.out.println("Failed to delete on invalid index");
		}
	}

	public static void menuList() {
		for (int i = 0; i < assignments.size(); i++) {
			Assignment a = assignments.get(i);
			System.out.println(i + ". " + a.getName());
		}
	}

	public static void menuEarliest() {
		Assignment a = findSoonestDue();
		System.out.println("Soonest Assignment: " + a.getName() + " due: " + a.getDueDate());
	}

}