package ca.ualberta.cs.oneclick_cookbook;

public class Friend {
	private String screenName = null;
	private String email = null;
	private String phoneNumber = null;
	
	Friend (String screenName, String email, String phoneNumber) {
		this.email = email;
		this.screenName = screenName;
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		return email + " " + screenName + " " + phoneNumber;
	}
	
}
