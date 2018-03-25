import java.util.TreeMap;
import java.util.Calendar;
import java.util.Iterator;

/**
 * @author caoilainnmccrory, patrickturton-smith, joelsieber, lucascerha
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
	 * Method to create a meeting
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
		Meeting meetingToAdd = new Meeting(startTime, endTime, description);
		diary.put(startTime, meetingToAdd);
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
