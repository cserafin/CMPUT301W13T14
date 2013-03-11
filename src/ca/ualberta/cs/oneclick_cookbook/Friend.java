/**
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * @version	1.0
 */


package ca.ualberta.cs.oneclick_cookbook;

public class Friend {
	private String screenName = null;
	private String email = null;
	private String phoneNumber = null;
	
	/**
	 * The Class constructor.
	 * 
	 * @param screenName The name that will be displayed on the screen
	 * @param email The email for the user
	 * @param phoneNumber The phone Number for the user
	 */
	public Friend (String screenName, String email, String phoneNumber) {
		this.email = email;
		this.screenName = screenName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Turns the user's info into a string
	 */
	public String toString() {
		return email + " " + screenName + " " + phoneNumber;
	}
	
}
