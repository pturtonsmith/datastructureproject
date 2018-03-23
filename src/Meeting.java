import java.util.Calendar;

/**
 * @author caoilainnmccrory, joelsieber, patrickturton-smith, lucascerha
 * 
 * @version v1.0
 */

public class Meeting {
	
	private Calendar startTime;
	private Calendar endTime;
	private String description;

	/**
	 * Default constructor. Initialises fields to default values.
	 */
	public Meeting() {
		description = "";
	}
	
	public Meeting(Calendar startTime, Calendar endTime, String description) {
		setStartTime(startTime);
		setEndTime(endTime);
		setDescription(description);
	}
	
	public void setDateTime(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute) {
		startTime.set(year, month, date, startHour, startMinute);
		endTime.set(year, month, date, endHour, endMinute);
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
