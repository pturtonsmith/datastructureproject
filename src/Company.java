import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Company class. Contains methods to maintain the Company, such as adding, removing and searching for employees. Also contains method to search selected employees diaries to find an appropriate time for a groupaa meeting.
 * @author Patrick Turton-Smith for Group 8 (Caoilainn McCrory, Joel Sieber, Lucas Cerha & Patrick Turton-Smith)
 * @version 1.0
 */

public class Company {

	/** Private field treemap of employees in company */ private TreeMap<String, Employee> employees;

	/**
	 * Default constructor for object of Company class. Creates a blank tree sorted by the string name of employee.
	 */
	public Company() {

		this.employees = new TreeMap<String, Employee>();
	
	}

	/**
	 * Method to add employee to company's tree
	 * @param toBeAdded new Employee to be added to tree
	 */
	public void addEmployee(String new_name) {
		
		if (this.employees.containsKey(new_name)) { // if an employee with the same name is found,
			System.out.println("There is already an employees with this name, and cannot be added to the company."); // let the user know the employee cannot be added
			System.out.println("You'll just have to sack them."); // this bit's a joke 
		} 
		else { // otherwise, add the employee to the company
			this.employees.put(new_name, new Employee(new_name));
		}
		
	}

	/**
	 * Method to remove employee from company's tree
	 * @param criteria name of employee to remove
	 * @return details of employee to be deleted (null if no such employee exists)
	 */
	public Employee deleteEmployee(String criteria) {

		Employee toBeDeleted = this.employees.remove(criteria); // find and remove the chosen employee from the company
		return toBeDeleted; // return the employee that has been removed (null will be returned if no such employee exists)
		
	}

	/**
	 * Method for searching tree for an employees details
	 * @param criteria name of employee to find
	 * @return details of requested employee (null if no such employee exists)
	 */
	public Employee searchEmployee(String criteria) {
		
		Employee found = this.employees.get(criteria); // search for the employee
		return found; // return the details of the requested employee (this will be null if no such employee exists)
		
	}

	/**
	 * Method for searching through multiple employees diaries to find a suitable time for a group meeting.
	 * @param criteria the names of all the employees to plan for
	 * @return the linked list of times that all the employees are free
	 */
	public LinkedList<Meeting> searchDiariesGroupMeeting(String[] criteria, Calendar startRange, Calendar endRange) {
		TreeMap<Calendar, Meeting> all_meetings = new TreeMap<Calendar, Meeting>();
		Date startTime = new Date();
		
		for (int num_employees = 0; num_employees < criteria.length; num_employees++) {
			Employee current_employee = this.employees.get(criteria[num_employees]);
			Iterator<Meeting> iter_meetings = current_employee.getDiary().getDiary().values().iterator();
			while (iter_meetings.hasNext()) {
				Meeting current_meeting = iter_meetings.next();
				if (current_meeting.getStartTime().compareTo(startRange) > 0 && current_meeting.getStartTime().compareTo(endRange) < 0) {
					all_meetings.put(current_meeting.getStartTime(), current_meeting);
				}
			}
		}
		
		all_meetings = mergeMeetings(all_meetings);
		LinkedList<Meeting> free_time = findFreeTime(all_meetings);
		Date endTime = new Date();
		long diffInMillies = endTime.getTime() - startTime.getTime();
		long second = (diffInMillies / 1000) % 60;
		long minute = (diffInMillies / (1000 * 60)) % 60;
		long hour = (diffInMillies / (1000 * 60 * 60)) % 24;
		String searchDuration = String.format("%02d:%02d:%02d", hour, minute, second);
		System.out.println("Time taken to search for possible meetings: " + searchDuration);
		
		return free_time;
		
	}
		
	/**
	 * Method called from searchDiaries method to merge meetings where they either overlap or are next to each other in order to make determining free time easier.
	 * @param list_meetings LinkedList containing the times of all the meetings found so far
	 * @return list_meeting LinkedList the same list but condensed where possible
	 */
	private TreeMap<Calendar, Meeting> mergeMeetings(TreeMap<Calendar, Meeting> list_meetings) {
		
		Iterator<Meeting> iter_meetings = list_meetings.values().iterator();
		Meeting current = iter_meetings.next();
		Meeting next;
		
		while (iter_meetings.hasNext()) {
			next = iter_meetings.next(); 
			
			if (current.getEndTime().compareTo(next.getStartTime()) >= 0) {
				if (current.getEndTime().compareTo(next.getEndTime()) < 0) {
					current.setEndTime(next.getEndTime());
				}
				iter_meetings.remove();
			} else {
				current = next;
			}
		
		}
		
		return list_meetings;
		
	}

	public Iterator<Employee> iterator() {
		return this.employees.values().iterator();
	}

