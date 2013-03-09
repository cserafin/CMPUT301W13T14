package ca.ualberta.cs.oneclick_cookbook;

import android.app.Application;

// Put anything that needs to be global in here
// Note that the DVM may GC any of your stuff at any time
// so don't assume it will be there always
public class GlobalApplication extends Application {
	
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

}
