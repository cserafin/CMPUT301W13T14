package ca.ualberta.cs.oneclick_cookbook;

import android.app.Application;

// Put anything that needs to be global in here
// Note that the DVM may GC any of your stuff at any time
// so don't assume it will be there always
//TODO Add anything thats needs to be global, and add
// save stuff to onLowMemory
public class GlobalApplication extends Application {
	
	private User current = null;
	
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
	
	//TODO Implement this properly (not always John Doe
	public User getCurrent() {
		if (current == null) {
			current = new User("John Doe", "password", "JD42","jd42@gmail.com");
		}
		
		return current;
	}

}
