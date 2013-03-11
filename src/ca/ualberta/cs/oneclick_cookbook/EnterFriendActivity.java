package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Class that allows the user to enter their friend's info.
 * @author Kenneth Armstrong
 *
 */
public class EnterFriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_friend);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_friend, menu);
		return true;
	}
	
	// Handles clicks from the user
	public void clickHandler(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.bAddFriReturn:
			finish();
			break;
		case R.id.bAddFriSave:
			//TODO Save code goes here
			break;
		}
	}

}
