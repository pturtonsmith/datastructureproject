import java.util.Date;
import java.util.Calendar;

/**
 * @author Caoilainn McCrory, Patrick Turton-Smith, Joel Sieber, Lucas Cerha
 * 
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
		Date startTime = getStartTime().getTime();
		Date endTime = getEndTime().getTime();
		String description = getDescription();
		System.out.print(startTime + "\n" + endTime + "\n" + description);
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
	public void setDateTime(int year, int month, int day, int startHour, int startMinute, int endHour, int endMinute) {
		startTime.set(year, month, day, startHour, startMinute);
		endTime.set(year, month, day, endHour, endMinute);
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
