import java.util.TreeMap;
import java.util.Calendar;

/**
 * @author caoilainnmccrory
 *
 */
public class Diary {
	
	TreeMap <Calendar, Meeting> diary = new TreeMap <Calendar, Meeting>();
	
	public Diary() {
		
	}
	
	public void createMeeting(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute, String description) {
		Calendar startTime = Calendar.getInstance();
		startTime.set(year, month, date, startHour, startMinute);
		Calendar endTime = Calendar.getInstance();
		endTime.set(year, month, date, endHour, endMinute);
		Meeting meetingToAdd = new Meeting(startTime, endTime, description);
		diary.put(startTime, meetingToAdd);
	}
	
	//check if meeting exists? need date?
	public void getMeeting(int startTime, int endTime, int day) {
		
	}
	
	//should return type be boolean?
//	public boolean checkBusy(int startTime, int endTime, int day) {
//		
//	}
}
