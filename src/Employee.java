import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * Employee class.
 * @author Patrick Turton-Smith for Group 8 (Caoilainn McCrory, Joel Sieber, Lucas Cerha & Patrick Turton-Smith)
 * @version 1.0
 */

public class Employee {
	
	/** Private field String name of employee */ private String name;
	/** Private field Diary diary of meetings */ private Diary diary;
	Stack<Meeting> newStack;
	Stack<Integer> newStack2;

	/**
	 * Default constructor for objects of Diary class
	 * @param new_name String name of new employee
	 */
	public Employee(String new_name) {
		this.name = new_name;
		this.diary = new Diary();
		newStack = new Stack();
		newStack2 = new Stack();
	}
	
//	/**
//	 * Method to add meeting to diary. Calls createMeeting method in Meeting class
//	 * @param year Year of meeting
//	 * @param month Month of meeting
//	 * @param date Date of meeting
//	 * @param startHour Start hour of meeting
//	 * @param startMinute Start minute of meeting
//	 * @param endHour End hour of meeting
//	 * @param endMinute End minute of meeting
//	 * @param description Description of meeting
//	 */
//	public void addToDiary(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute, String description) {
//		this.diary.createMeeting(year, month, date, startHour, startMinute, endHour, endMinute, description);
//	}

	/**
	 * Method to print diary
	 */
	public void printDiary() {
		Iterator<Meeting> iter_meeting = this.diary.getDiary().values().iterator();
		Meeting current_meeting;
		while (iter_meeting.hasNext()) {
			current_meeting = iter_meeting.next();
			current_meeting.printMeeting();
		}
	}
	
	/**
	 * Method to add meeting to diary
	 * @param start Start time of meeting
	 * @param end End time of meeting
	 * @param description Description of meeting
	 * @return Reference to meeting created
	 */
	public void addToDiary(String start, String end, String description) {
				long startLong = Long.parseLong(start);
				Calendar startTime = Calendar.getInstance();
				startTime.setTimeInMillis(startLong);
				long endLong = Long.parseLong(end);
				Calendar endTime = Calendar.getInstance();
				endTime.setTimeInMillis(endLong); 
				Meeting newMeeting = this.diary.createMeeting(startTime, endTime, description);
				newStack.push(newMeeting);
				newStack2.push(1);
	}
	
	public void deleteFromDiary(Calendar criteria) {
		Meeting meetingDeleted = this.diary.deleteMeeting(criteria);
		newStack2.push(2);
		newStack.push(meetingDeleted);
	}
	
	public void undoAdd() {
		Meeting meetingToDelete = newStack.pop();
		this.diary.deleteMeeting(meetingToDelete.getStartTime());
	}
	
	public void undoDelete() {
		Meeting meetingToAdd = newStack.pop();
		this.diary.addMeeting(meetingToAdd);
	}
	
	public void undo() {
		int stackNumber = newStack2.pop();
		
		switch(stackNumber) {
			case 1:
				undoAdd();
				break;
			case 2:
				undoDelete();
				break;
		}
	}
	
	/**
	 * 
	 * @param outputStream Output stream for writing to file
	 * @param printWriter PrintWriter to print names to file
	 */
	public void saveEmployee(FileOutputStream outputStream, PrintWriter printWriter) {
		printWriter.println(this.name + ";");
		diary.saveMeeting(outputStream, printWriter);
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
}