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
	private Stack<Meeting> newStack;
	private Stack<Integer> newStack2;

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
	
	/**
	 * Method to delete from diary
	 * @param criteria Start time of meeting to be deleted
	 */
	public void deleteFromDiary(Calendar criteria) {
		Meeting meetingDeleted = this.diary.deleteMeeting(criteria);
		newStack2.push(2);
		newStack.push(meetingDeleted);
	}
	
	/**
	 * Method to edit diary
	 * @param type Type of edit being done
	 * @param change User's edit to meeting
	 * @param criteria Start time of meeting to be edited
	 */
	public void editDiary(int type, String change, Calendar criteria) {
		Meeting[] meetingEdited = this.diary.editMeeting(type, change, criteria);
		newStack.push(meetingEdited[0]);
		newStack.push(meetingEdited[1]);
 		newStack2.push(3);
	}
	
	/**
	 * Method to undo an addition to diary
	 */
	public void undoAdd() {
		Meeting meetingToDelete = newStack.pop();
		this.diary.deleteMeeting(meetingToDelete.getStartTime());
	}
	
	/**
	 * Method to undo deletion of diary entry
	 */
	public void undoDelete() {
		Meeting meetingToAdd = newStack.pop();
		this.diary.addMeeting(meetingToAdd);
	}
	
	/**
	 * Method to undo an edit to diary
	 */
	public void undoEdit() {
		Meeting undoEdit = newStack.pop();
		this.diary.deleteMeeting(undoEdit.getStartTime());
		Meeting undoEdit2 = newStack.pop();
		this.diary.addMeeting(undoEdit2);
	}
	
	/**
	 * Method that calls undo add/delete/edit methods
	 */
	public void undo() {
		int stackNumber = newStack2.pop();
		
		switch(stackNumber) {
			case 1:
				undoAdd();
				break;
			case 2:
				undoDelete();
				break;
			case 3:
				undoEdit();
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