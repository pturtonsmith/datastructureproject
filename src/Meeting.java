import java.util.Date;
import java.util.Calendar;

/**
 * Meeting class. Print and edit meetings, set the date, time, description, start time and end time.
 * @author Caoilainn McCrory, Patrick Turton-Smith, Joel Sieber, Lucas Cerha
 * @version v1.0
 */

public class Meeting {
	
	private Calendar startTime;
	private Calendar endTime;
	private String description;

	/**
	 * Default constructor. Initialises fields to default values
	 */
	public Meeting() {
		description = "";
	}
	
	/**
	 * Alternative constructor. Sets fields to given values
	 * @param startTime Start time to be set
	 * @param endTime End time to be set 
	 * @param description Description to be set
	 */
	public Meeting(Calendar startTime, Calendar endTime, String description) {
		setStartTime(startTime);
		setEndTime(endTime);
		setDescription(description);
	}
	
	/**
	 * Method to print meeting
	 */
	public void printMeeting() {
		Date startTime = getStartTime().getTime(); // set the start time
		Date endTime = getEndTime().getTime(); // set the end time
		String description = getDescription(); // set the description
		System.out.print(startTime + "\n" + endTime + "\n" + description); // print the start time, end time, and description
	}
	
	/**
	 * Method to edit meeting
	 * @param type Type of edit being done
	 * @param change User's edit to meeting
	 * @return Array of meetings consisting of original and edited versions of meeting
	 */
	public Meeting[] editMeeting(int type, String change) {
		Meeting[] meetings = new Meeting[2];
		meetings[0] = new Meeting(this.startTime, this.endTime, this.description);
		switch (type) {
			case 1:
				String[] temp_startsplit = change.split(" ");
				String[] temp_startdate = temp_startsplit[0].split("/");
				String[] temp_starttime = temp_startsplit[1].split(":");
				int[] startDate = new int[3];
				int[] startTime = new int[2];
				for (int i = 0; i < temp_startdate.length; i++) {
					startDate[i] = Integer.parseInt(temp_startdate[i]);
				}
				for (int i = 0; i < temp_starttime.length; i++) {
					startTime[i] = Integer.parseInt(temp_starttime[i]);
				}
				this.startTime.set(startDate[2], startDate[1], startDate[0], startTime[0], startTime[1]);
				break;
			case 2:
				String[] temp_endsplit = change.split(" ");
				String[] temp_enddate = temp_endsplit[0].split("/");
				String[] temp_endtime = temp_endsplit[1].split(":");
				int[] endDate = new int[3];
				int[] endTime = new int[2];
				for (int i = 0; i < temp_enddate.length; i++) {
					endDate[i] = Integer.parseInt(temp_enddate[i]);
				}
				for (int i = 0; i < temp_endtime.length; i++) {
					endTime[i] = Integer.parseInt(temp_endtime[i]);
				}
				this.startTime.set(endDate[2], endDate[1], endDate[0], endTime[0], endTime[1]);
				break;
			case 3:
				this.description = change;
		}
		meetings[1] = new Meeting(this.startTime, this.endTime, this.description);
		
		return meetings;
	}
	
	/**
	 * Method to set date and time
	 * @param year Year to be set
	 * @param month Month to be set
	 * @param day Day to be set
	 * @param startHour Start hour to be set
	 * @param startMinute Start minute to be set
	 * @param endHour End hour to be set
	 * @param endMinute End minute to be set
	 */
	public Meeting setDateTime(int year, int month, int day, int startHour, int startMinute, int endHour, int endMinute, String description) {
		startTime.set(year, month, day, startHour, startMinute); // set the start time
		endTime.set(year, month, day, endHour, endMinute); // set the end time
		
		Meeting setMeeting = new Meeting(startTime, endTime, description); // create a new meeting object
		return setMeeting;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startTime
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

}
