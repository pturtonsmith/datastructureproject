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
 * Menu class. Contains methods to create and display the menu to the user, process their menu choices, print their diary, search meetings, and read and save the file.
 * @author Caoilainn McCrory, Patrick Turton-Smith, Joel Sieber, Lucas Cerha
 *
 */
public class Menu {
	
	
	public Company company;
	
	
	/**
	 * creates a new company object
	 */
	public Menu() {
		this.company = new Company();
	}
	
	
	/**
	 * 
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	
	
	/**
	 * 
	 * @param company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu myMenu = new Menu();
		
		myMenu.processUserChoices(); // run the menu
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
		System.out.println("0. Exit");
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
				employeesView(theCompany.searchEmployee(name));
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice,
			{
				// does other stuff
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
	
	
	/**
	 * print the employee's diary
	 * @param theEmployee
	 */
	private void printDiary(Employee theEmployee) {
		theEmployee.printDiary();		
	}


	/**
	 * search for meetings
	 * @param theEmployee
	 * @return the employee's search criteria
	 */
	private Meeting searchMeetings(Employee theEmployee) {
		System.out.println("Please enter the date & time you want to check for a meeting (HH:MM DD/MM/YYYY):");
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		Calendar criteria = getTimeDateCalendar(input);
		Meeting toBeFound = theEmployee.searchMeeting(criteria);
		
		return toBeFound;
	}

	
	/**
	 * gets the date and time calendar
	 * @param input
	 * @return the new calendar
	 */
	private Calendar getTimeDateCalendar(String input) {
		String[] split = input.split(" ");
		String[] temp_time = split[0].split(":");
		int[] time = new int[2];
		time[0] = Integer.parseInt(temp_time[0]);
		time[1] = Integer.parseInt(temp_time[1]);
		String[] temp_date = split[1].split("/");
		int[] date = new int[3];
		date[0] = Integer.parseInt(temp_date[0]);
		date[1] = Integer.parseInt(temp_date[1]);
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
			displayEmployeeMenu(); // display the menu
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
				manageEmployee(theCompany.searchEmployee(name));
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice,
			{
				// does other stuff
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
	
	
	
	private void manageEmployee(Employee searchEmployee) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Read in the file
	 * @param thisCompany
	 */
	public static void ReadFile(Company thisCompany)
	{
		// receive the file name from the user and read in the file
		System.out.println("Please enter the name of the file you want to open the company's details from: ");
		Scanner scanFilepath = new Scanner(System.in);
		String filePath = scanFilepath.nextLine() + ".txt";
		thisCompany.openFile(filePath);
		
	}
	
	
	/**
	 * Save the file
	 * @param thisCompany
	 */
	public static void SaveFile(Company thisCompany) 
	{ 
		// receive the file name from the user and save to a text document
		System.out.println("Please enter the name of the file you want to save the company's details to: ");
		Scanner scanFilepath = new Scanner(System.in);
		String filePath = scanFilepath.nextLine() + ".txt";
		thisCompany.saveFile(filePath);
		
	}
	
	
	/**
	 * Adds an employee to the company
	 * @param thisCompany
	 */
	public static void addEmployee(Company thisCompany) {
		// receives the employee's name from the user and adds him to the database
		System.out.println("Please enter the name of the new employee: ");
		Scanner scanName = new Scanner(System.in);
		String name = scanName.nextLine();
		thisCompany.addEmployee(name);
	}
	
	
	/**
	 * Remove an employee from the company
	 * @param thisCompany
	 */
	public static void removeEmployee(Company thisCompany) {
		// receive the employee's name from the user and removes him from the database
		System.out.println("Please enter the name of the employee you've fired:");
		Scanner scanName = new Scanner(System.in);
		String name = scanName.nextLine();
		thisCompany.deleteEmployee(name);
	}
	
	
	/**
	 * find a suitable group meeting time
	 * @param thisCompany
	 */
	public static void findGroupMeeting(Company thisCompany) {
		// receive the names of the employees that are organising a meeting along with a date range for possible meetings
		// and find a suitable meeting time and date for them.
		System.out.println("Enter the names of the employees you want to organise a group meeting for (separated by commas):");
		Scanner scanNames = new Scanner(System.in);
		String allNames = scanNames.nextLine();
		String[] indivNames = allNames.split(",");
		System.out.println("Please enter a date range for the possible meetings (DD/MM/YYYY - DD/MM/YYYY):");
		
		// ADD INPUT, SPLIT & PARSE TO CREATE TWO CALENDAR OBJECTS TO SEND TO searchDiariesGroupMeeting METHOD
		LinkedList<Meeting> possible_times = thisCompany.searchDiariesGroupMeeting(indivNames, startRange, endRange);
		Iterator<Meeting> iter_free_time = possible_times.iterator();
		System.out.println("\n The employees are all free at these times:");
		while (iter_free_time.hasNext()) {
			iter_free_time.next().printMeeting();
		}
	}
	
	
	/**
	 * add a meeting
	 * @param thisEmployee
	 */
	public static void addMeeting(Employee thisEmployee) {
		System.out.println("Please enter the date the meeting will take place on (DD/MM/YYYY):"); // receive the meeting's date from the user
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		String[] temp_date = input.split("/"); // split the date by forward slashes into a new 1D int array
		int[] date = new int[3];
		for (int i = 0; i < date.length; i++) { // parse the date array
			date[i] = Integer.parseInt(temp_date[i]);
		}
		
		System.out.println("Please enter the meetings start time (HH:MM):"); // receive the start time from the user
		input = scanInput.nextLine();
		String[] temp_startTime = input.split(":"); // split the time by colons into a new 1D int array
		int[] startTime = new int[2];
		for (int i = 0; i < startTime.length; i++) { // parse the startTime array
			startTime[i] = Integer.parseInt(temp_startTime[i]);
		}
		
		System.out.println("Please enter the meetings end time (HH:MM):"); // receive the end time from the user
		input = scanInput.nextLine();
		String[] temp_endTime = input.split(":"); // split the time by colons into a new 1D int array
		int[] endTime = new int[2];
		for (int i = 0; i < startTime.length; i++) { // parse the endTime array
			startTime[i] = Integer.parseInt(temp_startTime[i]);
		}
		
		System.out.println("Please enter a description for the meeting:"); // receive a meeting description from the user
		String description = scanInput.nextLine();
		
		thisEmployee.addToDiary(date[2], date[1], date[0], startTime[0], startTime[1], endTime[0], endTime[1], description); // add all of the meeting information to the diary
	}
	
	
	
}
