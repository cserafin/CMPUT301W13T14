package ca.ualberta.cs.oneclick_cookbook;

import android.app.Application;


/**
 * Class that manages the global application state.
 * @author Kenneth Armstrong
 *
 * Put anything that needs to be global in here
 * Note that the DVM may GC any of your stuff at any time
 * so don't assume it will be there always
 * TODO Add anything thats needs to be global, and add
 * save stuff to onLowMemory
 */
public class GlobalApplication extends Application {
	
	private User currentUser = null;
	private Recipe currentRecipe = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}
	
	/**
	 * Returns the current user. Should always be kept up to date
	 * to retain accuracy.
	 * @return currentUser The current user
	 */
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = new User("", "", "","");
		}
		
		return currentUser;
	}
	
	/**
	 * Sets the current user. Should always be kept up to date
	 * to retain accuracy.
	 * @param u The current user
	 */
	public void setCurrentuser(User u) {
		currentUser = u;
	}
	
	/**
	 * Sets the current Recipe. Should always be kept up to date
	 * to retain accuracy.
	 * @param r The current recipe
	 */
	public void setCurrentRecipe(Recipe r) {
		currentRecipe = r;
	}
	
	/**
	 * Returns the current Recipe. Should always be kept up to date
	 * to retain accuracy.
	 * @return currentRecipe The current Recipe
	 */
	public Recipe getCurrentRecipe() {
		if (currentRecipe == null) {
			currentRecipe = new Recipe("", new Pantry(), "");
		}
		return currentRecipe;
	}

}
