import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;

/**
 * 
 * Employee class.
 * @author Patrick Turton-Smith for Group 8 (Caoilainn McCrory, Joel Sieber, Lucas Cerha & Patrick Turton-Smith)
 * @version 1.0
 */
public class Employee {
	
	
	
	/** Private field String name of employee */ private String name;
	/** Private field Diary diary of meetings */ private Diary diary;
	
	
	
	/**
	 * Default constructor for objects of Diary class
	 * @param new_name String name of new employee
	 */
	public Employee(String new_name) {
		this.name = new_name;
		this.diary = new Diary();
	}
	
	
	
	/**
	 * Getter for employee's name
	 * @return name String name of employee
	 */
	public String getName() {
		return name;
	}
	
	
	
	/**
	 * Setter for employee's name
	 * @param name String new name of employee
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	/**
	 * Getter for employee's diary
	 * @return diary Diary employee's diary
	 */
	public Diary getDiary() {
		return diary;
	}
	
	
	
	/**
	 * Setter for employee's diary
	 * @param diary Diary employee's diary
	 */
	public void setDiary(Diary diary) {
		this.diary = diary;
	}
	
	
	
	public void addToDiary(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute, String description) {
		this.diary.createMeeting(year, month, date, startHour, startMinute, endHour, endMinute, description);
	}
	
	
	
	public void addToDiary(String start, String end, String description) {
		long startLong = Long.parseLong(start);
		Calendar startTime = Calendar.getInstance();
		startTime.setTimeInMillis(startLong);
		long endLong = Long.parseLong(end);
		Calendar endTime = Calendar.getInstance();
		endTime.setTimeInMillis(endLong); 
		this.diary.createMeeting(startTime, endTime, description);
	}
	
	
	
	public void printDiary() {
		Iterator<Meeting> iter_meeting = this.diary.getDiary().values().iterator();
		Meeting current_meeting;
		while (iter_meeting.hasNext()) {
			current_meeting = iter_meeting.next();
			current_meeting.printMeeting();
		}
	}
	
	
	
	public void saveEmployee(FileOutputStream outputStream, PrintWriter printWriter) {
		printWriter.print(this.name + ";");
		this.diary.saveMeeting(outputStream, printWriter);
	}
	
	
	
	public void removeFromDiary(Meeting toBeRemoved) {
		this.diary.removeMeeting(toBeRemoved);
	}
	
	
	
}
