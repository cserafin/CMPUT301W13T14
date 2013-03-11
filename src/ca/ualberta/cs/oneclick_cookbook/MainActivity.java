package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Class that represents the main screen of the app.
 * Acts as the controller for the main screen.
 * @author Kenneth Armstrong
 *
 */
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Function that is called when the user resumes on the
	 * main screen. Sets up the welcome text.
	 */
	public void onResume() {
		super.onResume();
		// Set the welcome message (default account is John Doe)
		TextView t = (TextView) findViewById(R.id.tWelcomeMessage);
		GlobalApplication app = (GlobalApplication) getApplication();
		String username = app.getCurrentUser().getUserName();

		// Do some formatting if the name is long
		if (username.length() > 20) {
			t.setText("Hello " + username.substring(0, 20) + "..!");
		}

		else {
			t.setText("Hello " + username + "!");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Function that handles the button clicks from the main activity.
	 * @param v: the view of the button that was clicked.
	 */
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bHomeLogin:
			intent = new Intent(this.getApplicationContext(),
					LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeCreate:
			intent = new Intent(this.getApplicationContext(),
					CreateRecipeActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeRecipes:
			intent = new Intent(this.getApplicationContext(),
					ViewRecipesActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeHistory:
			break;
		case R.id.bHomePantry:
			intent = new Intent(this.getApplicationContext(),
					ManagePantryActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeSearch:
			intent = new Intent(this.getApplicationContext(),
					SearchActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeFriends:
			intent = new Intent(this.getApplicationContext(),
					NotificationsActivity.class);
			startActivity(intent);
			break;
		case R.id.bHomeFav:
			break;
		}
	}
}
