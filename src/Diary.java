import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;

/**
 * @author Caoilainn McCrory, Patrick Turton-Smith, Joel Sieber, Lucas Cerha
 *
 */
public class Diary {
	
	TreeMap <Calendar, Meeting> diary;
	
	/**
	 * Default constructor. Initialises fields to default values
	 */
	public Diary() {
		this.diary = new TreeMap <Calendar, Meeting>();
	}
	
	/**
	 * Method that passes through date, start/end time, and description into createMeeting method
	 * @param year Year of meeting
	 * @param month Month of meeting
	 * @param day Day of meeting
	 * @param startHour Start hour of meeting
	 * @param startMinute Start minute of meeting
	 * @param endHour End hour of meeting
	 * @param endMinute End minute of meeting
	 * @param description Description of meeting
	 */
	public void createMeeting(int year, int month, int day, int startHour, int startMinute, int endHour, int endMinute, String description) {
		Calendar startTime = Calendar.getInstance();
		startTime.set(year, month, day, startHour, startMinute);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month, day, endHour, endMinute);
		
		createMeeting(startTime, endTime, description);
	}
	
	/**
	 * Method that creates meeting
	 * @param startTime Start time of meeting
	 * @param endTime End time of meeting
	 * @param description Description of meeting
	 */
	public Meeting createMeeting(Calendar startTime, Calendar endTime, String description) {
		Meeting meetingToAdd = new Meeting(startTime, endTime, description);
		diary.put(startTime, meetingToAdd);
		System.out.println("The following meeting has been created and added: ");
		meetingToAdd.printMeeting();
		return meetingToAdd;
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
	
	public void addMeeting(Meeting meetingToAdd) {
		this.diary.put(meetingToAdd.getStartTime(), meetingToAdd);
	}
	
	public Meeting deleteMeeting(Calendar criteria) {
		return diary.remove(criteria);
	}
	
	/**
	 * Method that saves meeting
	 * @param outputStream Output stream for writing to file
	 * @param printWriter PrintWriter to print meeting info to file
	 */
	public void saveMeeting(FileOutputStream outputStream, PrintWriter printWriter) {
        Iterator<Meeting> iter_meeting = this.diary.values().iterator();
        Meeting currentMeeting;
        
        while(iter_meeting.hasNext()) {
	    		currentMeeting = iter_meeting.next();
	    		long startTime = currentMeeting.getStartTime().getTimeInMillis();
	    		long endTime = currentMeeting.getEndTime().getTimeInMillis();
	    		printWriter.println(currentMeeting.getDescription() + "|" + startTime + "|" + endTime);
        }
	}
	
	/**
	 * @return the diary
	 */
	public TreeMap<Calendar, Meeting> getDiary() {
		return diary;
	}

	/**
	 * @param diary the diary to set
	 */
	public void setDiary(TreeMap<Calendar, Meeting> diary) {
		this.diary = diary;
	}

}
