package ca.ualberta.cs.oneclick_cookbook;

public class User {

	private String userName;
	private String password;
	private String screenName;
	private String emailAddress;
	private int phoneNumber;
	
	public User(String userName, String password, String screenName, String emailAddress){
		this.setUserName(userName);
		this.setPassword(password);
		this.setScreenName(screenName);
		this.setEmailAddress(emailAddress);
	}
	
	public User(String userName, String password, String screenName, int phoneNumber){
		this.setUserName(userName);
		this.setPassword(password);
		this.setScreenName(screenName);
		this.setPhoneNumber(phoneNumber);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
