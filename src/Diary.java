import java.util.TreeSet;

/**
 * @author caoilainnmccrory
 *
 */
public class Diary {

	private TreeSet[] daysOfWeek;
	private Meeting meeting;
	
	public Diary() {
		daysOfWeek = new TreeSet[5];
		meeting = new Meeting();
	}
	
	//need date? should startTime,endTime be ints?
	public void createMeeting(int startTime, int endTime) {
//		Meeting newMeeting = new Meeting(startTime, endTime);
			//should have method to create a meeting in meeting class?
	}
	
	//check if meeting exists? need date?
	public void getMeeting(int startTime, int endTime) {
		
	}
	
	//need day? should return type be boolean?
	public boolean checkBusy(int startTime, int endTime) {
		
	}
}
