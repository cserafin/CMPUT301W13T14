/**
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * 
 * Stores the relevant information about one of the user's friend
 */


package ca.ualberta.cs.oneclick_cookbook;

public class Friend {
	private String screenName = null;
	private String email = null;
	private String phoneNumber = null;
	
	/**
	 * The Class constructor.
	 * 
	 * @param screenName The screen name of the friend
	 * @param email The email for the friend (may or may not have one)
	 * @param phoneNumber The phone Number for the friend (may or may not have one)
	 */
	public Friend (String screenName, String email, String phoneNumber) {
		this.email = email;
		this.screenName = screenName;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Turns the friend's info into a string
	 */
	public String toString() {
		return email + " " + screenName + " " + phoneNumber;
	}
	
}
