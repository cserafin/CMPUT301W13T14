package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SendToFriendActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_to_friend);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_to_friend, menu);
		return true;
	}
	
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bListedFriReturn:
			finish();
			break;
		case R.id.bListedFriSend:
			break;
		}
	}

}
