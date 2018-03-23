import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Company class. Contains methods to maintain the Company, such as adding, removing and searching for employees. Also contains method to search selected employees diaries to find an appropriate time for a groupaa meeting.
 * @author Patrick Turton-Smith for Group 8 (Caoilainn McCrory, Joel Sieber, Lucas Cerha & Patrick Turton-Smith)
 * @version 1.0
 */

public class Company {
	
	
	private TreeMap<String, Employee> employees = new TreeMap<String, Employee>();
	
	
	/**
	 * Default constructor for object of Company class. Creates a blank tree sorted by the string name of employee.
	 */
	public Company() {
		employees = new TreeMap<String, Employee>();
	}
	
	
	public void addEmployee(Employee toBeAdded) {
		if (employees.containsKey(toBeAdded.getName())) {
			System.out.println("There is already an employees with this name, and cannot be added to the company.");
			System.out.println("You'll just have to sack them.");
		} else {
			employees.put(toBeAdded.getName(), toBeAdded);
		}	
	}
	
	
	public Employee deleteEmployee(String criteria) {
		Employee toBeDeleted = employees.remove(criteria);
		return toBeDeleted;
	}
	
	
	public Employee searchEmployee(String criteria) {
		Employee found = employees.get(criteria);
		return found;
	}
	
	
	public void searchDiaries(String[] criteria) {
		Set<Date> time_free_all = new HashSet<Date>();
		for (int num_employees = 0; num_employees < criteria.length; num_employees++) {
			Employee current_employee = employees.get(criteria);
			TreeMap<Calendar, Meeting> current_meetings = current_employee.getDiary().getTimesDay();
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
			System.out.print(iter_free_time.next().toGMTString() + ", ");
		}
		
		/**
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
		**/
	}
	
	
	public void iterator() {
		return employees.iterator();
	}
	
	
	private Set<Integer> setTimesDay() {
		Set<Integer> times_day = new HashSet<Integer>();
		for (int i = 8; i < 24; i++) {
			times_day.add(i);
		}
		return times_day;
	}
	
	
}
