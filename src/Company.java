import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
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
		this.employees.addEmployees("Manager"); // Add owner to manage company
	
	}
	
	
	
	/**
	 * Method to add employee to company's tree
	 * @param toBeAdded new Employee to be added to tree
	 */
	public void addEmployee(Employee toBeAdded) {
		if (employees.containsKey(toBeAdded.getName())) {
			System.out.println("There is already an employees with this name, and cannot be added to the company.");
			System.out.println("You'll just have to sack them.");
		} else {
			employees.put(toBeAdded.getName(), toBeAdded);
		}
	}
	
	
	
	/**
	 * Method to remove employee from company's tree
	 * @param criteria name of employee to remove
	 * @return details of employee to be deleted (null if no such employee exists)
	 */
	public Employee deleteEmployee(String criteria) {	
		Employee toBeDeleted = employees.remove(criteria);
		return toBeDeleted;
	}
	
	
	
	/**
	 * Method for searching tree for an employees details
	 * @param criteria name of employee to find
	 * @return details of requested employee (null if no such employee exists)
	 */
	public Employee searchEmployee(String criteria) {
		Employee found = employees.get(criteria);
		return found;
	}
	
	
	
	/**
	 * Method for searching through multiple employees diaries to find a suitable time for a group meeting.
	 * @param criteria the names of all the employees to plan for
	 * @return the linked list of times that all the employees are free
	 */
	public LinkedList<Meeting> searchDiaries(String[] criteria) {
		LinkedList<Meeting> all_meetings = new LinkedList<Meeting>();
		
		for (int num_employees = 0; num_employees < criteria.length; num_employees++) {
			Employee current_employee = employees.get(criteria);
			Iterator<Meeting> iter_meetings = current_employee.getDiary().getMeetings().keyIterator();
			while (iter_meetings.hasNext()) {
				all_meetings.add(iter_meetings.next());
			}
			all_meetings = mergeMeetings(all_meetings);
		}
		
		return all_meetings;
	}	
		/**
		Set<Date> time_free_all = new HashSet<Date>();
		for (int num_employees = 0; num_employees < criteria.length; num_employees++) {
			Employee current_employee = employees.get(criteria);
			TreeMap<Calendar, Meeting> current_meetings = current_employee.getDiary().getTimesDay().keyIterator();
			Set<Calendar> time_free_current = new HashSet<Calendar>();
			Iterator<Calendar> iter_meetings = current_meetings.iterator();
			while (iter_meetings.hasNext()) {
				Meeting current_meeting = iter_meetings.next();
				if (current_meeting.getBusy() == false) {
					Calendar start_time = current_meeting.getStartTime();
					time_free_current.add(start_time);
				}
			}
			if (num_employees == 0) {
				time_free_all = time_free_current;
			} else {
				time_free_all.retainAll(time_free_current);
			}
		}
		Iterator<Date> iter_free_time = time_free_all.iterator();
		System.out.println("The employees are all free at these times:");
		while(iter_free_time.hasNext()) {
			System.out.print(iter_free_time.next().toString() + ", ");
		}
		
		
		for (int i = 0; i < criteria.length; i++) {
			Employee current = employees.get(criteria);
			TreeMap<Date, Meeting>  current_employee = current.getDiary().getMeetings();
			Iterator<Meetings> iterate_meetings = Meetings.iterator();
			while (iterate_meetings.hasNext()) {
				Meetings current = iterate_meetings.next();
				Integer start_time = current.getStartTime();
				Integer end_time = current.getEndTime();
				start_time_taken.add(start_time);
				end_time_taken.add(end_time);
			}
		}
		Iterator<Integer> iterate_start_time = start_time_taken.iterator();
		System.out.println("The employees all have meetings at: ");
		while (iterate_start_time.hasNext()) {
			System.out.print(iterate_start_time.next() + " ");
		}
		Set<Integer> times_day = setTimesDay();
		times_day.removeAll(start_time_taken);
		Iterator<Integer> iterate_times_day = times_day.iterator();
		System.out.println("The employees are all free at: ");
		while (iterate_times_day.hasNext()) {
			System.out.print(iterate_times_day.next() + ":00, ");
		}
		
	}
	**/
	
	
	
	/**
	 * Method called from searchDiaries method to merge meetings where they either overlap or are next to each other in order to make determining free time easier.
	 * @param list_meetings LinkedList containing the times of all the meetings found so far
	 * @return list_meeting LinkedList the same list but condensed where possible
	 */
	private LinkedList<Meeting> mergeMeetings(LinkedList<Meeting> list_meetings) {
		Iterator<Meeting> iter_meetings = list_meetings.iterator();
		Meeting current = iter_meetings.next();
		Meeting next;
		
		while (iter_meetings.hasNext()) {
			next = iter_meetings.next(); 
			
			if (current.getEndTime().compareTo(next.getStartTime()) >= 0) {
				if (current.getEndTime().compareTo(next.getEndTime()) < 0) {
					current.setEndTime(next.getEndTime());
				}
				list_meetings.remove(next);
			} else {
				current = next;
			}
		
		}
		
		return list_meetings;
	}


	public void iterator() {
		return employees.iterator();
	}
	
	
}
