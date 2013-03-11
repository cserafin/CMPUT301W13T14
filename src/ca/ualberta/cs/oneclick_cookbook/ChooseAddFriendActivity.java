package ca.ualberta.cs.oneclick_cookbook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ChooseAddFriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_add_friend);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_add_friend, menu);
		return true;
	}
	
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bChooseSReturn:
			finish();
			break;
		case R.id.bChooseSManual:
			intent = new Intent(this.getApplicationContext(),
					EnterFriendActivity.class);
			startActivity(intent);
			break;
		case R.id.bChooseSSearch:
			intent = new Intent(this.getApplicationContext(),
					SearchUsersActivity.class);
			startActivity(intent);
			break;
		}
	}

}
