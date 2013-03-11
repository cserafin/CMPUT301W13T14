package ca.ualberta.cs.oneclick_cookbook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// Called when the user clicks login
	public void onLogin() {
		EditText u = (EditText) findViewById(R.id.UserName);
		EditText p = (EditText) findViewById(R.id.Password);

		// Get the user name and password
		String username = u.getText().toString();
		String password = p.getText().toString();

		// TODO Add the network stuff here

		
		GlobalApplication app = (GlobalApplication) getApplication();
		app.setCurrentuser(new User(username, password, "abc123", "abc123@gmail.com"));
		finish();

		return;

	}

	// Handles the clicks from this activity
	public void clickHandler(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.bLoginLogin:
			onLogin();
			break;
		case R.id.bLoginGetStarted:
			intent = new Intent(this.getApplicationContext(),
					SignUpActivity.class);
			startActivity(intent);
			break;
		}
	}

}
