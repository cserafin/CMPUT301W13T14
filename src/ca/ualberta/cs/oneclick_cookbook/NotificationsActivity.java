package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Class that displays to the user any notifications 
 * taht they may have.
 * @author Kenneth Armstrong
 *
 */
public class NotificationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notifications, menu);
		return true;
	}
	
	// Handles and directs the clicks from the user
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bNotifMFriends:
			intent = new Intent(this.getApplicationContext(), ManageFriendsActivity.class);
			startActivity(intent);
			break;
		case R.id.bNotifReturn:
			finish();
			break;
		}
	}

}
