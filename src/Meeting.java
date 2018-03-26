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
		this.description = "";
	}
	
	
	
	public Meeting(Calendar startTime, Calendar endTime, String description) {
		setStartTime(startTime);
		setEndTime(endTime);
		setDescription(description);
	}
	
	
	
	public void setDateTime(int year, int month, int date, int startHour, int startMinute, int endHour, int endMinute) {
		startTime.set(year, month, date, startHour, startMinute, 0);
		endTime.set(year, month, date, endHour, endMinute, 0);
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
	
	
	
	public void printMeeting() {
		System.out.println("Start Time: " + this.startTime.getTime());
		System.out.println("End Time: " + this.endTime.getTime());
		System.out.println("Description: " + this.description);
	}
	
	
	
	public void editMeeting(int type, String change) {
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
		
	}
	
	
}
