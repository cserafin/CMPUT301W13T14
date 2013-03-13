package ca.ualberta.cs.oneclick_cookbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * Class that allows the user to sign up for our service. Talks with remote
 * server to create user.
 * 
 * @author Kenneth Armstrong
 * 
 */
public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	/**
	 * Commits the signup of the user, based on the info they have entered.
	 */
	public void onSignUp() {
		EditText u = (EditText) findViewById(R.id.tSignUpUsername);
		EditText p = (EditText) findViewById(R.id.tSignUpPassword);
		EditText s = (EditText) findViewById(R.id.tSignUpScreenName);
		EditText e = (EditText) findViewById(R.id.tSignUpEmail);
		EditText m = (EditText) findViewById(R.id.tSignUpMobile);

		// Extract the strings from the text boxes
		String username = u.getText().toString();
		String password = p.getText().toString();
		String screenName = s.getText().toString();
		String email = e.getText().toString();
		String mobile = m.getText().toString();

		User user;
		if (!email.equals("")) {
			// If they entered an email, use that
			user = new User(username, password, screenName, email);
		} else {
			// If they didn't enter an email, check for a mobile number
			// Make sure that a valid number was entered
			Integer mobileNumber;
			try {
				mobileNumber = new Integer(mobile);
			} catch (NumberFormatException et) {
				// They didin't enter a valid number
				return;
			}
			user = new User(username, password, screenName, mobileNumber);
		}

		// Check to make sure that valid info was entered
		if (user.isValidInfo() != Constants.GOOD) {
			// Should probably add some message here
			return;
		}

		// TODO Add the networking stuff here

		// End the activity... Possible rather start the main screen
		finish();

	}

	/**
	 * Handles and directs the clicks for this activity.
	 * 
	 * @param v
	 *            The view of te button that was clicked.
	 */
	public void clickHandler(View v) {
		switch (v.getId()) {
		case R.id.bSignUp:
			onSignUp();
			break;
		}
	}

}
