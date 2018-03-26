import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * @author joelsieber
 *
 */
public class Menu {
	
	
	public Company company;
	
	
	
	public Menu() {
		this.company = new Company();
	}
	
	
	
	public Company getCompany() {
		return company;
	}
	
	
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu myMenu = new Menu();
		
		myMenu.processUserChoices();
	}



	/**
	 * displays the initial menu
	 */
	public void displayStartMenu()
	{
		/* ask the user to select one of the following options
		 * by typing a corresponding number to the option they
		 * choose
		 */
		System.out.println("Please select one of the options below: ");
		System.out.println("1. Load company's data from file");
		System.out.println("2. Save company's data to file");
		System.out.println("3. Employee's view");
		System.out.println("4. Manager's view");
		System.out.println("0. Exit");
	}
	
	
	
	/**
	 * displays the employee's menu
	 */
	public void displayEmployeeMenu()
	{
		/* ask the user to select one of the following options
		 * by typing a corresponding number to the option they
		 * choose
		 */
		System.out.println("Please select one of the options below: ");
		System.out.println("1. Add meeting");
		System.out.println("2. Search meetings");
		System.out.println("3. Display meetings");
		System.out.println("4. Remove meeting");
		System.out.println("5. Edit existing meeting");
		System.out.println("0. Return to previous menu");
	}
	
	
	
	/**
	 * displays the manager's menu
	 */
	public void displayManagerMenu()
	{
		/* ask the user to select one of the following options
		 * by typing a corresponding number to the option they
		 * choose
		 */
		System.out.println("Please select one of the options below: ");
		System.out.println("1. Add employee");
		System.out.println("2. Search employees");
		System.out.println("3. Remove employee");
		System.out.println("4. Plan group meeting");
		System.out.println("0. Return to previous menu");
	}
	
	
	
	/**
	 * Processes the user's menu choices
	 */
	public void processUserChoices()
	{
		String choice;
		Company theCompany = getCompany();
		do // do the following while the user's choice is not 0
		{
			displayStartMenu(); // display the menu
			/* Receive the option the user has chosen
			 * and put it into the choice field
			 */
			Scanner s1 = new Scanner(System.in);
			System.out.println("\nOption: ");
			choice = s1.nextLine();
		
			if (choice.equals("1")) // if the user enters 1 as their choice, open company's data from file
			{
				ReadFile(theCompany);
			}
			else if (choice.equals("2")) // If the user enters 2 as their choice, save company's data to file
			{
				SaveFile(theCompany);
			}
			else if (choice.equals("3")) // If the user enters 3 as their choice, an employee wants to manage their diary
			{
				System.out.println("Please enter your name:");
				String name = s1.nextLine();
				Employee theEmployee = theCompany.searchEmployee(name);
				if (theEmployee != null) {
					System.out.println("Now entering employees view...");
					employeesView(theCompany.searchEmployee(name));
				} else {
					System.out.println("There is no employee with this name");
				}
				
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice,
			{
				managersView(theCompany);
			}
			else if (choice.equals("0")) // If the user enters 0 as their choice,
			{
				System.out.println("Goodbye!"); //Display "Goodbye!"
				// The program will end if this method is chosen
			}
			else // if the user has not entered 1,2,3,4 or 0,
			{
				System.out.println("Invalid option. Please enter 1,2,3,4, or 0"); // Display an error message
			}
			
		}
		while (! choice.equals("0"));
	}
	
	
	
	/**
	 * Processes the user's menu choices
	 */
	public void employeesView(Employee theEmployee)
	{
		String choice;
		do // do the following while the user's choice is not 0
		{
			displayEmployeeMenu(); // display the menu
			/* Receive the option the user has chosen
			 * and put it into the choice field
			 */
			Scanner s1 = new Scanner(System.in);
			System.out.println("\nOption: ");
			choice = s1.nextLine();
		
			if (choice.equals("1")) // if the user enters 1 as their choice, open company's data from file
			{
				addMeeting(theEmployee);
			}
			else if (choice.equals("2")) // If the user enters 2 as their choice, save company's data to file
			{
				Meeting found = searchMeetings(theEmployee);
				if (found != null) {
					System.out.println("A meeting has been found at this time:");
					found.printMeeting();
				} else {
					System.out.println("No meeting has been found at this time");
				}
				
			}
			else if (choice.equals("3")) // If the user enters 3 as their choice, an employee wants to manage their diary
			{
				printDiary(theEmployee);
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice,
			{
				Meeting toBeDeleted = searchMeetings(theEmployee);
				if (toBeDeleted != null) {
					theEmployee.deleteFromDiary(toBeDeleted);
				} else {
					System.out.println("No meeting has been found at this time and so cannot be deleted");
				}
			}
			else if (choice.equals("5")) { // If user enters 5 as their choice, they want to edit a meeting
				Meeting toBeEdited = searchMeetings(theEmployee);
				if (toBeEdited != null) {
					toBeEdited.printMeeting();
					System.out.println("Please enter the type of change of you want to make:");
					System.out.println("1 - Change start time");
					System.out.println("2 - Change end time");
					System.out.println("3 - Change description");
					int type = s1.nextInt();
					if (type == 1 || type == 2) {
						System.out.println("Please enter the new date & time for the meeting (HH:MM DD/MM/YYYY):");		
					} else if (type == 3) {
						System.out.println("Please enter the new description for the meeting:");
					}
					String change = s1.nextLine();
					theEmployee.editDiary(type, change, toBeEdited.getStartTime());
				} 
			}
			else if (choice.equals("0")) // If the user enters 0 as their choice,
			{
				System.out.println("Goodbye!"); //Display "Goodbye!"
				// The program will return to initial menu
			}
			else // if the user has not entered 1,2,3,4 or 0,
			{
				System.out.println("Invalid option"); // Display an error message
			}
			
		}
		while (! choice.equals("0"));
	}
	
	
	
	private void printDiary(Employee theEmployee) {
		theEmployee.printDiary();		
	}



	private Meeting searchMeetings(Employee theEmployee) {
		System.out.println("Please enter the date & time you want to check for a meeting (HH:MM DD/MM/YYYY):");
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		Calendar criteria = getTimeDateCalendar(input);
		Meeting toBeFound = theEmployee.searchMeeting(criteria);
		
		return toBeFound;
	}

	
	
	private static Calendar getTimeDateCalendar(String input) {
		String[] split = input.split(" ");
		String[] temp_time = split[0].split(":");
		int[] time = new int[2];
		time[0] = Integer.parseInt(temp_time[0]);
		time[1] = Integer.parseInt(temp_time[1]);
		String[] temp_date = split[1].split("/");
		int[] date = new int[3];
		date[0] = Integer.parseInt(temp_date[0]);
		date[1] = Integer.parseInt(temp_date[1]) - 1;
		date[2] = Integer.parseInt(temp_date[2]);
		
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.set(date[2], date[1], date[0], time[0], time[1]);
		return newCalendar;
	}
	
	

	/**
	 * Processes the user's menu choices
	 */
	public void managersView(Company theCompany)
	{
		String choice;

		do // do the following while the user's choice is not 0
		{
			displayManagerMenu(); // display the menu
			/* Receive the option the user has chosen
			 * and put it into the choice field
			 */
			Scanner s1 = new Scanner(System.in);
			System.out.println("\nOption: ");
			choice = s1.nextLine();
		
			if (choice.equals("1")) // if the user enters 1 as their choice, open company's data from file
			{
				System.out.println("Please enter a name for the new employee:");
				String new_name = s1.nextLine();
				theCompany.addEmployee(new_name);
			}
			else if (choice.equals("2")) // If the user enters 2 as their choice, save company's data to file
			{
				System.out.println("Please enter the name of the employee you wish to search for:");
				String criteria = s1.nextLine();
				if (theCompany.searchEmployee(criteria) != null) {
					System.out.println("There is an employee on the system with the name " + criteria);
				} else {
					System.out.println("No such employee exists on the system");
				}
			}
			else if (choice.equals("3")) // If the user enters 3 as their choice, an employee wants to manage their diary
			{
				System.out.println("Please enter the name of the employee you want to remove:");
				String name = s1.nextLine();
				if (theCompany.deleteEmployee(name) != null) {
					System.out.println("Employee " + name + " has been removed from the system");
				} else {
					System.out.println("No such employee exists and so cannot be removed from the system");
				}
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice, find times for group meeting
			{
				findGroupMeeting(theCompany);
			}
			else if (choice.equals("5")) { // If user enters 5 as their choice, manage an employee
				System.out.println("Please enter the name of the employee you wish to manage:");
				String criteria = s1.nextLine();
				Employee manageEmployee = theCompany.searchEmployee(criteria);
				if (manageEmployee != null) {
					manageEmployee(manageEmployee);
				} else {
					System.out.println("This employee does not exist in the system");
				}
			}
			else if (choice.equals("0")) // If the user enters 0 as their choice,
			{
				System.out.println("Goodbye!"); //Display "Goodbye!"
				// The program will end if this method is chosen
			}
			else // if the user has not entered 1,2,3,4 or 0,
			{
				System.out.println("Invalid option. Please enter 1,2,3,4, or 0"); // Display an error message
			}
			
		}
		while (! choice.equals("0"));
	}
	
	
	
	private void manageEmployee(Employee manageEmployee) {
		System.out.println("Managing employee: " + manageEmployee.getName());
		System.out.println("Please choose an option:");
		System.out.println("1 - Change their name");
		System.out.println("2 - Enter their employee view");
		Scanner scanInput = new Scanner(System.in);
		int choice = scanInput.nextInt();
		switch (choice) {
		case 1:
				System.out.println("Please enter their new name:");
				String name = scanInput.nextLine();
				manageEmployee.setName(name);
				break;
		case 2:
				System.out.println("Now entering employee view...");
				employeesView(manageEmployee);
				break;
		}
	}



	public static void ReadFile(Company thisCompany)
	{
		
		System.out.println("Please enter the name of the file you want to open the company's details from: ");
		Scanner scanFilepath = new Scanner(System.in);
		String filePath = scanFilepath.nextLine() + ".txt";
		thisCompany.openFile(filePath);
		
	}
	
	
	
	public static void SaveFile(Company thisCompany) 
	{ 
		
		System.out.println("Please enter the name of the file you want to save the company's details to: ");
		Scanner scanFilepath = new Scanner(System.in);
		String filePath = scanFilepath.nextLine() + ".txt";
		thisCompany.saveFile(filePath);
		
	}
	
	
	
	public static void addEmployee(Company thisCompany) {
		System.out.println("Please enter the name of the new employee: ");
		Scanner scanName = new Scanner(System.in);
		String name = scanName.nextLine();
		thisCompany.addEmployee(name);
	}
	
	
	
	public static void removeEmployee(Company thisCompany) {
		System.out.println("Please enter the name of the employee you've fired:");
		Scanner scanName = new Scanner(System.in);
		String name = scanName.nextLine();
		thisCompany.deleteEmployee(name);
	}
	
	
	
	public static void findGroupMeeting(Company thisCompany) {
		System.out.println("Enter the names of the employees you want to organise a group meeting for (separated by commas):");
		Scanner scanNames = new Scanner(System.in);
		String allNames = scanNames.nextLine();
		String[] indivNames = allNames.split(",");
		System.out.println("Please enter a date range for the possible meetings (DD/MM/YYYY - DD/MM/YYYY):");
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		String [] dates = input.split(" - ");
		dates[0] = "00:00 " + dates[0];
		dates[1] = "23:59 " + dates[1];
		Calendar startRange = Calendar.getInstance();
		startRange = getTimeDateCalendar(dates[0]);
		Calendar endRange = Calendar.getInstance();
		endRange = getTimeDateCalendar(dates[1]);
		// ADD INPUT, SPLIT & PARSE TO CREATE TWO CALENDAR OBJECTS TO SEND TO searchDiariesGroupMeeting METHOD
		LinkedList<Meeting> possible_times = thisCompany.searchDiariesGroupMeeting(indivNames, startRange, endRange);
		Iterator<Meeting> iter_free_time = possible_times.iterator();
		System.out.println("\n The employees are all free at these times:");
		while (iter_free_time.hasNext()) {
			iter_free_time.next().printMeeting();
		}
	}
	
	
	
	public static void addMeeting(Employee thisEmployee) {
		System.out.println("Please enter the date the meeting will take place on (DD/MM/YYYY):");
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		String[] temp_date = input.split("/");
		int[] date = new int[3];
		for (int i = 0; i < date.length; i++) {
			date[i] = Integer.parseInt(temp_date[i]);
		}
		date[1] = date[1] - 1;
		
		System.out.println("Please enter the meetings start time (HH:MM):");
		input = scanInput.nextLine();
		String[] temp_startTime = input.split(":");
		int[] startTime = new int[2];
		for (int i = 0; i < startTime.length; i++) {
			startTime[i] = Integer.parseInt(temp_startTime[i]);
		}
		
		System.out.println("Please enter the meetings end time (HH:MM):");
		input = scanInput.nextLine();
		String[] temp_endTime = input.split(":");
		int[] endTime = new int[2];
		for (int i = 0; i < endTime.length; i++) {
			endTime[i] = Integer.parseInt(temp_endTime[i]);
		}
		
		System.out.println("Please enter a description for the meeting:");
		String description = scanInput.nextLine();
		
		thisEmployee.addToDiary(date[2], date[1], date[0], startTime[0], startTime[1], endTime[0], endTime[1], description);
	}
	
	
	
}
