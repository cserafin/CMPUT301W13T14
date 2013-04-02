package ca.ualberta.cs.oneclick_cookbook;

/**
 * @author	Chris Serafin, Peter Maidens, Ken "Mike" Armstrong, Kimberly Kramer
 * 
 * Allows the user to search the database. It handles the clicks from the user.
 */

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class KeywordSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyword_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.keyword_search, menu);
		return true;
	}

	/**
	 * Function that handles and directs the clicks from the user.
	 * 
	 * @param v
	 *            The view of the button that was clicked.
	 */
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bSSearch:
			break;
		case R.id.bSHome:
			intent = new Intent(this.getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			break;
		case R.id.bSReturn:
			finish();
			break;
		}
	}

}