	/**
	 * Method to find the free time for all employees by reversing the list of the times at which they are all busy.
	 * @param all_meetings LinkedList containing the condensed list of times when all the employees are busy
	 * @return list_free_time LinkedList containing all of the times when all the employees are free
	 */
	private LinkedList<Meeting> findFreeTime(TreeMap<Calendar, Meeting> all_meetings) {
		LinkedList<Meeting> list_free_time = new LinkedList<Meeting>();
		Iterator<Meeting> iter_meetings = all_meetings.values().iterator();
		//Meeting current = iter_meetings.next();
		//Meeting previous = current;
		Meeting current = iter_meetings.next();
		Meeting previous;
		Meeting new_free_time;
		Calendar current_sevenAM = Calendar.getInstance();
		Calendar current_startTime = current.getStartTime();
		Calendar previous_endTime;
		current_sevenAM.set(current_startTime.get(Calendar.YEAR), current_startTime.get(Calendar.MONTH), current_startTime.get(Calendar.DATE), 7, 0, 0);
		System.out.println(current_sevenAM.getTime());
		Calendar previous_sevenPM = Calendar.getInstance();
		if (current_startTime.compareTo(current_sevenAM) > 0) {
			new_free_time = new Meeting(current_sevenAM, current_startTime, "");
			list_free_time.add(new_free_time);
		}
		previous = current;
		while (iter_meetings.hasNext()) {
			current = iter_meetings.next();
			previous_endTime = previous.getEndTime();
			current_startTime = current.getStartTime();
			
			if (checkSameDay(previous, current)) {
				new_free_time = new Meeting(previous_endTime, current_startTime, "");
				list_free_time.add(new_free_time);
			} else {
				previous_sevenPM.set(previous_endTime.get(Calendar.YEAR), previous_endTime.get(Calendar.MONTH), previous_endTime.get(Calendar.DATE), 19, 0, 0);
				current_sevenAM.set(current_startTime.get(Calendar.YEAR), current_startTime.get(Calendar.MONTH), current_startTime.get(Calendar.DATE), 7, 0, 0);
			
				if (previous_endTime.compareTo(previous_sevenPM) < 0) {
					new_free_time = new Meeting(previous_endTime, previous_sevenPM, "");
					list_free_time.add(new_free_time);
				}
			
				if (current_startTime.compareTo(current_sevenAM) > 0) {
					new_free_time = new Meeting(current_sevenAM, current_startTime, "");
					list_free_time.add(new_free_time);
				}
			
			}
			
			previous = current;
			
		}
		
		Calendar current_sevenPM = Calendar.getInstance(); 
		current_sevenPM.set(current_startTime.get(Calendar.YEAR), current_startTime.get(Calendar.MONTH), current_startTime.get(Calendar.DATE), 19, 0, 0);
		
		if (current.getEndTime().compareTo(current_sevenPM) < 0) {
			new_free_time = new Meeting(current.getEndTime(), current_sevenPM, "");
			list_free_time.add(new_free_time);
		}
		
		return list_free_time;
		
	}
	
	
	/**
	 * Method to check whether two meetings are on the same day or not.
	 * @param previous Meeting previous meeting in list of meetings
	 * @param current Meeting current meeting in list of meetings
	 * @return boolean whether meetings are on the same day or not
	 */
	private Boolean checkSameDay(Meeting previous, Meeting current) {
		
		int previous_date = previous.getStartTime().get(Calendar.DATE);
		int current_date = current.getStartTime().get(Calendar.DATE);
		
		if (previous_date != current_date) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void addGroupMeeting(String[] names, Meeting groupMeeting) {
		for (int num_employees = 0; num_employees < names.length; num_employees++) {
			Employee current_employee = this.employees.get(names[num_employees]);
			current_employee.getDiary().addMeeting(groupMeeting);
		}
	}

	/**
	 * Method to save all the data about every meeting and every employee in the company to a file. 
	 */
	public void saveFile(String input) {
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		try {
			outputStream = new FileOutputStream(input);
			printWriter = new PrintWriter(outputStream);
			
			Iterator<Employee> iter_employees = this.iterator();
			Employee current;
			
			while(iter_employees.hasNext()) {
				current = iter_employees.next();
				current.saveEmployee(outputStream, printWriter);
				printWriter.println();
			}
		
		} catch (FileNotFoundException e) {
			
			System.out.println("Your file could not be found.");
			
		} catch (IOException e) {
			
			System.out.println("There was an error saving to this file.");
			
		} finally {
			
			if (printWriter != null) {
				printWriter.close();
			}
			
		}
		
	}

	/**
	 * Method to read in all the company's data from a file
	 */
	public void openFile(String input) {
		FileReader filePath = null;
		BufferedReader bufferedReader = null;
		
		try {
			
			filePath = new FileReader(input);
			bufferedReader = new BufferedReader(filePath);
			
			String nextLine = bufferedReader.readLine();
			
			while (nextLine != null) {
				String split[] = nextLine.split(";");
				Employee new_employee = new Employee(split[0]);
				String[] all_meetings = split[1].split(":");
				
				for (int i = 0; i < all_meetings.length; i++) {
					String[] current_meeting = all_meetings[i].split("\\~");
					new_employee.addToDiary(current_meeting[1], current_meeting[2], current_meeting[0]);
				}
				
				this.employees.put(split[0], new_employee);
				nextLine = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Your file could not be found.");
		} catch (IOException e) {
			System.out.println("There was an error opening your file.");
		} finally {
			if (bufferedReader != null) { // if the printWriter is not empty then
				try {
					bufferedReader.close(); // close it
				} catch (IOException e) {
					System.out.println("There was an error"); // if there's an IOException error then let them know
				}
			}
		}
	}
} 
