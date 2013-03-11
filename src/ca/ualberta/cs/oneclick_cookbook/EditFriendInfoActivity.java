package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class EditFriendInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_friend_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_friend_info, menu);
		return true;
	}
	
	// Handles clicks from the user
	public void clickHandler(View v) {
		switch(v.getId()) {
		case R.id.bEditFriReturn:
			finish();
			break;
		case R.id.bEditFriDelete:
			//TODO Add delete code here
			break;
		case R.id.bEditFriSave:
			//TODO Add save code here
			break;
		}
	}

}
