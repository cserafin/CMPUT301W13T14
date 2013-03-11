/**
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * 
 * Stores all the information about the user.
 */

package ca.ualberta.cs.oneclick_cookbook;

import java.util.ArrayList;

public class User {

	private String userName;
	private String password;
	private String screenName;
	private String emailAddress;
	private int phoneNumber;
	private ArrayList<Recipe> userRecipes = null;
	private Pantry userPantry = null;
	
	/**
	 * Constructor V1
	 * @param userName The username of the user
	 * @param password The password of the user
	 * @param screenName The screen name of the user
	 * @param emailAddress the email address of the user
	 */
	public User(String userName, String password, String screenName, String emailAddress){
		this.setUserName(userName);
		this.setPassword(password);
		this.setScreenName(screenName);
		this.setEmailAddress(emailAddress);
		userRecipes = new ArrayList<Recipe>();
		userPantry = new Pantry();
	}
	
	/**
	 * Constructor V2
	 * @param userName The username of the user
	 * @param password The password of the user
	 * @param screenName The screen name of the user
	 * @param phoneNumber The phone Number of the user
	 */
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
	
	public void addRecipe(Recipe r) {
		userRecipes.add(r);
	}
	
	public void clearRecipes() {
		userRecipes.clear();
	}
	
	public ArrayList<Recipe> getUserRecipes() {
		return userRecipes;
	}
	
	public Pantry getUserPantry() {
		return userPantry;
	}
	
	/**
	 * Conversts the Recipes to a string
	 * @return s The string of the converted Recipes
	 */
	public ArrayList<String> getRecipesToString() {
		ArrayList<String> s = new ArrayList<String>();
		for (int i=0; i<userRecipes.size(); i++) {
			s.add(userRecipes.get(i).toString());
		}
		return s;
	}
	
	public int isValidInfo() {
		return Constants.GOOD;
	}
}
