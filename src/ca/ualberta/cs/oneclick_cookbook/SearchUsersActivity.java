package ca.ualberta.cs.oneclick_cookbook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SearchUsersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_users);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_users, menu);
		return true;
	}
	
	// Handles clicks from users (but you should know that already)
	public void clickHandler(View v) {
		Intent intent;
		switch(v.getId()) {
		case R.id.bFriSReturn:
			finish();
			break;
		case R.id.bFriSHome:
			intent = new Intent(this.getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			break;
		case R.id.bFriSSearch:
			//TODO Search code goes here
			break;
		}
	}

}
