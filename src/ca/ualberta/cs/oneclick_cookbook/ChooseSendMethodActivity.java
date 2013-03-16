package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ChooseSendMethodActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_send_method);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_send_method, menu);
		return true;
	}

	/**
	 * Function that handles and directs clicks from the user
	 * 
	 * @param v
	 *            The view of the button that was clicked
	 */
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bChooseSendReturn:
			finish();
			break;
		case R.id.bChooseSendManual:
			break;
		case R.id.bChooseSendSearch:
			break;
		}
	}

}
