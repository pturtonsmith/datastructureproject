import java.util.Date;

/**
 * @author caoilainnmccrory, joelsieber, patrickturton-smith, lucascerha
 * 
 * @version v1.0
 */

public class Meeting {
	
	//should change Date to int? what about date?
	private Date startTime;
	private Date endTime;
	private String description;
	private Date date;

	/**
	 * Default constructor. Initialises fields to default values.
	 */
	public Meeting() {
		description = "";
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
