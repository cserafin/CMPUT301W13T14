package ca.ualberta.cs.oneclick_cookbook;

import android.app.Application;

// Put anything that needs to be global in here
// Note that the DVM may GC any of your stuff at any time
// so don't assume it will be there always
//TODO Add anything thats needs to be global, and add
// save stuff to onLowMemory
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
	
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = new User("", "", "","");
		}
		
		return currentUser;
	}
	
	public void setCurrentuser(User u) {
		currentUser = u;
	}
	
	public void setCurrentRecipe(Recipe r) {
		currentRecipe = r;
	}
	
	public Recipe getCurrentRecipe() {
		if (currentRecipe == null) {
			currentRecipe = new Recipe("", new Pantry(), "");
		}
		return currentRecipe;
	}

}
