import java.util.TreeMap;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;


/**
 * @author caoilainnmccrory
 *
 */


public class Diary {

	
	
	/** Private field treemap of meetings in employees diary */ private TreeMap <Calendar, Meeting> diary;
	
	
	
	/**
	 * Default constructor for Diary objects
	 */
	public Diary() {
		this.diary = new TreeMap <Calendar, Meeting>();
	}
	
	
	
	/**
	 * Getter for tree of meetings
	 * @return diary TreeMap of meetings
	 */
	public TreeMap<Calendar, Meeting> getDiary() {
		return diary;
	}



	/**
	 * Setter for tree of meetings
	 * @param diary TreeMap of meetings
	 */
	public void setDiary(TreeMap<Calendar, Meeting> diary) {
		this.diary = diary;
	}



	/**
	 * Method for creating a new meeting for an employee. Receives raw date/time information from calling method and uses it to create a new meeting object and add to the employee's tree of meetings (diary). 
	 * @param year int year
	 * @param month int month
	 * @param date int date of month
	 * @param startHour int hour of day meeting begins at
	 * @param startMinute int minute of hour meeting begins at
	 * @param endHour int hour of day meeting ends at
	 * @param endMinute int minute of hour meeting ends at
	 * @param description String description of meeting
	 */
	public void createMeeting(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute, String description) {
		Calendar startTime = Calendar.getInstance();
		startTime.set(year, month, date, startHour, startMinute, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month, date, endHour, endMinute, 0);
		Meeting meetingToAdd = new Meeting(startTime, endTime, description);
		this.diary.put(startTime, meetingToAdd);
	}
	
	
	
	/**
	 * Iterates through tree to see if employee has a meeting at time (criteria). Returns details of the meeting if there is one at that time, or null if there is not.
	 * @param criteria Calendar date/time the employee wants to check a meeting for
	 * @return details of the meeting if there is one, or null if there isn't
	 */
	public Meeting getMeeting(Calendar criteria) {
		Iterator<Meeting> iter_meetings = this.getDiary().values().iterator();
		Meeting found = null;
		
		while (iter_meetings.hasNext() && found != null) {
			Meeting current = iter_meetings.next();
			if (current.getStartTime().compareTo(criteria) >= 0 && current.getEndTime().compareTo(criteria) < 0) {
				found = current;
			} else {
				found = null;
			}
		}
		
		return found;
	}



	public void createMeeting(Calendar startTime, Calendar endTime, String description) {
		Meeting meetingToAdd = new Meeting(startTime, endTime, description);
		this.diary.put(startTime, meetingToAdd);
	}
	
	
	
	public void saveMeeting(FileOutputStream outputStream, PrintWriter printWriter) {
        Iterator<Meeting> iter_meeting = this.diary.values().iterator();
        Meeting currentMeeting;
        
        while(iter_meeting.hasNext()) {
	    		currentMeeting = iter_meeting.next();
	    		long startTime = currentMeeting.getStartTime().getTimeInMillis();
	    		long endTime = currentMeeting.getEndTime().getTimeInMillis();
	    		printWriter.print(startTime + "~" + endTime + "~" + currentMeeting.getDescription() + ":");
        }
	}
	
	
	
}
