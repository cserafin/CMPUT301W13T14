package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Class that allows the user to manage their friends.
 * @author Kenneth Armstrong
 *
 */
public class ManageFriendsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_friends);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_friends, menu);
		return true;
	}

	// Handles clicks from the user
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bManageHome:
			intent = new Intent(this.getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			break;
		case R.id.bManageAdd:
			intent = new Intent(this.getApplicationContext(),
					ChooseAddFriendActivity.class);
			startActivity(intent);
			break;
		}
	}

}
