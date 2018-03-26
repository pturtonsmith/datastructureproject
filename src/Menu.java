import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
				searchMeetings(theEmployee);
			}
			else if (choice.equals("3")) // If the user enters 3 as their choice, an employee wants to manage their diary
			{
				printMeetings(theEmployee);
			}
			else if (choice.equals("4")) // If the user enters 4 as their choice,
			{
				Meeting toBeDeleted = searchMeetings(theEmployee);
				theEmployee.getDiary().
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
	public void managersView(Company theCompany)
	{
		String choice;
		Company theCompany = getCompany();
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
		for (int i = 0; i < startTime.length; i++) {
			startTime[i] = Integer.parseInt(temp_startTime[i]);
		}
		
		System.out.println("Please enter a description for the meeting:");
		String description = scanInput.nextLine();
		
		thisEmployee.addToDiary(date[2], date[1], date[0], startTime[0], startTime[1], endTime[0], endTime[1], description);
	}
	
	
	
}
